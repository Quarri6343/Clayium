package clayium.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.common.blocks.MetaBlocks.statePropertiesToString;

public class ClayMetaBlocks {

    public static BlockCompressedClay COMPRESSED_CRAY;
    public static BlockClayOre CLAY_ORE;
    public static BlockClayMachineHull CLAY_MACHINE_HULL;
//    public static final BlockSawmillConveyor BLOCK_SAWMILL_CONVEYOR = new BlockSawmillConveyor();

    public static void Init(){
        COMPRESSED_CRAY = new BlockCompressedClay();
        COMPRESSED_CRAY.setRegistryName("compressed_clay");
        CLAY_ORE = new BlockClayOre();
        CLAY_ORE.setRegistryName("clay_ore");
        CLAY_MACHINE_HULL = new BlockClayMachineHull();
        CLAY_MACHINE_HULL.setRegistryName("clay_machine_hull");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(COMPRESSED_CRAY);
        registerItemModel(CLAY_ORE);
        registerItemModel(CLAY_MACHINE_HULL);
//        registerItemModel(BLOCK_SAWMILL_CONVEYOR);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }
}
