package clayium.api.recipes;

import com.google.common.collect.ImmutableList;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.recipes.*;
import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.recipes.recipeproperties.RecipePropertyStorage;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ItemStackHashStrategy;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that represent machine recipe.<p>
 * <p>
 * Recipes are created using {@link RecipeBuilder} or its subclasses in builder-alike pattern. To get RecipeBuilder use {@link RecipeMap#recipeBuilder()}.<p>
 * <p>
 * Example:
 * RecipeMap.POLARIZER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.APPLE)).outputs(new ItemStack(Items.GOLDEN_APPLE)).duration(256).CEt(480).buildAndRegister();<p>
 * This will create and register Polarizer recipe with Apple as input and Golden apple as output, duration - 256 ticks and energy consumption of 480 EU/t.<p>
 * To get example for particular RecipeMap see {@link RecipeMap}<p>
 * <p>
 * Recipes are immutable.
 */
public class ClayRecipe extends Recipe {

    private final int tier;

    public ClayRecipe(List<CountableIngredient> inputs, List<ItemStack> outputs, List<ChanceEntry> chancedOutputs, List<FluidStack> fluidInputs, List<FluidStack> fluidOutputs, int duration, int EUt, boolean hidden, boolean isCTRecipe, int tier) {
        super(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs, duration, EUt, hidden, isCTRecipe);
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    public List<ItemStack> getResultItemOutputs(int tier, ClayRecipeMap<?> recipeMap) {
        ArrayList<ItemStack> outputs = new ArrayList(GTUtility.copyStackList(this.getOutputs()));
        List<Recipe.ChanceEntry> chancedOutputsList = this.getChancedOutputs();
        List<ItemStack> resultChanced = new ArrayList();
        Iterator var6 = chancedOutputsList.iterator();

        while(var6.hasNext()) {
            Recipe.ChanceEntry chancedOutput = (Recipe.ChanceEntry)var6.next();
            int outputChance = recipeMap.getChanceFunction().chanceFor(chancedOutput.getChance(), chancedOutput.getBoostPerTier(), tier);
            if (GTValues.RNG.nextInt(getMaxChancedValue()) <= outputChance) {
                ItemStack stackToAdd = chancedOutput.getItemStack();
                GTUtility.addStackToItemStackList(stackToAdd, resultChanced);
            }
        }

        outputs.addAll(resultChanced);
        return outputs;
    }

    public ClayRecipe copy() {
        ClayRecipe newRecipe = new ClayRecipe(getInputs(), getOutputs(), getChancedOutputs(), getFluidInputs(), getFluidOutputs(), getDuration(), getEUt(), isHidden(), getIsCTRecipe(), getTier());
        if (getRecipePropertyStorage().getSize() > 0) {
            Iterator var2 = this.getRecipePropertyStorage().getRecipeProperties().iterator();

            while(var2.hasNext()) {
                Map.Entry<RecipeProperty<?>, Object> property = (Map.Entry)var2.next();
                newRecipe.setProperty((RecipeProperty)property.getKey(), property.getValue());
            }
        }

        return newRecipe;
    }

    public ClayRecipe trimRecipeOutputs(ClayRecipe currentRecipe, ClayRecipeMap<?> recipeMap, int itemTrimLimit, int fluidTrimLimit) {
        if (itemTrimLimit == -1 && fluidTrimLimit == -1) {
            return currentRecipe;
        } else {
            currentRecipe = currentRecipe.copy();
            ClayRecipeBuilder<?> builder = new ClayRecipeBuilder(currentRecipe, recipeMap);
            builder.clearOutputs();
            builder.clearChancedOutput();
            builder.clearFluidOutputs();
            Pair<List<ItemStack>, List<ClayRecipe.ChanceEntry>> recipeOutputs = currentRecipe.getItemAndChanceOutputs(itemTrimLimit);
            builder.chancedOutputs((List)recipeOutputs.getRight());
            builder.outputs((Collection)recipeOutputs.getLeft());
            List<FluidStack> recipeFluidOutputs = currentRecipe.getAllFluidOutputs(fluidTrimLimit);
            builder.fluidOutputs(recipeFluidOutputs);
            return (ClayRecipe)builder.build().getResult();
        }
    }
}
