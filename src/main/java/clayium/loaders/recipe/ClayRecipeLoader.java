package clayium.loaders.recipe;

import clayium.api.recipes.ClayRecipeMaps;
import clayium.common.blocks.BlockClayOre;
import clayium.common.blocks.BlockCompressedClay;
import clayium.common.blocks.ClayMetaBlocks;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import static clayium.common.blocks.ClayMetaBlocks.CLAY_ORE;
import static clayium.common.blocks.ClayMetaBlocks.COMPRESSED_CRAY;
import static clayium.common.items.ClayMetaItems.*;
import static clayium.common.metatileentities.ClayMetaTileEntities.*;
import static gregtech.api.unification.material.Materials.*;

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

        //DENSE_CLAY
        ModHandler.addShapedRecipe("dense_clay", COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.DENSE),
                "CCC", "CCC", "CCC", 'C', Blocks.CLAY);
        ModHandler.addShapedRecipe("compressed_clay", COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED),
                "CCC", "CCC", "CCC", 'C', COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));

        //CLAY_WORKTABLE
        ModHandler.addShapedRecipe("clay_worktable", CLAY_WORKTABLE.getStackForm(),
                "CC ", "CC ", "   ", 'C', COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));
        //CLAY_CRAFTING_BOARD_OVERLAY
        ModHandler.addShapedRecipe("clay_crafting_board", CLAY_CRAFTING_BOARD.getStackForm(),
                "CCC", "   ", "   ", 'C', COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));
        //CLAY_SHOVEL
        ModHandler.addShapedRecipe("clay_crafting_board", CLAY_SHOVEL.getStackForm(Clay),
                " P ", " S ", " S ", 'P', CLAY_PLATE, 'S', CLAY_STICK);
        //LARGE_CLAY_BALL
        ModHandler.addShapelessRecipe("clay_ball_large", CLAY_BALL_LARGE.getStackForm(),
                Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL);

        //CLAY_WORKTABLE_USAGE
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(10)
                .input(Items.CLAY_BALL)
                .notConsumable(CLAYTASK_PAT)
                .output(CLAY_STICK)
                .duration(4).tier(0).buildAndRegister();
        //placeholder
        ClayRecipeMaps.CLAY_WORKTABLE_RECIPES.recipeBuilder().CEt(10)
                .input(CLAY_BALL_LARGE)
                .output(CLAY_PLATE)
                .duration(10).tier(1).buildAndRegister();

        //CLAY_MINER_USAGE
        ClayRecipeMaps.CLAY_MINER_RECIPES.recipeBuilder().CEt(10)
                .input(Items.COAL)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.NORMAL),8000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.DENSE),4000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.LARGE_DENSE),2000,0)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_MINER_RECIPES.recipeBuilder().CEt(10)
                .input(OrePrefix.gem, Charcoal)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.NORMAL),8000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.DENSE),4000,0)
                .chancedOutput(CLAY_ORE.getItemVariant(BlockClayOre.BlockType.LARGE_DENSE),2000,0)
                .duration(10).tier(0).buildAndRegister();
    }
}
