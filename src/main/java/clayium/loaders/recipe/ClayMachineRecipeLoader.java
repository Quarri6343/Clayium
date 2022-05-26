package clayium.loaders.recipe;

import clayium.api.recipes.ClayRecipeMaps;
import clayium.common.blocks.BlockClayOre;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Items;

import static clayium.api.ClayValues.microCE;
import static clayium.common.blocks.ClayMetaBlocks.CLAY_ORE;
import static clayium.common.items.ClayMetaItems.*;
import static gregtech.api.unification.material.Materials.Charcoal;

public class ClayMachineRecipeLoader {

    public static void Init(){
        //CLAY_WORKTABLE_USAGE
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(Items.CLAY_BALL)
                .notConsumable(CLAYTASK_PAT)
                .output(CLAY_STICK)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_PLATE,3)
                .notConsumable(CLAYTASK_PAT)
                .output(CLAY_BALL_LARGE)
                .duration(40).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_DISC)
                .notConsumable(CLAYTASK_PRESS)
                .output(CLAY_SLICER_RAW)
                .duration(15).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_DISC)
                .notConsumable(CLAYTASK_ROLL)
                .notConsumable(CLAY_ROLLING_PIN)
                .output(CLAY_SLICER_RAW)
                .duration(2).tier(0).buildAndRegister();

        //todo:replace with proper recipe
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_BALL_LARGE)
                .output(CLAY_PLATE)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_PLATE,9)
                .output(CLAY_PLATE_LARGE)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_STICK,5)
                .output(CLAY_GEAR)
                .duration(20).tier(0).buildAndRegister();

        //CLAY_MINER_USAGE
        ClayRecipeMaps.CLAY_MINER_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(Items.COAL)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.NORMAL),8000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.DENSE),2000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.LARGE_DENSE),20,0)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_MINER_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(OrePrefix.gem, Charcoal)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.NORMAL),8000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.DENSE),2000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.LARGE_DENSE),20,0)
                .duration(10).tier(0).buildAndRegister();
    }
}
