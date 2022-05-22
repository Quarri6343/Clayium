package clayium.common.blocks;

import clayium.common.items.ClayMetaItems;
import gregtech.api.GTValues;
import gregtech.api.block.VariantBlock;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.util.GTUtility;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockClayOre extends VariantBlock<BlockClayOre.BlockType> {

    public BlockClayOre() {
        super(Material.IRON);
        setTranslationKey("clay_ore");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
        setDefaultState(getState(BlockType.NORMAL));
    }

    public BlockClayOre.BlockType getVariant(IBlockState blockState) {
        return blockState.getValue(VARIANT);
    }

    public enum BlockType implements IStringSerializable {

        NORMAL("normal"),
        DENSE("dense"),
        LARGE_DENSE("large_dense");

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

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World player, List<String> tooltip, @Nonnull ITooltipFlag advanced) {}

    @Override
    public int quantityDropped(Random random) {
        return 4 + random.nextInt(5) * random.nextInt(4);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0) {
            int i = random.nextInt(fortune + 2) - 1;
            if (i < 0) {
                i = 0;
            }
            return quantityDropped(random) * (i + 1);
        }
        return quantityDropped(random);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        if(state == getState(BlockType.NORMAL))
            return quantityDroppedWithBonus(fortune, random);
        else
            return 1;
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        return (state == getState(BlockType.NORMAL)) ? GTValues.RNG.nextInt(1) : 0;
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.005F);

        if (this.canSilkHarvest(worldIn, pos, state, player) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0)
        {
            java.util.List<ItemStack> items = new java.util.ArrayList<ItemStack>();
            ItemStack itemstack = this.getSilkTouchDrop(state);

            if (!itemstack.isEmpty())
            {
                items.add(itemstack);
            }

            net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, state, 0, 1.0f, true, player);
            for (ItemStack item : items)
            {
                spawnAsEntity(worldIn, pos, item);
            }
        }
        else
        {
            harvesters.set(player);
            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            if (player.getHeldItemMainhand().getItem() == ClayMetaItems.CLAY_SHOVEL.getMetaItem()) {
                i = (i + 1) * 3;
            }
            else if (player.getHeldItemMainhand().getItem() == ClayMetaItems.CLAY_PICKAXE.getMetaItem()) {
                i = (i + 1) * 4;
            }
            this.dropBlockAsItem(worldIn, pos, state, i);
            harvesters.set(null);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if(state == getState(BlockType.NORMAL))
            return Items.CLAY_BALL;
        else
            return super.getItemDropped(state, rand, fortune);
    }
}