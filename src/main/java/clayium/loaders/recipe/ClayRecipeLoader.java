package clayium.loaders.recipe;

import clayium.api.recipes.ClayRecipeMaps;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Items;

import static gregtech.api.unification.material.Materials.*;
import static clayium.common.items.ClayMetaItems.*;

public class ClayRecipeLoader {

    public static void init() {

        //CLAYTASK
        ModHandler.addShapedRecipe("claytask_cutcircle", CLAYTASK_CUTCIRCLE.getStackForm(),
                " P ", " C ", "   ", 'C', Items.CLAY_BALL, 'P', Items.PAPER);
        ModHandler.addShapedRecipe("claytask_cutsquare", CLAYTASK_CUTSQUARE.getStackForm(),
                "  P", " C ", "   ", 'C', Items.CLAY_BALL, 'P', Items.PAPER);
        ModHandler.addShapedRecipe("claytask_pat", CLAYTASK_PAT.getStackForm(),
                "   ", " CP", "   ", 'C', Items.CLAY_BALL, 'P', Items.PAPER);
        ModHandler.addShapedRecipe("claytask_press", CLAYTASK_PRESS.getStackForm(),
                "   ", " C ", "  P", 'C', Items.CLAY_BALL, 'P', Items.PAPER);
        ModHandler.addShapedRecipe("claytask_roll", CLAYTASK_ROLL.getStackForm(),
                "   ", " C ", " P ", 'C', Items.CLAY_BALL, 'P', Items.PAPER);
        ModHandler.addShapedRecipe("claytask_slice", CLAYTASK_SLICE.getStackForm(),
                "   ", " C ", "P  ", 'C', Items.CLAY_BALL, 'P', Items.PAPER);

        //CLAY_WORKTABLE
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(10)
                .input(OrePrefix.dust, Clay, 1)
                .output(OrePrefix.block, Clay, 1)
                .duration(30).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(10)
                .input(OrePrefix.block, Clay, 9)
                .output(OrePrefix.block, Clay, 1)
                .duration(30).tier(1).buildAndRegister();
    }
}
