package clayium.api.util;

import clayium.api.recipes.ClayRecipe;
import clayium.api.recipes.ClayRecipeMap;
import gregtech.api.recipes.CountableIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.util.CTRecipeHelper.getCtItemString;

public class ClayCTRecipeHelper {

    public static String getRecipeRemoveLine(ClayRecipeMap<?> recipeMap, ClayRecipe recipe) {
        StringBuilder builder = new StringBuilder();
        builder.append("<recipemap:")
                .append(recipeMap.unlocalizedName)
                .append(">.findRecipe(")
                .append(recipe.getEUt())
                .append(", ");

        if (recipe.getInputs().size() > 0) {
            builder.append("[");
            for (CountableIngredient ci : recipe.getInputs()) {
                String ingredient = getCtItemString(ci);
                if (ingredient != null)
                    builder.append(ingredient);
            }
            builder.delete(builder.length() - 2, builder.length())
                    .append("], ");
        } else {
            builder.append("null, ");
        }

        if (recipe.getFluidInputs().size() > 0) {
            builder.append("[");
            for (FluidStack fluidStack : recipe.getFluidInputs()) {
                builder.append("<liquid:")
                        .append(fluidStack.getFluid().getName())
                        .append(">");

                if (fluidStack.amount > 1) {
                    builder.append(" * ")
                            .append(fluidStack.amount);
                }

                builder.append(", ");
            }
            builder.delete(builder.length() - 2, builder.length())
                    .append("]");
        } else {
            builder.append("null");
        }


        builder.append(").remove();");
        return builder.toString();
    }

    public static String getFirstOutputString(ClayRecipe recipe) {
        String output = "";
        if (!recipe.getOutputs().isEmpty()) {
            ItemStack item = recipe.getOutputs().get(0);
            output = item.getDisplayName() + " * " + item.getCount();
        } else if (!recipe.getFluidOutputs().isEmpty()) {
            FluidStack fluid = recipe.getFluidOutputs().get(0);
            output = fluid.getLocalizedName() + " * " + fluid.amount;
        }
        return output;
    }
}
