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
                .input(CLAY_CYLINDER)
                .notConsumable(CLAYTASK_PAT)
                .output(CLAY_NEEDLE)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_BALL_LARGE)
                .notConsumable(CLAYTASK_PAT)
                .output(CLAY_CYLINDER)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_PLATE)
                .notConsumable(CLAYTASK_PRESS)
                .output(CLAY_BLADE)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_DISC)
                .notConsumable(CLAYTASK_PRESS)
                .output(CLAY_SLICER_RAW)
                .duration(15).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_BALL_LARGE)
                .notConsumable(CLAYTASK_PRESS)
                .output(CLAY_DISC)
                .duration(30).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_PLATE)
                .notConsumable(CLAYTASK_ROLL)
                .notConsumable(CLAY_ROLLING_PIN)
                .output(CLAY_BLADE)
                .output(Items.CLAY_BALL, 2)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_PLATE,6)
                .notConsumable(CLAYTASK_ROLL)
                .notConsumable(CLAY_ROLLING_PIN)
                .output(CLAY_PLATE_LARGE)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_DISC)
                .notConsumable(CLAYTASK_ROLL)
                .notConsumable(CLAY_ROLLING_PIN)
                .output(CLAY_SLICER_RAW)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_BALL_LARGE)
                .notConsumable(CLAYTASK_ROLL)
                .notConsumable(CLAY_ROLLING_PIN)
                .output(CLAY_DISC)
                .output(Items.CLAY_BALL, 2)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_DISC)
                .notConsumable(CLAYTASK_CUTSQUARE)
                .notConsumable(CLAY_SLICER)
                .output(CLAY_PLATE)
                .output(Items.CLAY_BALL, 2)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_DISC)
                .notConsumable(CLAYTASK_CUTCIRCLE)
                .notConsumable(CLAY_SPATULA)
                .output(CLAY_RING)
                .output(CLAY_DISC_SMALL)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_DISC_SMALL)
                .notConsumable(CLAYTASK_CUTSQUARE)
                .notConsumable(CLAY_SPATULA)
                .output(CLAY_RING_SMALL)
                .output(CLAY_STICK_SHORT)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_PLATE)
                .notConsumable(CLAYTASK_SLICE)
                .notConsumable(CLAY_SLICER)
                .output(CLAY_STICK,4)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(CLAY_CYLINDER)
                .notConsumable(CLAYTASK_SLICE)
                .notConsumable(CLAY_SLICER)
                .output(CLAY_DISC_SMALL,8)
                .duration(7).tier(0).buildAndRegister();

        //CLAY_MINER_USAGE
        ClayRecipeMaps.CLAY_MINER_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(Items.COAL)
                .output(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.NORMAL).getItem())
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.NORMAL),6000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.DENSE),4000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.LARGE_DENSE),20,0)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_MINER_RECIPES.recipeBuilder().CEt(50 * microCE)
                .input(OrePrefix.gem, Charcoal)
                .output(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.NORMAL).getItem())
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.NORMAL),6000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.DENSE),4000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.LARGE_DENSE),20,0)
                .duration(10).tier(0).buildAndRegister();
    }
}
