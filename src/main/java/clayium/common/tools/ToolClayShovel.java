package clayium.common.tools;

import clayium.common.blocks.ClayMetaBlocks;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.items.behaviors.ShovelBehavior;
import gregtech.common.tools.DamageValues;
import gregtech.common.tools.ToolBase;
import gregtech.common.tools.ToolUtility;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Set;

public class ToolClayShovel extends ToolBase {

    private static final Set<String> CLAY_SHOVEL_TOOL_CLASSES = Collections.singleton("clay_shovel");
    private static final Set<String> SHOVEL_TOOL_CLASSES = Collections.singleton("shovel");

    @Override
    public boolean canApplyEnchantment(ItemStack stack, Enchantment enchantment) {
        if(enchantment.type == null) {
            return false;
        }

        return enchantment.type.canEnchantItem(Items.IRON_SHOVEL);
    }

    @Override
    public int getToolDamagePerBlockBreak(ItemStack stack) {
        return 1;
    }

    @Override
    public int getToolDamagePerContainerCraft(ItemStack stack) {
        return 1;
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        return 1.5F;
    }

    @Override
    public float getDigSpeedMultiplier(ItemStack stack) {
        return 0.44F;
    }

    @Override
    public boolean canMineBlock(IBlockState block, ItemStack stack) {
        String tool = block.getBlock().getHarvestTool(block);
        return (tool != null && CLAY_SHOVEL_TOOL_CLASSES.contains(tool)) ||
                SHOVEL_TOOL_CLASSES.contains(tool) ||
                block.getMaterial() == Material.SAND ||
                block.getMaterial() == Material.GRASS ||
                block.getMaterial() == Material.GROUND ||
                block.getMaterial() == Material.SNOW ||
                block.getMaterial() == Material.CLAY ||
                block.getBlock() == ClayMetaBlocks.CLAY_ORE;
    }

    @Override
    public void onStatsAddedToTool(MetaItem.MetaValueItem item) {
        item.addComponents(new ShovelBehavior(DamageValues.DAMAGE_FOR_SHOVEL));
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return CLAY_SHOVEL_TOOL_CLASSES;
    }
}
