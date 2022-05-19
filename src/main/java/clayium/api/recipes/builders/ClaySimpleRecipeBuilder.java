package clayium.api.recipes.builders;

import clayium.api.recipes.ClayRecipe;
import clayium.api.recipes.ClayRecipeBuilder;
import clayium.api.recipes.ClayRecipeMap;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.util.ValidationResult;

public class ClaySimpleRecipeBuilder extends ClayRecipeBuilder<ClaySimpleRecipeBuilder> {

    public ClaySimpleRecipeBuilder() {
    }

    public ClaySimpleRecipeBuilder(ClayRecipe recipe, ClayRecipeMap<ClaySimpleRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public ClaySimpleRecipeBuilder(ClayRecipeBuilder<ClaySimpleRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public ClaySimpleRecipeBuilder copy() {
        return new ClaySimpleRecipeBuilder(this);
    }

    public ValidationResult<ClayRecipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new ClayRecipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs, duration, EUt, hidden, isCTRecipe, tier));
    }
}
