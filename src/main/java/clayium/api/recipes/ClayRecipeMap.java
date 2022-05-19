package clayium.api.recipes;

import clayium.api.gui.widgets.ClayRecipeProgressWidget;
import clayium.api.recipes.crafttweaker.ClayCTRecipe;
import clayium.api.recipes.crafttweaker.ClayCTRecipeBuilder;
import com.google.common.collect.ImmutableList;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.gui.widgets.TankWidget;
import gregtech.api.recipes.*;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.*;
import gregtech.common.ConfigHolder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.items.IItemHandlerModifiable;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;

//@ZenClass("mods.clayium.recipe.ClayRecipeMap")
//@ZenRegister
public class ClayRecipeMap<R extends ClayRecipeBuilder<R>> {

    private static final Map<String, ClayRecipeMap<?>> RECIPE_MAP_REGISTRY = new HashMap<>();
    public IChanceFunction chanceFunction = (chance, boostPerTier, tier) -> chance + (boostPerTier * tier);

    public final String unlocalizedName;

    private final R recipeBuilderSample;
    private final int minInputs, maxInputs;
    private final int minOutputs, maxOutputs;
    private final int minFluidInputs, maxFluidInputs;
    private final int minFluidOutputs, maxFluidOutputs;
    protected final TByteObjectMap<TextureArea> slotOverlays;
    protected TextureArea specialTexture;
    protected int[] specialTexturePosition;
    protected TextureArea progressBarTexture;
    protected MoveType moveType;
    public final boolean isHidden;

    private final Object2ObjectOpenHashMap<FluidKey, Set<ClayRecipe>> recipeFluidMap = new Object2ObjectOpenHashMap<>();
    private final Object2ObjectOpenHashMap<ItemStackKey, Set<ClayRecipe>> recipeItemMap = new Object2ObjectOpenHashMap<>();

    private static final Comparator<ClayRecipe> RECIPE_DURATION_THEN_EU =
            Comparator.comparingInt(ClayRecipe::getDuration)
                    .thenComparingInt(ClayRecipe::getEUt)
                    .thenComparing(ClayRecipe::hashCode);

    private final Set<ClayRecipe> recipeSet = new TreeSet<>(RECIPE_DURATION_THEN_EU);

    private Consumer<ClayRecipeBuilder<?>> onRecipeBuildAction;

    protected SoundEvent sound;

    private ClayRecipeMap<?> smallRecipeMap;

    public ClayRecipeMap(String unlocalizedName,
                         int minInputs, int maxInputs, int minOutputs, int maxOutputs,
                         int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs,
                         R defaultRecipe, boolean isHidden) {
        this.unlocalizedName = unlocalizedName;
        this.slotOverlays = new TByteObjectHashMap<>();
        this.progressBarTexture = GuiTextures.PROGRESS_BAR_ARROW;
        this.moveType = MoveType.HORIZONTAL;

        this.minInputs = minInputs;
        this.minFluidInputs = minFluidInputs;
        this.minOutputs = minOutputs;
        this.minFluidOutputs = minFluidOutputs;

        this.maxInputs = maxInputs;
        this.maxFluidInputs = maxFluidInputs;
        this.maxOutputs = maxOutputs;
        this.maxFluidOutputs = maxFluidOutputs;

        this.isHidden = isHidden;
        defaultRecipe.setRecipeMap(this);
        this.recipeBuilderSample = defaultRecipe;
        RECIPE_MAP_REGISTRY.put(unlocalizedName, this);
    }

    @ZenMethod
    public static List<ClayRecipeMap<?>> getRecipeMaps() {
        return ImmutableList.copyOf(RECIPE_MAP_REGISTRY.values());
    }

    @ZenMethod
    public static ClayRecipeMap<?> getByName(String unlocalizedName) {
        return RECIPE_MAP_REGISTRY.get(unlocalizedName);
    }

    @ZenMethod
    public IChanceFunction getChanceFunction() {
        return chanceFunction;
    }

    public static boolean isFoundInvalidRecipe() {
        return foundInvalidRecipe;
    }

    public static void setFoundInvalidRecipe(boolean foundInvalidRecipe) {
        ClayRecipeMap.foundInvalidRecipe |= foundInvalidRecipe;
        OrePrefix currentOrePrefix = OrePrefix.getCurrentProcessingPrefix();
        if (currentOrePrefix != null) {
            Material currentMaterial = OrePrefix.getCurrentMaterial();
            GTLog.logger.error("Error happened during processing ore registration of prefix {} and material {}. " +
                            "Seems like cross-mod compatibility issue. Report to GTCEu github.",
                    currentOrePrefix, currentMaterial);
        }
    }

    public ClayRecipeMap<R> setProgressBar(TextureArea progressBar, MoveType moveType) {
        this.progressBarTexture = progressBar;
        this.moveType = moveType;
        return this;
    }

    public ClayRecipeMap<R> setSlotOverlay(boolean isOutput, boolean isFluid, TextureArea slotOverlay) {
        return this
                .setSlotOverlay(isOutput, isFluid, false, slotOverlay)
                .setSlotOverlay(isOutput, isFluid, true, slotOverlay);
    }

    public ClayRecipeMap<R> setSlotOverlay(boolean isOutput, boolean isFluid, boolean isLast, TextureArea slotOverlay) {
        this.slotOverlays.put((byte) ((isOutput ? 2 : 0) + (isFluid ? 1 : 0) + (isLast ? 4 : 0)), slotOverlay);
        return this;
    }

    public ClayRecipeMap<R> setSound(SoundEvent sound) {
        this.sound = sound;
        return this;
    }

    @ZenMethod("setChanceFunction")
    public ClayRecipeMap<R> setChanceFunction(IChanceFunction function) {
        chanceFunction = function;
        return this;
    }

    public ClayRecipeMap<R> onRecipeBuild(Consumer<ClayRecipeBuilder<?>> consumer) {
        onRecipeBuildAction = consumer;
        return this;
    }

    public ClayRecipeMap<R> setSmallRecipeMap(ClayRecipeMap<?> recipeMap) {
        this.smallRecipeMap = recipeMap;
        return this;
    }

    public ClayRecipeMap<?> getSmallRecipeMap() {
        return smallRecipeMap;
    }

    /**
     * This is alternative case when machine can input given fluid
     * If this method returns true, machine will receive given fluid even if getRecipesForFluid doesn't have
     * any recipe for this fluid
     */
    public boolean canInputFluidForce(Fluid fluid) {
        return false;
    }

    public Collection<ClayRecipe> getRecipesForFluid(FluidStack fluid) {
        return recipeFluidMap.getOrDefault(new FluidKey(fluid), Collections.emptySet());
    }

    public Collection<ClayRecipe> getRecipesForFluid(FluidKey fluidKey) {
        return recipeFluidMap.getOrDefault(fluidKey, Collections.emptySet());
    }

    private static boolean foundInvalidRecipe = false;

    //internal usage only, use buildAndRegister()
    public void addRecipe(ValidationResult<ClayRecipe> validationResult) {
        validationResult = postValidateRecipe(validationResult);
        switch (validationResult.getType()) {
            case SKIP:
                return;
            case INVALID:
                setFoundInvalidRecipe(true);
                return;
        }
        ClayRecipe recipe = validationResult.getResult();
        if (recipeSet.add(recipe)) {
            for (CountableIngredient countableIngredient : recipe.getInputs()) {
                ItemStack[] stacks = countableIngredient.getIngredient().getMatchingStacks();
                for (ItemStack itemStack : stacks) {
                    ItemStackKey stackKey = KeySharedStack.getRegisteredStack(itemStack);
                    recipeItemMap.computeIfPresent(stackKey, (k, v) -> {
                        v.add(recipe);
                        return v;
                    });
                    recipeItemMap.computeIfAbsent(stackKey, k -> new HashSet<>()).add(recipe);
                }
            }
            for (FluidStack fluid : recipe.getFluidInputs()) {
                if (fluid.tag != null && fluid.tag.hasKey("nonConsumable")) {
                    fluid = fluid.copy();
                    fluid.tag.removeTag("nonConsumable");
                    if (fluid.tag.isEmpty()) {
                        fluid.tag = null;
                    }
                }
                FluidKey fluidKey = new FluidKey(fluid);
                recipeFluidMap.computeIfPresent(fluidKey, (k, v) -> {
                    v.add(recipe);
                    return v;
                });
                recipeFluidMap.computeIfAbsent(fluidKey, k -> new HashSet<>()).add(recipe);
            }
        } else if (ConfigHolder.misc.debug) {
            GTLog.logger.warn("Recipe: {} for Recipe Map {} is a duplicate and was not added", recipe.toString(), this.unlocalizedName);
            if(recipe.getIsCTRecipe()) {
                CraftTweakerAPI.logError(String.format("Recipe: %s for Recipe Map %s is a duplicate and was not added", recipe.toString(), this.unlocalizedName));
            }
        }
    }

    public boolean removeRecipe(ClayRecipe recipe) {
        //if we actually removed this recipe
        if (recipeSet.remove(recipe)) {
            //also iterate trough fluid mappings and remove recipe from them
            recipeFluidMap.values().forEach(fluidMap ->
                    fluidMap.removeIf(fluidRecipe -> fluidRecipe == recipe));
            recipeItemMap.values().forEach(itemMap ->
                    itemMap.removeIf(itemRecipe -> itemRecipe == recipe));

            return true;
        }
        return false;
    }

    protected ValidationResult<ClayRecipe> postValidateRecipe(ValidationResult<ClayRecipe> validationResult) {
        EnumValidationResult recipeStatus = validationResult.getType();
        ClayRecipe recipe = validationResult.getResult();
        if (!GTUtility.isBetweenInclusive(getMinInputs(), getMaxInputs(), recipe.getInputs().size())) {
            GTLog.logger.error("Invalid amount of recipe inputs. Actual: {}. Should be between {} and {} inclusive.", recipe.getInputs().size(), getMinInputs(), getMaxInputs());
            GTLog.logger.error("Stacktrace:", new IllegalArgumentException("Invalid number of Inputs"));
            if(recipe.getIsCTRecipe()) {
                CraftTweakerAPI.logError(String.format("Invalid amount of recipe inputs. Actual: %s. Should be between %s and %s inclusive.", recipe.getInputs().size(), getMinInputs(), getMaxInputs()));
                CraftTweakerAPI.logError("Stacktrace:", new IllegalArgumentException("Invalid number of Inputs"));
            }
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (!GTUtility.isBetweenInclusive(getMinOutputs(), getMaxOutputs(), recipe.getOutputs().size() + recipe.getChancedOutputs().size())) {
            GTLog.logger.error("Invalid amount of recipe outputs. Actual: {}. Should be between {} and {} inclusive.", recipe.getOutputs().size() + recipe.getChancedOutputs().size(), getMinOutputs(), getMaxOutputs());
            GTLog.logger.error("Stacktrace:", new IllegalArgumentException("Invalid number of Outputs"));
            if(recipe.getIsCTRecipe()) {
                CraftTweakerAPI.logError(String.format("Invalid amount of recipe outputs. Actual: %s. Should be between %s and %s inclusive.", recipe.getOutputs().size() + recipe.getChancedOutputs().size(), getMinOutputs(), getMaxOutputs()));
                CraftTweakerAPI.logError("Stacktrace:", new IllegalArgumentException("Invalid number of Outputs"));
            }
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (!GTUtility.isBetweenInclusive(getMinFluidInputs(), getMaxFluidInputs(), recipe.getFluidInputs().size())) {
            GTLog.logger.error("Invalid amount of recipe fluid inputs. Actual: {}. Should be between {} and {} inclusive.", recipe.getFluidInputs().size(), getMinFluidInputs(), getMaxFluidInputs());
            GTLog.logger.error("Stacktrace:", new IllegalArgumentException("Invalid number of Fluid Inputs"));
            if(recipe.getIsCTRecipe()) {
                CraftTweakerAPI.logError(String.format("Invalid amount of recipe fluid inputs. Actual: %s. Should be between %s and %s inclusive.", recipe.getFluidInputs().size(), getMinFluidInputs(), getMaxFluidInputs()));
                CraftTweakerAPI.logError("Stacktrace:", new IllegalArgumentException("Invalid number of Fluid Inputs"));
            }
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (!GTUtility.isBetweenInclusive(getMinFluidOutputs(), getMaxFluidOutputs(), recipe.getFluidOutputs().size())) {
            GTLog.logger.error("Invalid amount of recipe fluid outputs. Actual: {}. Should be between {} and {} inclusive.", recipe.getFluidOutputs().size(), getMinFluidOutputs(), getMaxFluidOutputs());
            GTLog.logger.error("Stacktrace:", new IllegalArgumentException("Invalid number of Fluid Outputs"));
            if(recipe.getIsCTRecipe()) {
                CraftTweakerAPI.logError(String.format("Invalid amount of recipe fluid outputs. Actual: %s. Should be between %s and %s inclusive.", recipe.getFluidOutputs().size(), getMinFluidOutputs(), getMaxFluidOutputs()));
                CraftTweakerAPI.logError("Stacktrace:", new IllegalArgumentException("Invalid number of Fluid Outputs"));
            }
            recipeStatus = EnumValidationResult.INVALID;
        }
        return ValidationResult.newResult(recipeStatus, recipe);
    }

    @Nullable
    public ClayRecipe findRecipe(long tier, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs, int outputFluidTankCapacity) {
        return this.findRecipe(tier, GTUtility.itemHandlerToList(inputs), GTUtility.fluidHandlerToList(fluidInputs), outputFluidTankCapacity);
    }

    /**
     * Finds a Recipe matching the Fluid and/or ItemStack Inputs.
     *
     * @param tier                 Tier of the Machine or Long.MAX_VALUE if it has no Voltage
     * @param inputs                  the Item Inputs
     * @param fluidInputs             the Fluid Inputs
     * @param outputFluidTankCapacity minimal capacity of output fluid tank, used for fluid canner recipes for example
     * @return the Recipe it has found or null for no matching Recipe
     */
    @Nullable
    public ClayRecipe findRecipe(long tier, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity) {
        return findRecipe(tier, inputs, fluidInputs, outputFluidTankCapacity, false);
    }

    /**
     * Finds a Recipe matching the Fluid and/or ItemStack Inputs.
     *
     * @param tier                 Tier of the Machine or Long.MAX_VALUE if it has no Tier
     * @param inputs                  the Item Inputs
     * @param fluidInputs             the Fluid Inputs
     * @param outputFluidTankCapacity minimal capacity of output fluid tank, used for fluid canner recipes for example
     * @param exactVoltage            should require exact voltage matching on recipe. used by craftweaker
     * @return the Recipe it has found or null for no matching Recipe
     */

    @Nullable
    public ClayRecipe findRecipe(long tier, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity, boolean exactVoltage) {
        if (recipeSet.isEmpty())
            return null;
        if (minFluidInputs > 0 && GTUtility.amountOfNonNullElements(fluidInputs) < minFluidInputs) {
            return null;
        }
        if (minInputs > 0 && GTUtility.amountOfNonEmptyStacks(inputs) < minInputs) {
            return null;
        }
        return findByInputsAndFluids(tier, inputs, fluidInputs,exactVoltage);
    }

    @Nullable
    private ClayRecipe findByInputsAndFluids(long tier, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        HashSet<ClayRecipe> iteratedRecipes = new HashSet<>();
        HashSet<ItemStackKey> searchedItems = new HashSet<>();
        HashSet<FluidKey> searchedFluids = new HashSet<>();
        HashMap<Integer, LinkedList<ClayRecipe>> priorityRecipeMap = new HashMap<>();
        HashMap<ClayRecipe, Integer> promotedTimes = new HashMap<>();

        for (ItemStack stack : inputs) {
            if (!stack.isEmpty()) {
                ItemStackKey itemStackKey = KeySharedStack.getRegisteredStack(stack);
                if (!searchedItems.contains(itemStackKey) && recipeItemMap.containsKey(itemStackKey)) {
                    searchedItems.add(itemStackKey);
                    for (ClayRecipe tmpRecipe : recipeItemMap.get(itemStackKey)) {
                        if (!exactVoltage && tier < tmpRecipe.getTier()) {
                            continue;
                        } else if (exactVoltage && tier != tmpRecipe.getTier()) {
                            continue;
                        }
                        calculateRecipePriority(tmpRecipe, promotedTimes, priorityRecipeMap);
                    }
                }
            }
        }

        for (FluidStack fluidStack : fluidInputs) {
            if (fluidStack != null) {
                FluidKey fluidKey = new FluidKey(fluidStack);
                if (!searchedFluids.contains(fluidKey) && recipeFluidMap.containsKey(fluidKey)) {
                    searchedFluids.add(fluidKey);
                    for (ClayRecipe tmpRecipe : recipeFluidMap.get(fluidKey)) {
                        if (!exactVoltage && tier < tmpRecipe.getTier()) {
                            continue;
                        } else if (exactVoltage && tier != tmpRecipe.getTier()) {
                            continue;
                        }
                        calculateRecipePriority(tmpRecipe, promotedTimes, priorityRecipeMap);
                    }
                }
            }
        }

        return prioritizedRecipe(priorityRecipeMap, iteratedRecipes, inputs, fluidInputs);
    }

    private ClayRecipe prioritizedRecipe(Map<Integer, LinkedList<ClayRecipe>> priorityRecipeMap, HashSet<ClayRecipe> iteratedRecipes, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
        for (int i = priorityRecipeMap.size() - 1; i >= 0; i--) {
            if (priorityRecipeMap.containsKey(i)) {
                for (ClayRecipe tmpRecipe : priorityRecipeMap.get(i)) {
                    if (iteratedRecipes.add(tmpRecipe)) {
                        if (tmpRecipe.matches(false, inputs, fluidInputs)) {
                            return tmpRecipe;
                        }
                    }
                }
            }
        }

        return null;
    }

    private void calculateRecipePriority(ClayRecipe recipe, HashMap<ClayRecipe, Integer> promotedTimes, Map<Integer, LinkedList<ClayRecipe>> priorityRecipeMap) {
        Integer p = promotedTimes.get(recipe);
        if (p == null) {
            p = 0;
        }
        promotedTimes.put(recipe, p + 1);
        priorityRecipeMap.computeIfAbsent(p, k -> new LinkedList<>());
        priorityRecipeMap.get(p).add(recipe);
    }

    public ModularUI.Builder createJeiUITemplate(IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(yOffset);
        builder.widget(new ClayRecipeProgressWidget(200, 78, 23 + yOffset, 20, 20, progressBarTexture, moveType, this));
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset);
        if (this.specialTexture != null && this.specialTexturePosition != null)
            addSpecialTexture(builder);
        return builder;
    }

    //this DOES NOT include machine control widgets or binds player inventory
    public ModularUI.Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(yOffset);
        builder.widget(new ClayRecipeProgressWidget(progressSupplier, 78, 23 + yOffset, 20, 20, progressBarTexture, moveType, this));
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset);
        if (this.specialTexture != null && this.specialTexturePosition != null)
            addSpecialTexture(builder);
        return builder;
    }

    //this DOES NOT include machine control widgets or binds player inventory
    public ModularUI.Builder createUITemplateNoOutputs(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(yOffset);
        builder.widget(new ClayRecipeProgressWidget(progressSupplier, 78, 23 + yOffset, 20, 20, progressBarTexture, moveType, this));
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
        if (this.specialTexture != null && this.specialTexturePosition != null)
            addSpecialTexture(builder);
        return builder;
    }

    protected void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
        int itemInputsCount = itemHandler.getSlots();
        int fluidInputsCount = fluidHandler.getTanks();
        boolean invertFluids = false;
        if (itemInputsCount == 0) {
            int tmp = itemInputsCount;
            itemInputsCount = fluidInputsCount;
            fluidInputsCount = tmp;
            invertFluids = true;
        }
        int[] inputSlotGrid = determineSlotsGrid(itemInputsCount);
        int itemSlotsToLeft = inputSlotGrid[0];
        int itemSlotsToDown = inputSlotGrid[1];
        int startInputsX = isOutputs ? 106 : 70 - itemSlotsToLeft * 18;
        int startInputsY = 33 - (int) (itemSlotsToDown / 2.0 * 18) + yOffset;
        boolean wasGroup = itemHandler.getSlots() + fluidHandler.getTanks() == 12;
        if (wasGroup) startInputsY -= 9;
        else if (itemHandler.getSlots() >= 6 && fluidHandler.getTanks() >= 2 && !isOutputs) startInputsY -= 9;
        for (int i = 0; i < itemSlotsToDown; i++) {
            for (int j = 0; j < itemSlotsToLeft; j++) {
                int slotIndex = i * itemSlotsToLeft + j;
                if (slotIndex >= itemInputsCount) break;
                int x = startInputsX + 18 * j;
                int y = startInputsY + 18 * i;
                addSlot(builder, x, y, slotIndex, itemHandler, fluidHandler, invertFluids, isOutputs);
            }
        }
        if (wasGroup) startInputsY += 2;
        if (fluidInputsCount > 0 || invertFluids) {
            if (itemSlotsToDown >= fluidInputsCount && itemSlotsToLeft < 3) {
                int startSpecX = isOutputs ? startInputsX + itemSlotsToLeft * 18 : startInputsX - 18;
                for (int i = 0; i < fluidInputsCount; i++) {
                    int y = startInputsY + 18 * i;
                    addSlot(builder, startSpecX, y, i, itemHandler, fluidHandler, !invertFluids, isOutputs);
                }
            } else {
                int startSpecY = startInputsY + itemSlotsToDown * 18;
                for (int i = 0; i < fluidInputsCount; i++) {
                    int x = isOutputs ? startInputsX + 18 * (i % 3) : startInputsX + itemSlotsToLeft * 18 - 18 - 18 * (i % 3);
                    int y = startSpecY + (i / 3) * 18;
                    addSlot(builder, x, y, i, itemHandler, fluidHandler, !invertFluids, isOutputs);
                }
            }
        }
    }

    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {
        if (!isFluid) {
            builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                    .setBackgroundTexture(getOverlaysForSlot(isOutputs, false, slotIndex == itemHandler.getSlots() - 1)));
        } else {
            builder.widget(new TankWidget(fluidHandler.getTankAt(slotIndex), x, y, 18, 18)
                    .setAlwaysShowFull(true)
                    .setBackgroundTexture(getOverlaysForSlot(isOutputs, true, slotIndex == fluidHandler.getTanks() - 1))
                    .setContainerClicking(true, !isOutputs));
        }
    }

    protected TextureArea[] getOverlaysForSlot(boolean isOutput, boolean isFluid, boolean isLast) {
        TextureArea base = isFluid ? GuiTextures.FLUID_SLOT : GuiTextures.SLOT;
        byte overlayKey = (byte) ((isOutput ? 2 : 0) + (isFluid ? 1 : 0) + (isLast ? 4 : 0));
        if (slotOverlays.containsKey(overlayKey)) {
            return new TextureArea[]{base, slotOverlays.get(overlayKey)};
        }
        return new TextureArea[]{base};
    }

    protected static int[] determineSlotsGrid(int itemInputsCount) {
        int itemSlotsToLeft;
        int itemSlotsToDown;
        double sqrt = Math.sqrt(itemInputsCount);
        //if the number of input has an integer root
        //return it.
        if (sqrt % 1 == 0) {
            itemSlotsToLeft = itemSlotsToDown = (int) sqrt;
        } else if (itemInputsCount == 3) {
            itemSlotsToLeft = 3;
            itemSlotsToDown = 1;
        } else {
            //if we couldn't fit all into a perfect square,
            //increase the amount of slots to the left
            itemSlotsToLeft = (int) Math.ceil(sqrt);
            itemSlotsToDown = itemSlotsToLeft - 1;
            //if we still can't fit all the slots in a grid,
            //increase the amount of slots on the bottom
            if (itemInputsCount > itemSlotsToLeft * itemSlotsToDown) {
                itemSlotsToDown = itemSlotsToLeft;
            }
        }
        return new int[]{itemSlotsToLeft, itemSlotsToDown};
    }

    protected ClayRecipeMap<R> setSpecialTexture(int x, int y, int width, int height, TextureArea area) {
        this.specialTexturePosition = new int[]{x, y, width, height};
        this.specialTexture = area;
        return this;
    }

    protected ModularUI.Builder addSpecialTexture(ModularUI.Builder builder) {
        builder.image(specialTexturePosition[0], specialTexturePosition[1], specialTexturePosition[2], specialTexturePosition[3], specialTexture);
        return builder;
    }


    public Collection<ClayRecipe> getRecipeList() {
        return Collections.unmodifiableList(new ArrayList<>(recipeSet));
    }

    public SoundEvent getSound() {
        return sound;
    }

    @ZenMethod("findRecipe")
    @Method(modid = GTValues.MODID_CT)
    @Nullable
    public ClayCTRecipe ctFindRecipe(long maxVoltage, IItemStack[] itemInputs, ILiquidStack[] fluidInputs, @Optional(valueLong = Integer.MAX_VALUE) int outputFluidTankCapacity) {
        List<ItemStack> mcItemInputs = itemInputs == null ? Collections.emptyList() :
                Arrays.stream(itemInputs)
                        .map(CraftTweakerMC::getItemStack)
                        .collect(Collectors.toList());
        List<FluidStack> mcFluidInputs = fluidInputs == null ? Collections.emptyList() :
                Arrays.stream(fluidInputs)
                        .map(CraftTweakerMC::getLiquidStack)
                        .collect(Collectors.toList());
        ClayRecipe backingRecipe = findRecipe(maxVoltage, mcItemInputs, mcFluidInputs, outputFluidTankCapacity, true);
        return backingRecipe == null ? null : new ClayCTRecipe(this, backingRecipe);
    }

    @ZenGetter("recipes")
    @Method(modid = GTValues.MODID_CT)
    public List<ClayCTRecipe> ccGetRecipeList() {
        return getRecipeList().stream()
                .map(recipe -> new ClayCTRecipe(this, recipe))
                .collect(Collectors.toList());
    }

    @ZenGetter("localizedName")
    public String getLocalizedName() {
        return LocalizationUtils.format("recipemap." + unlocalizedName + ".name");
    }

    @ZenGetter("unlocalizedName")
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public R recipeBuilder() {
        return recipeBuilderSample.copy().onBuild(onRecipeBuildAction);
    }

    @ZenMethod("recipeBuilder")
    @Method(modid = GTValues.MODID_CT)
    public ClayCTRecipeBuilder ctRecipeBuilder() {
        return new ClayCTRecipeBuilder(recipeBuilder());
    }

    @ZenGetter("minInputs")
    public int getMinInputs() {
        return minInputs;
    }

    @ZenGetter("maxInputs")
    public int getMaxInputs() {
        return maxInputs;
    }

    @ZenGetter("minOutputs")
    public int getMinOutputs() {
        return minOutputs;
    }

    @ZenGetter("maxOutputs")
    public int getMaxOutputs() {
        return maxOutputs;
    }

    @ZenGetter("minFluidInputs")
    public int getMinFluidInputs() {
        return minFluidInputs;
    }

    @ZenGetter("maxFluidInputs")
    public int getMaxFluidInputs() {
        return maxFluidInputs;
    }

    @ZenGetter("minFluidOutputs")
    public int getMinFluidOutputs() {
        return minFluidOutputs;
    }

    @ZenGetter("maxFluidOutputs")
    public int getMaxFluidOutputs() {
        return maxFluidOutputs;
    }

    @Override
    @ZenMethod
    public String toString() {
        return "RecipeMap{" +
                "unlocalizedName='" + unlocalizedName + '\'' +
                '}';
    }

    @FunctionalInterface
    @ZenClass("mods.clayium.recipe.IChanceFunction")
    @ZenRegister
    public interface IChanceFunction {
        int chanceFor(int chance, int boostPerTier, int boostTier);
    }
}
