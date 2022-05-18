package clayium.loaders.recipe;

import clayium.api.recipes.ClayRecipeMaps;
import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.unification.material.Materials.*;

public class ClayRecipeLoader {

    public static void init() {

        //Greenhouse Casing
//        ModHandler.addShapedRecipe("gte_metal_casing:0", GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.GREENHOUSE,2) ,
//                "PhP", "PFP", "PwP", 'P',new UnificationEntry(plate, Galvalume),'F',new UnificationEntry(frameGt, Galvalume));

        //PLACEHOLDER
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().EUt(10)
                .input(OrePrefix.dust, Clay, 1)
                .output(OrePrefix.block, Clay, 1)
                .duration(30).buildAndRegister();
    }
}
