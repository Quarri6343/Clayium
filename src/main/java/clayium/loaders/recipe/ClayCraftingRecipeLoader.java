package clayium.loaders.recipe;

import clayium.common.blocks.BlockClayMachineHull;
import clayium.common.blocks.BlockCompressedClay;
import gregtech.api.recipes.ModHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static clayium.common.blocks.ClayMetaBlocks.CLAY_MACHINE_HULL;
import static clayium.common.blocks.ClayMetaBlocks.COMPRESSED_CRAY;
import static clayium.common.items.ClayMetaItems.*;
import static clayium.common.metatileentities.ClayMetaTileEntities.*;
import static gregtech.api.unification.material.Materials.Clay;

public class ClayCraftingRecipeLoader {

    public static void Init(){
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

        //CLAY_COMPRESSION
        ModHandler.addShapedRecipe("dense_clay", COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.DENSE),
                "CCC", "CCC", "CCC", 'C', Blocks.CLAY);
        ModHandler.addShapedRecipe("compressed_clay", COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED),
                "CCC", "CCC", "CCC", 'C', COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));

        //CLAY_WORKTABLE
        ModHandler.addShapedRecipe("clay_worktable", CLAY_WORKTABLE.getStackForm(),
                "CC ", "CC ", "   ", 'C', COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));
        //CLAY_CRAFTING_BOARD
        ModHandler.addShapedRecipe("clay_crafting_board", CLAY_CRAFTING_BOARD.getStackForm(),
                "CCC", "   ", "   ", 'C', COMPRESSED_CRAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));
        //CLAY_SHOVEL
        ModHandler.addShapedRecipe("clay_shovel", CLAY_SHOVEL.getStackForm(Clay),
                " P ", " S ", " S ", 'P', CLAY_PLATE, 'S', CLAY_STICK);
        //LARGE_CLAY_BALL
        ModHandler.addShapelessRecipe("clay_ball_large", CLAY_BALL_LARGE.getStackForm(),
                Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL,Items.CLAY_BALL);
        //CLAY_MANUAL_MINER
        ModHandler.addShapedRecipe("clay_manual_miner", CLAY_MANUAL_MINER.getStackForm(),
                "SGS", "GHG", "SGS", 'H', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.CLAY), 'S', CLAY_STICK, 'G', CLAY_GEAR);
        //RAW_CLAY_MACHINE_HULL
        ModHandler.addShapedRecipe("raw_clay_machine_hull", CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.RAW_CLAY),
                "PPP", "PGP", "PPP", 'G', CLAY_GEAR, 'P', CLAY_PLATE_LARGE);
        //CLAY_ROLLING_PIN_RAW
        ModHandler.addShapedRecipe("clay_rolling_pin_raw", CLAY_ROLLING_PIN_RAW.getStackForm(),
                "SCS", "   ", "   ", 'S', CLAY_STICK_SHORT, 'C', CLAY_CYLINDER);
        //CLAY_SPATULA_RAW
        ModHandler.addShapedRecipe("clay_spatula_raw", CLAY_SPATULA_RAW.getStackForm(),
                "SB ", "   ", "   ", 'S', CLAY_STICK_SHORT, 'B', CLAY_BLADE);
    }
}
