package clayium.loaders.recipe;

import clayium.api.recipes.ClayRecipeMaps;
import clayium.common.blocks.BlockCompressedClay;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static clayium.api.ClayValues.microCE;
import static clayium.api.ClayValues.milliCE;
import static clayium.common.blocks.ClayMetaBlocks.COMPRESSED_CLAY;
import static clayium.common.items.ClayMetaItems.*;
import static clayium.common.items.ClayMetaItems.CLAY_DUST;
import static gregtech.api.unification.material.Materials.Lapis;

public class ClayGrinderRecipes {

    public static void init(){
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.STONE)
                .output(Blocks.COBBLESTONE)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.CLAY)
                .output(CLAY_DUST)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_PLATE)
                .output(CLAY_DUST)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_STICK,4)
                .output(CLAY_DUST)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_STICK_SHORT,8)
                .output(CLAY_DUST)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_RING_SMALL,8)
                .output(CLAY_DUST)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_BLADE)
                .output(CLAY_DUST)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_DISC_SMALL,4)
                .output(CLAY_DUST)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_PIPE)
                .output(CLAY_DUST)
                .duration(3).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .inputs(COMPRESSED_CLAY.getItemVariant(BlockCompressedClay.BlockType.DENSE))
                .output(DENSE_CLAY_DUST)
                .duration(6).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_NEEDLE)
                .output(CLAY_DUST,2)
                .duration(6).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_CYLINDER)
                .output(CLAY_DUST,2)
                .duration(6).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_DISC,2)
                .output(CLAY_DUST,3)
                .duration(9).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.COBBLESTONE)
                .output(Blocks.GRAVEL)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.COBBLESTONE,4)
                .output(Blocks.GRAVEL,4)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.COBBLESTONE,16)
                .output(Blocks.GRAVEL,16)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(Blocks.COBBLESTONE,64)
                .output(Blocks.GRAVEL,64)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_PLATE_LARGE)
                .output(CLAY_DUST,4)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_SPINDLE)
                .output(CLAY_DUST,4)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_PLATE)
                .output(DENSE_CLAY_DUST)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_STICK,4)
                .output(DENSE_CLAY_DUST)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_STICK_SHORT,8)
                .output(DENSE_CLAY_DUST)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_RING_SMALL,8)
                .output(DENSE_CLAY_DUST)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_BLADE)
                .output(DENSE_CLAY_DUST)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_DISC_SMALL,4)
                .output(DENSE_CLAY_DUST)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_PIPE)
                .output(DENSE_CLAY_DUST)
                .duration(12).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_RING,4)
                .output(CLAY_DUST,5)
                .duration(15).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_BEARING,4)
                .output(CLAY_DUST,5)
                .duration(15).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_NEEDLE)
                .output(DENSE_CLAY_DUST,2)
                .duration(24).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_CYLINDER)
                .output(DENSE_CLAY_DUST,2)
                .duration(24).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_GEAR,8)
                .output(CLAY_DUST,9)
                .duration(27).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_CUTTING_HEAD)
                .output(CLAY_DUST,9)
                .duration(27).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_DISC,2)
                .output(DENSE_CLAY_DUST,3)
                .duration(36).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(CLAY_GRINDING_HEAD)
                .output(CLAY_DUST,16)
                .duration(48).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_PLATE_LARGE)
                .output(DENSE_CLAY_DUST,4)
                .duration(48).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_SPINDLE)
                .output(DENSE_CLAY_DUST,4)
                .duration(48).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_RING,4)
                .output(DENSE_CLAY_DUST,5)
                .duration(60).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_BEARING,4)
                .output(DENSE_CLAY_DUST,5)
                .duration(60).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(100 * microCE)
                .input(Blocks.GRAVEL)
                .output(Blocks.SAND)
                .duration(10).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_GEAR,8)
                .output(DENSE_CLAY_DUST,9)
                .duration(108).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_CUTTING_HEAD)
                .output(DENSE_CLAY_DUST,9)
                .duration(108).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * microCE)
                .input(DENSE_CLAY_GRINDING_HEAD)
                .output(DENSE_CLAY_DUST,16)
                .duration(192).tier(0).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(100 * microCE)
                .input(Blocks.SANDSTONE)
                .output(Blocks.SAND,4)
                .duration(100).tier(3).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(250 * microCE)
                .input(Blocks.LAPIS_ORE)
                .output(OrePrefix.gem,Lapis,16)
                .duration(80).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(250 * microCE)
                .input(Blocks.DIAMOND_ORE)
                .output(Items.DIAMOND,2)
                .duration(80).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(250 * microCE)
                .input(Blocks.REDSTONE_ORE)
                .output(Items.REDSTONE,10)
                .duration(80).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(250 * microCE)
                .input(Blocks.EMERALD_ORE)
                .output(Items.EMERALD,2)
                .duration(80).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(250 * microCE)
                .input(Blocks.QUARTZ_ORE)
                .output(Items.QUARTZ,2)
                .duration(80).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * milliCE)
                .input(Blocks.WOOL)
                .output(Items.STRING,4)
                .duration(100).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * milliCE)
                .input(Blocks.MELON_BLOCK)
                .output(Items.MELON,9)
                .duration(100).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * milliCE)
                .input(Blocks.CARPET)
                .output(Items.STRING,2)
                .duration(100).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * milliCE)
                .input(Items.BONE)
                .outputs(new ItemStack(Items.DYE, 5, 15))
                .duration(100).tier(5).buildAndRegister();
        ClayRecipeMaps.CLAY_GRINDER_RECIPES.recipeBuilder().CEt(10 * milliCE)
                .input(Items.BLAZE_ROD)
                .output(Items.BLAZE_POWDER)
                .duration(100).tier(5).buildAndRegister();
    }
}
