package clayium.common.tools;

import gregtech.common.tools.ToolBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Set;

public class ToolClayPickaxe extends ToolBase {

    private static final Set<String> CLAY_PICKAXE_TOOL_CLASSES = Collections.singleton("clay_pickaxe");
    private static final Set<String> PICKAXE_TOOL_CLASSES = Collections.singleton("pickaxe");

    @Override
    public boolean canApplyEnchantment(ItemStack stack, Enchantment enchantment) {
        if(enchantment.type == null) {
            return false;
        }

        return enchantment.type.canEnchantItem(Items.IRON_PICKAXE);
    }

    @Override
    public int getToolDamagePerBlockBreak(ItemStack stack) {
        return 1;
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        return 1.5F;
    }

    @Override
    public float getDigSpeedMultiplier(ItemStack stack) {
        return 1.24F;
    }

    @Override
    public boolean canMineBlock(IBlockState block, ItemStack stack) {
        String tool = block.getBlock().getHarvestTool(block);
        return (tool != null && CLAY_PICKAXE_TOOL_CLASSES.contains(tool)) ||
                PICKAXE_TOOL_CLASSES.contains(tool) ||
                block.getMaterial() == Material.ROCK ||
                block.getMaterial() == Material.IRON ||
                block.getMaterial() == Material.ANVIL ||
                block.getMaterial() == Material.GLASS;
    }

    @Override
    public void onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entity) {
        super.onBlockDestroyed(stack, world, state, pos, entity);
        if (!entity.isSneaking() && entity instanceof EntityPlayer) {
//            ToolUtility.applyTimberAxe(stack, world, pos, (EntityPlayer) entity);
        }
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return CLAY_PICKAXE_TOOL_CLASSES;
    }
}
