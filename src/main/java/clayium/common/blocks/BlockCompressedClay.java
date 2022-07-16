package clayium.common.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockCompressedClay extends VariantBlock<BlockCompressedClay.BlockType> {

    public BlockCompressedClay() {
        super(Material.IRON);
        setTranslationKey("compressed_clay");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.STONE);
        setHarvestLevel("shovel", 1);
        setDefaultState(getState(BlockType.DENSE));
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World player, List<String> tooltip, @Nonnull ITooltipFlag advanced) {
        tooltip.add(I18n.format("gui.Common.tier", getState(stack).ordinal()));
    }

    public BlockCompressedClay.BlockType getVariant(IBlockState blockState) {
        return blockState.getValue(VARIANT);
    }

    public enum BlockType implements IStringSerializable {

        DENSE("dense"),
        COMPRESSED("compressed"),
        INDUSTRIAL("industrial"),
        ADVANCED_INDUSTRIAL("advanced_industrial");
//        ENERGETIC("energetic"),
//        COMPRESSED_ENERGETIC("compressed_energetic"),
//        DOUBLE_COMPRESSED_ENERGETIC("double_compressed_energetic"),
//        TRIPLE_COMPRESSED_ENERGETIC("triple_compressed_energetic"),
//        QUADRUPLE_COMPRESSED_ENERGETIC("quadruple_compressed_energetic"),
//        QUINTUPLE_COMPRESSED_ENERGETIC("quintuple_compressed_energetic"),
//        SEXTUPLE_COMPRESSED_ENERGETIC("sextuple_compressed_energetic"),
//        SEPTUPLE_COMPRESSED_ENERGETIC("septuple_compressed_energetic"),
//        OCTUPLE_COMPRESSED_ENERGETIC("octuple_compressed_energetic");

        private final String name;

        BlockType(String name) {
            this.name = name;
        }

        @Nonnull
        @Override
        public String getName() {
            return this.name;
        }
    }
}
