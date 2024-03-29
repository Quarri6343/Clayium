package clayium.integration.jei.recipe;

import clayium.api.recipes.ClayRecipe;
import clayium.api.recipes.ClayRecipeMap;
import clayium.api.util.ClayCTRecipeHelper;
import clayium.api.util.ClayUtility;
import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.recipes.Recipe.ChanceEntry;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.recipeproperties.PrimitiveProperty;
import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.util.ClipboardUtil;
import gregtech.integration.jei.utils.AdvancedRecipeWrapper;
import gregtech.integration.jei.utils.JeiButton;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

public class ClayRecipeWrapper extends AdvancedRecipeWrapper {

    private static final int LINE_HEIGHT = 10;

    private final ClayRecipeMap<?> recipeMap;
    private final ClayRecipe recipe;
    private String lastCopiedRemoval;

    public ClayRecipeWrapper(ClayRecipeMap<?> recipeMap, ClayRecipe recipe) {
        this.recipeMap = recipeMap;
        this.recipe = recipe;
    }

    public ClayRecipe getRecipe() {
        return recipe;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {

        // Inputs
        if (!recipe.getInputs().isEmpty()) {
            List<List<ItemStack>> matchingInputs = new ArrayList<>(recipe.getInputs().size());
            for (GTRecipeInput recipeInput : recipe.getInputs()) {
                matchingInputs.add(Arrays.stream(recipeInput.getInputStacks())
                        .map(ItemStack::copy)
                        .collect(Collectors.toList()));
            }
            ingredients.setInputLists(VanillaTypes.ITEM, matchingInputs);
        }

        // Fluid Inputs
        if (!recipe.getFluidInputs().isEmpty()) {
            List<FluidStack> matchingFluidInputs = new ArrayList<>(recipe.getFluidInputs().size());

            for (GTRecipeInput fluidInput : recipe.getFluidInputs()) {
                FluidStack fluidStack = fluidInput.getInputFluidStack();
                Collections.addAll(matchingFluidInputs, fluidStack);
            }
            ingredients.setInputs(VanillaTypes.FLUID, matchingFluidInputs);
        }

        // Outputs
        if (!recipe.getOutputs().isEmpty() || !recipe.getChancedOutputs().isEmpty()) {
            List<ItemStack> recipeOutputs = recipe.getOutputs()
                    .stream().map(ItemStack::copy).collect(Collectors.toList());

            List<ChanceEntry> chancedOutputs = recipe.getChancedOutputs();
            chancedOutputs.sort(Comparator.comparingInt(entry -> entry == null ? 0 : entry.getChance()));
            for (ChanceEntry chancedEntry : chancedOutputs) {
                recipeOutputs.add(chancedEntry.getItemStackRaw());
            }
            ingredients.setOutputs(VanillaTypes.ITEM, recipeOutputs);
        }

        // Fluid Outputs
        if (!recipe.getFluidOutputs().isEmpty()) {
            ingredients.setOutputs(VanillaTypes.FLUID, recipe.getFluidOutputs().stream()
                    .map(FluidStack::copy)
                    .collect(Collectors.toList()));
        }
    }

    public void addItemTooltip(int slotIndex, boolean input, Object ingredient, List<String> tooltip) {
        boolean notConsumed = input && isNotConsumedItem(slotIndex);

        ChanceEntry entry = null;
        int outputIndex = slotIndex - recipeMap.getMaxInputs();
        if (!input && !recipe.getChancedOutputs().isEmpty() && outputIndex >= recipe.getOutputs().size()) {
            entry = recipe.getChancedOutputs().get(outputIndex - recipe.getOutputs().size());
        }

        if (entry != null) {
            double chance = entry.getChance() / 100.0;
            double boost = entry.getBoostPerTier() / 100.0;
            tooltip.add(I18n.format("gregtech.recipe.chance", chance, boost));
        } else if (notConsumed) {
            tooltip.add(I18n.format("gregtech.recipe.not_consumed"));
        }
    }

    public void addFluidTooltip(int slotIndex, boolean input, Object ingredient, List<String> tooltip) {
        boolean notConsumed = input && isNotConsumedFluid(slotIndex);

        if (notConsumed) {
            tooltip.add(I18n.format("gregtech.recipe.not_consumed"));
        }
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
        int yPosition = recipeHeight - getPropertyListHeight();
        if (recipeMap.exactTier) {
            minecraft.fontRenderer.drawString(I18n.format("gui.Common.exact.tier"), 0, yPosition, 0x6a0dad);
        }
        if (!recipe.hasProperty(PrimitiveProperty.getInstance())) {
            minecraft.fontRenderer.drawString(I18n.format("gui.Common.tier", Math.abs(recipe.getTier())), 0, yPosition += LINE_HEIGHT, 0x111111);
            minecraft.fontRenderer.drawString(I18n.format("gui.Common.energy", String.format("%sCE/t x %dt = %s", ClayUtility.getCEWithUnit(recipe.getCEt()), recipe.getDuration(), ClayUtility.getCEWithUnit(recipe.getCEt() * recipe.getDuration()))), 0, yPosition += LINE_HEIGHT, 0x111111);
        } else yPosition -= LINE_HEIGHT * 2;
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.duration", recipe.getDuration() / 20f), 0, yPosition += LINE_HEIGHT, 0x111111);
        for (Map.Entry<RecipeProperty<?>, Object> propertyEntry : recipe.getPropertyValues()) {
            if (!propertyEntry.getKey().isHidden()) {
                propertyEntry.getKey().drawInfo(minecraft, 0, yPosition += LINE_HEIGHT, 0x111111, propertyEntry.getValue());
            }
        }
    }

    @Override
    public void initExtras() {
        BooleanSupplier creativePlayerCtPredicate = () -> Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isCreative() && Loader.isModLoaded(GTValues.MODID_CT);
        buttons.add(new JeiButton(166, 2, 10, 10)
                .setTextures(GuiTextures.BUTTON_CLEAR_GRID)
                .setTooltipBuilder(lines -> lines.add("Copies a CraftTweaker script, to remove this recipe, to the clipboard"))
                .setClickAction((minecraft, mouseX, mouseY, mouseButton) -> {
                    String recipeLine = ClayCTRecipeHelper.getRecipeRemoveLine(recipeMap, recipe);
                    String output = ClayCTRecipeHelper.getFirstOutputString(recipe);
                    if (!output.isEmpty()) {
                        output = "// " + output + "\n";
                    }
                    String copyString = output + recipeLine + "\n";
                    ClipboardUtil.copyToClipboard(copyString);
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Copied [\u00A76" + recipeLine + "\u00A7r] to the clipboard"));
                    this.lastCopiedRemoval = copyString;
                    return true;
                })
                .setActiveSupplier(creativePlayerCtPredicate));
    }

    public ChanceEntry getOutputChance(int slot) {
        if (slot >= recipe.getChancedOutputs().size() || slot < 0) return null;
        return recipe.getChancedOutputs().get(slot);
    }

    public boolean isNotConsumedItem(int slot) {
        if (slot >= recipe.getInputs().size()) return false;
        return recipe.getInputs().get(slot).isNonConsumable();
    }

    public boolean isNotConsumedFluid(int slot) {
        if (slot >= recipe.getFluidInputs().size()) return false;
        return recipe.getFluidInputs().get(slot).isNonConsumable();
    }

    private int getPropertyListHeight() {
        return (recipe.getUnhiddenPropertyCount() + 4) * LINE_HEIGHT - 3;
    }
}
