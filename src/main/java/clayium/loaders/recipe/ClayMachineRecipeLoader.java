package clayium.loaders.recipe;

import clayium.api.recipes.ClayRecipeMaps;
import clayium.common.blocks.BlockClayOre;
import clayium.common.blocks.BlockCompressedClay;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static clayium.api.ClayValues.microCE;
import static clayium.api.ClayValues.milliCE;
import static clayium.common.blocks.ClayMetaBlocks.CLAY_ORE;
import static clayium.common.blocks.ClayMetaBlocks.COMPRESSED_CLAY;
import static clayium.common.items.ClayMetaItems.*;
import static gregtech.api.unification.material.Materials.*;

public class ClayMachineRecipeLoader {

    private ClayMachineRecipeLoader() {
    }

    public static void Init(){
        registerClayWorkTableRecipes();
        registerclayMinerRecipes();
        registerBendingRecipes();
        registerWireDrawingRecipes();
        registerPipeDrawingRecipes();
        registerCuttingRecipes();
        registerLatheRecipes();
        registerCobblestoneGeneratorRecipes();
        registerMillingMachineRecipes();
        ClayGrinderRecipes.init();
        registerCondenserRecipes();
        registerDecomposerRecipes();
    }

    private static void registerClayWorkTableRecipes(){
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
    }

    private static void registerclayMinerRecipes(){
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

    private static void registerBendingRecipes(){
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.CLAY)
                .output(CLAY_PLATE)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_DISC)
                .output(CLAY_SLICER_RAW)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_BALL_LARGE)
                .output(CLAY_DISC)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Items.CLAY_BALL)
                .output(CLAY_DISC_SMALL)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_PLATE,4)
                .output(CLAY_PLATE_LARGE)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_CYLINDER)
                .output(CLAY_BLADE,2)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE))
                .output(DENSE_CLAY_PLATE)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_CYLINDER)
                .output(DENSE_CLAY_BLADE)
                .duration(8).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_PLATE,4)
                .output(DENSE_CLAY_PLATE_LARGE)
                .duration(4).tier(0).buildAndRegister();
    }

    private static void registerWireDrawingRecipes(){
        ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Items.CLAY_BALL)
                .output(CLAY_STICK)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_DISC_SMALL)
                .output(CLAY_STICK)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_PIPE)
                .output(CLAY_STICK,4)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_CYLINDER)
                .output(CLAY_STICK,8)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_DISC_SMALL)
                .output(DENSE_CLAY_STICK)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_PIPE)
                .output(DENSE_CLAY_STICK,4)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_CYLINDER)
                .output(DENSE_CLAY_STICK,8)
                .duration(6).tier(0).buildAndRegister();
    }

    private static void registerPipeDrawingRecipes(){
        ClayRecipeMaps.CLAY_PIPE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_CYLINDER)
                .output(CLAY_PIPE,2)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_PIPE_DRAWING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_CYLINDER)
                .output(DENSE_CLAY_PIPE,2)
                .duration(6).tier(0).buildAndRegister();
    }

    private static void registerCuttingRecipes(){
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_STICK)
                .output(CLAY_STICK_SHORT,2)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_CYLINDER)
                .output(CLAY_DISC_SMALL,8)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_BALL_LARGE)
                .output(CLAY_DISC)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_PLATE)
                .output(CLAY_DISC_SMALL,4)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_PLATE_LARGE)
                .output(CLAY_DISC,2)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_STICK)
                .output(DENSE_CLAY_STICK_SHORT,2)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_PLATE_LARGE)
                .output(DENSE_CLAY_DISC,2)
                .duration(6).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_CYLINDER)
                .output(DENSE_CLAY_DISC_SMALL,8)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_PLATE)
                .output(DENSE_CLAY_DISC_SMALL,4)
                .duration(6).tier(0).buildAndRegister();
    }

    private static void registerLatheRecipes(){
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Items.CLAY_BALL)
                .output(CLAY_STICK_SHORT)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_DISC_SMALL)
                .output(CLAY_RING_SMALL)
                .duration(1).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_DISC)
                .output(CLAY_RING)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_NEEDLE)
                .output(CLAY_STICK,6)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_CYLINDER)
                .output(CLAY_NEEDLE)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_BALL_LARGE)
                .output(CLAY_CYLINDER)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_NEEDLE)
                .output(DENSE_CLAY_STICK,6)
                .duration(6).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_DISC)
                .output(DENSE_CLAY_RING)
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_DISC_SMALL)
                .output(DENSE_CLAY_RING_SMALL)
                .duration(2).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_CYLINDER)
                .output(DENSE_CLAY_NEEDLE)
                .duration(6).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_LATHE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE,2))
                .output(DENSE_CLAY_CYLINDER)
                .duration(6).tier(0).buildAndRegister();
    }

    private static void registerCobblestoneGeneratorRecipes(){
        ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES.recipeBuilder().CEt(10 * microCE)
                .notConsumable(new ItemStack(Blocks.COBBLESTONE))
                .outputs(new ItemStack(Blocks.COBBLESTONE))
                .duration(50).tier(1).buildAndRegister();
        ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES.recipeBuilder().CEt(10 * microCE)
                .notConsumable(new ItemStack(Blocks.COBBLESTONE))
                .outputs(new ItemStack(Blocks.COBBLESTONE))
                .duration(20).tier(2).buildAndRegister();
        ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES.recipeBuilder().CEt(10 * microCE)
                .notConsumable(new ItemStack(Blocks.COBBLESTONE))
                .outputs(new ItemStack(Blocks.COBBLESTONE))
                .duration(7).tier(3).buildAndRegister();
        ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES.recipeBuilder().CEt(10 * microCE)
                .notConsumable(new ItemStack(Blocks.COBBLESTONE))
                .outputs(new ItemStack(Blocks.COBBLESTONE))
                .duration(2).tier(4).buildAndRegister();
        ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES.recipeBuilder().CEt(10 * microCE)
                .notConsumable(new ItemStack(Blocks.COBBLESTONE))
                .outputs(new ItemStack(Blocks.COBBLESTONE,2))
                .duration(1).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES.recipeBuilder().CEt(10 * microCE)
                .notConsumable(new ItemStack(Blocks.COBBLESTONE))
                .outputs(new ItemStack(Blocks.COBBLESTONE,10))
                .duration(1).tier(6).buildAndRegister();
        ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES.recipeBuilder().CEt(10 * microCE)
                .notConsumable(new ItemStack(Blocks.COBBLESTONE))
                .outputs(new ItemStack(Blocks.COBBLESTONE,64))
                .duration(1).tier(7).buildAndRegister();
    }

    private static void registerMillingMachineRecipes(){
        ClayRecipeMaps.CLAY_MILLING_MACHINE_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_PLATE)
                .output(CLAY_CIRCUIT_BOARD)
                .duration(32).tier(0).buildAndRegister();
    }

    private static void registerCondenserRecipes(){
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_DUST)
                .output(Blocks.CLAY)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.CLAY,9)
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE))
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE,9))
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED))
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_DUST)
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE))
                .duration(6).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(100 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED,9))
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.INDUSTRIAL))
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(1 * milliCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.INDUSTRIAL,9))
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.ADVANCED_INDUTRIAL))
                .duration(4).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(10 * milliCE)
                .input(Blocks.COAL_BLOCK,8)
                .output(Items.DIAMOND)
                .duration(100).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_SHARD_COMPRESSED,4)
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED))
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_SHARD_INDUSTRIAL,4)
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.INDUSTRIAL))
                .duration(6).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_CONDENSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_SHARD_INDUSTRIAL_ADVANCED,4)
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.ADVANCED_INDUTRIAL))
                .duration(9).tier(0).buildAndRegister();
    }

    private static void registerDecomposerRecipes(){
        ClayRecipeMaps.CLAY_DECOMPOSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.CLAY)
                .output(Items.CLAY_BALL,4)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_DECOMPOSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE))
                .output(Blocks.CLAY,9)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_DECOMPOSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED))
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE,9))
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_DECOMPOSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.INDUSTRIAL))
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.COMPRESSED,9))
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_DECOMPOSER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.ADVANCED_INDUTRIAL))
                .outputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.INDUSTRIAL,9))
                .duration(20).tier(0).buildAndRegister();
    }
}
