package clayium.loaders.recipe;

import clayium.common.blocks.BlockClayMachineHull;
import clayium.common.blocks.BlockCompressedClay;
import gregtech.api.recipes.ModHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static clayium.common.blocks.ClayMetaBlocks.CLAY_MACHINE_HULL;
import static clayium.common.blocks.ClayMetaBlocks.COMPRESSED_CLAY;
import static clayium.common.items.ClayMetaItems.*;
import static clayium.common.metatileentities.ClayMetaTileEntities.*;
import static gregtech.api.unification.material.Materials.Clay;

public class ClayCraftingRecipeLoader {

    public static void Init() {
        registerComponentCraftingRecipes();
        registerMachineCraftingRecipes();
        registerMiscCraftingRecipes();
    }

    private static void registerMachineCraftingRecipes() {
        ModHandler.addShapedRecipe("clay_manual_miner", CLAY_MANUAL_MINER.getStackForm(),
                "SGS", "GHG", "SGS", 'H', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.RAW_CLAY), 'S', CLAY_STICK, 'G', CLAY_GEAR);

        ModHandler.addShapedRecipe("clay_bending_machine", CLAY_BENDING_MACHINE[0].getStackForm(),
                "SCG", "PMP", "SCG", 'S', CLAY_SPINDLE, 'C', CLAY_CYLINDER, 'G', CLAY_GEAR, 'P', CLAY_PLATE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.CLAY));
        ModHandler.addShapedRecipe("clay_wire_drawing_machine", CLAY_WIRE_DRAWING_MACHINE[0].getStackForm(),
                "GSG", "PMP", "GSG", 'G', CLAY_GEAR, 'S', CLAY_SPINDLE, 'P', CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.CLAY));
        ModHandler.addShapedRecipe("clay_pipe_drawing_machine", CLAY_PIPE_DRAWING_MACHINE[0].getStackForm(),
                "GSG", "CMP", "GSG", 'G', CLAY_GEAR, 'S', CLAY_SPINDLE, 'C', CLAY_CYLINDER, 'P', CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.CLAY));
        ModHandler.addShapedRecipe("clay_cutting_machine", CLAY_CUTTING_MACHINE[0].getStackForm(),
                "PGP", "SMC", "PGP", 'P', CLAY_PLATE, 'G', CLAY_GEAR, 'S', CLAY_SPINDLE, 'C', CLAY_CUTTING_HEAD, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.CLAY));
        ModHandler.addShapedRecipe("clay_lathe", CLAY_LATHE[0].getStackForm(),
                "PGP", "IMS", "PGP", 'P', CLAY_PLATE, 'G', CLAY_GEAR, 'I', CLAY_STICK, 'S', CLAY_SPINDLE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.CLAY));
        ModHandler.addShapedRecipe("clay_cobblestone_generator", CLAY_COBBLESTONE_GENERATOR[0].getStackForm(),
                " G ", "PMP", " G ", 'G', CLAY_GEAR, 'P', CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.CLAY));

        ModHandler.addShapedRecipe("dense_clay_bending_machine", CLAY_BENDING_MACHINE[1].getStackForm(),
                "SCG", "PMP", "SCG", 'S', DENSE_CLAY_SPINDLE, 'C', DENSE_CLAY_CYLINDER, 'G', DENSE_CLAY_GEAR, 'P', DENSE_CLAY_PLATE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));
        ModHandler.addShapedRecipe("dense_clay_wire_drawing_machine", CLAY_WIRE_DRAWING_MACHINE[1].getStackForm(),
                "GSG", "PMP", "GSG", 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'P', DENSE_CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));
        ModHandler.addShapedRecipe("dense_clay_pipe_drawing_machine", CLAY_PIPE_DRAWING_MACHINE[1].getStackForm(),
                "GSG", "CMP", "GSG", 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'C', DENSE_CLAY_CYLINDER, 'P', DENSE_CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));
        ModHandler.addShapedRecipe("dense_clay_cutting_machine", CLAY_CUTTING_MACHINE[1].getStackForm(),
                "PGP", "SMC", "PGP", 'P', DENSE_CLAY_PLATE, 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'C', DENSE_CLAY_CUTTING_HEAD, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));
        ModHandler.addShapedRecipe("dense_clay_lathe", CLAY_LATHE[1].getStackForm(),
                "PGP", "IMS", "PGP", 'P', DENSE_CLAY_PLATE, 'G', DENSE_CLAY_GEAR, 'I', DENSE_CLAY_STICK, 'S', DENSE_CLAY_SPINDLE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));
        ModHandler.addShapedRecipe("dense_clay_cobblestone_generator", CLAY_COBBLESTONE_GENERATOR[1].getStackForm(),
                " G ", "PMP", " G ", 'G', DENSE_CLAY_GEAR, 'P', DENSE_CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));
        ModHandler.addShapedRecipe("dense_clay_condenser", CLAY_CONDENSER[0].getStackForm(),
                "GPG", "PMP", "GPG", 'G', DENSE_CLAY_GEAR, 'P', DENSE_CLAY_PLATE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));
        ModHandler.addShapedRecipe("dense_clay_grinder", CLAY_GRINDER[0].getStackForm(),
                "PGP", "SMS", "PEP", 'P', DENSE_CLAY_PLATE, 'G', DENSE_CLAY_GRINDING_HEAD, 'S', DENSE_CLAY_SPINDLE, 'E', DENSE_CLAY_GEAR, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));
        ModHandler.addShapedRecipe("dense_clay_decomposer", CLAY_DECOMPOSER[0].getStackForm(),
                "GSG", "CMC", "GPG", 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'C', CLAY_CIRCUIT, 'P', DENSE_CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY));

        ModHandler.addShapedRecipe("energetic_clay_condenser", ENERGETIC_CLAY_CONDENSER[0].getStackForm(),
                "PGP", "CMC", "PBP", 'P', DENSE_CLAY_PLATE, 'G', DENSE_CLAY_GEAR, 'C', CLAY_ENERGY_EXCITOR, 'B', BASIC_CIRCUIT, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_bending_machine", CLAY_BENDING_MACHINE[2].getStackForm(),
                "SCG", "PMP", "SCG", 'S', DENSE_CLAY_SPINDLE, 'C', DENSE_CLAY_CYLINDER, 'G', DENSE_CLAY_GEAR, 'P', DENSE_CLAY_PLATE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_wire_drawing_machine", CLAY_WIRE_DRAWING_MACHINE[2].getStackForm(),
                "GSG", "PMP", "GSG", 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'P', DENSE_CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_cutting_machine", CLAY_CUTTING_MACHINE[2].getStackForm(),
                "PGP", "SMC", "PGP", 'P', DENSE_CLAY_PLATE, 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'C', DENSE_CLAY_CUTTING_HEAD, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_lathe", CLAY_LATHE[2].getStackForm(),
                "PGP", "IMS", "PGP", 'P', DENSE_CLAY_PLATE, 'G', DENSE_CLAY_GEAR, 'I', DENSE_CLAY_STICK, 'S', DENSE_CLAY_SPINDLE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_cobblestone_generator", CLAY_COBBLESTONE_GENERATOR[2].getStackForm(),
                " G ", "PMP", " G ", 'G', DENSE_CLAY_GEAR, 'P', DENSE_CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_condenser", CLAY_CONDENSER[1].getStackForm(),
                "GPG", "PMP", "GPG", 'G', DENSE_CLAY_GEAR, 'P', DENSE_CLAY_PLATE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_grinder", CLAY_GRINDER[1].getStackForm(),
                "PGP", "SMS", "PEP", 'P', DENSE_CLAY_PLATE, 'G', DENSE_CLAY_GRINDING_HEAD, 'S', DENSE_CLAY_SPINDLE, 'E', DENSE_CLAY_GEAR, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_decomposer", CLAY_DECOMPOSER[1].getStackForm(),
                "GSG", "CMC", "GPG", 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'C', CLAY_CIRCUIT, 'P', DENSE_CLAY_PIPE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_milling_machine", CLAY_MILLING_MACHINE[1].getStackForm(),
                "PCP", "SMS", "PGP", 'P', DENSE_CLAY_PLATE, 'C', DENSE_CLAY_CUTTING_HEAD, 'S', DENSE_CLAY_SPINDLE, 'G', DENSE_CLAY_GEAR, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_assembler", CLAY_ASSEMBLER[0].getStackForm(),
                "GCG", "SMS", "GCG", 'G', DENSE_CLAY_GEAR, 'C', SIMPLE_CIRCUIT, 'S', DENSE_CLAY_SPINDLE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_inscriber", CLAY_INSCRIBER[0].getStackForm(),
                "GSG", "CMC", "GCG", 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'C', SIMPLE_CIRCUIT, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
        ModHandler.addShapedRecipe("simple_centrifuge", CLAY_CENTRIFUGE[0].getStackForm(),
                "GSG", "SMS", "GSG", 'G', DENSE_CLAY_GEAR, 'S', DENSE_CLAY_SPINDLE, 'M', CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE));
    }

    private static void registerComponentCraftingRecipes() {
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

        ModHandler.addShapelessRecipe("clay_stick_short_1", CLAY_STICK_SHORT.getStackForm(2),
                CLAY_STICK);
        ModHandler.addShapelessRecipe("clay_stick_short_2", CLAY_STICK_SHORT.getStackForm(),
                CLAY_RING_SMALL);
        ModHandler.addShapelessRecipe("clay_ring", CLAY_RING.getStackForm(),
                CLAY_CYLINDER);
        ModHandler.addShapelessRecipe("clay_ring_small", CLAY_RING_SMALL.getStackForm(),
                CLAY_STICK_SHORT);
        ModHandler.addShapedRecipe("clay_gear", CLAY_GEAR.getStackForm(),
                "SSS", "SRS", "SSS", 'S', CLAY_STICK_SHORT, 'R', CLAY_RING_SMALL);
        ModHandler.addShapelessRecipe("clay_pipe", CLAY_PIPE.getStackForm(),
                CLAY_PLATE);
        ModHandler.addShapelessRecipe("clay_ball_large", CLAY_BALL_LARGE.getStackForm(),
                Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL);
        ModHandler.addShapedRecipe("clay_plate_large", CLAY_PLATE_LARGE.getStackForm(),
                "PPP", "PPP", "PPP", 'P', CLAY_PLATE);
        ModHandler.addShapedRecipe("clay_grinding_head", CLAY_GRINDING_HEAD.getStackForm(),
                "NNN", "NRN", "NNN", 'N', CLAY_NEEDLE, 'R', CLAY_RING);
        ModHandler.addShapedRecipe("clay_bearing", CLAY_BEARING.getStackForm(),
                "CCC", "CRC", "CCC", 'C', Items.CLAY_BALL, 'R', CLAY_RING);
        ModHandler.addShapedRecipe("clay_spindle", CLAY_SPINDLE.getStackForm(),
                "RPR", "SBI", "RPR", 'R', CLAY_RING_SMALL, 'P', CLAY_PLATE, 'S', CLAY_STICK, 'B', CLAY_BEARING, 'I', CLAY_RING);
        ModHandler.addShapedRecipe("clay_cutting_head", CLAY_CUTTING_HEAD.getStackForm(),
                "BBB", "BRB", "BBB", 'B', CLAY_BLADE, 'R', CLAY_RING);
        ModHandler.addShapedRecipe("clay_wheel", CLAY_WHEEL.getStackForm(),
                "PPP", "PRP", "PPP", 'P', CLAY_PLATE, 'R', CLAY_RING);

        ModHandler.addShapedRecipe("clay_circuit", CLAY_CIRCUIT.getStackForm(),
                "SGS", "RCR", "SGS", 'S', DENSE_CLAY_STICK, 'G', DENSE_CLAY_GEAR, 'R', DENSE_CLAY_RING, 'C', CLAY_CIRCUIT_BOARD);
        ModHandler.addShapedRecipe("dense_clay_gear", DENSE_CLAY_GEAR.getStackForm(),
                "SSS", "SRS", "SSS", 'S', DENSE_CLAY_STICK_SHORT, 'R', DENSE_CLAY_RING_SMALL);
        ModHandler.addShapedRecipe("dense_clay_grinding_head", DENSE_CLAY_GRINDING_HEAD.getStackForm(),
                "NNN", "NRN", "NNN", 'N', DENSE_CLAY_NEEDLE, 'R', DENSE_CLAY_RING);
        ModHandler.addShapedRecipe("dense_clay_bearing", DENSE_CLAY_BEARING.getStackForm(),
                "CCC", "CRC", "CCC", 'C', Items.CLAY_BALL, 'R', DENSE_CLAY_RING);
        ModHandler.addShapedRecipe("dense_clay_spindle", DENSE_CLAY_SPINDLE.getStackForm(),
                "RPR", "SBI", "RPR", 'R', DENSE_CLAY_RING_SMALL, 'P', DENSE_CLAY_PLATE, 'S', DENSE_CLAY_STICK, 'B', DENSE_CLAY_BEARING, 'I', DENSE_CLAY_RING);
        ModHandler.addShapedRecipe("dense_clay_cutting_head", DENSE_CLAY_CUTTING_HEAD.getStackForm(),
                "BBB", "BRB", "BBB", 'B', DENSE_CLAY_BLADE, 'R', DENSE_CLAY_RING);
        ModHandler.addShapedRecipe("dense_clay_wheel", DENSE_CLAY_WHEEL.getStackForm(),
                "PPP", "PRP", "PPP", 'P', DENSE_CLAY_PLATE, 'R', DENSE_CLAY_RING);

        ModHandler.addShapedRecipe("simple_circuit", SIMPLE_CIRCUIT.getStackForm(),
                "EEE", "ECE", "EEE", 'E', ENERGIZED_CLAY_DUST, 'C', CLAY_CIRCUIT_BOARD);
    }

    private static void registerMiscCraftingRecipes() {
        //CLAY_COMPRESSION
        ModHandler.addShapedRecipe("dense_clay", COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE),
                "CCC", "CCC", "CCC", 'C', Blocks.CLAY);
        ModHandler.addShapedRecipe("compressed_clay", COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED),
                "CCC", "CCC", "CCC", 'C', COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));
        ModHandler.addShapedRecipe("industrial_clay", COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.INDUSTRIAL),
                "CCC", "CCC", "CCC", 'C', COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED));

        //MACHINE_HULL
        ModHandler.addShapedRecipe("raw_clay_machine_hull", CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.RAW_CLAY),
                "PPP", "PGP", "PPP", 'G', CLAY_GEAR, 'P', CLAY_PLATE_LARGE);
        ModHandler.addShapedRecipe("dense_clay_machine_hull", CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.DENSE_CLAY),
                "PPP", "PCP", "PPP", 'C', CLAY_CIRCUIT, 'P', DENSE_CLAY_PLATE_LARGE);
        ModHandler.addShapedRecipe("simple_machine_hull", CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.SIMPLE),
                "LLL", "LSL", "LLL", 'S', SIMPLE_CIRCUIT, 'L', INDUSTRIAL_CLAY_PLATE_LARGE);

        //CLAY_WORKTABLE
        ModHandler.addShapedRecipe("clay_worktable", CLAY_WORKTABLE.getStackForm(),
                "CC ", "CC ", "   ", 'C', COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));
        //CLAY_CRAFTING_BOARD
        ModHandler.addShapedRecipe("clay_crafting_board", CLAY_CRAFTING_BOARD.getStackForm(),
                "CCC", "   ", "   ", 'C', COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE));
        //CLAY_SHOVEL
        ModHandler.addShapedRecipe("clay_shovel", CLAY_SHOVEL.getStackForm(Clay),
                " P ", " S ", " S ", 'P', CLAY_PLATE, 'S', CLAY_STICK);
        //CLAY_ROLLING_PIN_RAW
        ModHandler.addShapedRecipe("clay_rolling_pin_raw", CLAY_ROLLING_PIN_RAW.getStackForm(),
                "SCS", "   ", "   ", 'S', CLAY_STICK_SHORT, 'C', CLAY_CYLINDER);
        //CLAY_SPATULA_RAW
        ModHandler.addShapedRecipe("clay_spatula_raw", CLAY_SPATULA_RAW.getStackForm(),
                "SB ", "   ", "   ", 'S', CLAY_STICK_SHORT, 'B', CLAY_BLADE);
        //CLAY_PICKAXE
        ModHandler.addShapedRecipe("clay_pickaxe", CLAY_PICKAXE.getStackForm(Clay),
                " P ", " S ", " S ", 'P', DENSE_CLAY_PLATE, 'S', DENSE_CLAY_STICK);
    }
}
