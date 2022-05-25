package clayium.common.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockClayMachineHull extends VariantBlock<BlockClayMachineHull.BlockType> {

    public BlockClayMachineHull() {
        super(Material.ROCK);
        setTranslationKey("clay_machine_hull");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
        setDefaultState(getState(BlockType.RAW_CLAY));
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World player, List<String> tooltip, @Nonnull ITooltipFlag advanced) {
        int ordinal =  getState(stack).ordinal();
        if(ordinal == 0){
            tooltip.add(I18n.format("gui.Common.tier", 1));
        }
        else{
            tooltip.add(I18n.format("gui.Common.tier", ordinal));
        }
    }

    public BlockClayMachineHull.BlockType getVariant(IBlockState blockState) {
        return blockState.getValue(VARIANT);
    }

    public enum BlockType implements IStringSerializable {

        RAW_CLAY("raw_clay"),
        CLAY("clay"),
        DENSE_CLAY("dense_clay"),
        SIMPLE("simple"),
        BASIC("basic"),
        ADVANCED("advanced"),
        PRECISION("precision"),
        CLAY_STEEL("clay_steel"),
        CLAYIUM("clayium"),
        ULTIMATE("ultimate"),
        ANTIMATTER("antimatter"),
        PURE_ANTIMATTER("pure_antimatter"),
        OEC("oec"),
        OPA("opa");

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