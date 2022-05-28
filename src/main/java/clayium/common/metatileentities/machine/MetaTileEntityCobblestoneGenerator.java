package clayium.common.metatileentities.machine;

import clayium.api.capability.IClayEnergyContainer;
import clayium.api.capability.impl.ClayRecipeLogicEnergy;
import clayium.api.metatileentity.ClaySimpleMachineMetaTileEntity;
import clayium.api.recipes.ClayRecipeMap;
import clayium.api.recipes.ClayRecipeMaps;
import clayium.client.ClayTextures;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import java.util.function.Supplier;

public class MetaTileEntityCobblestoneGenerator extends ClaySimpleMachineMetaTileEntity {

    private boolean hasValidFluids;

    public MetaTileEntityCobblestoneGenerator(ResourceLocation metaTileEntityId, ClayRecipeMap<?> recipeMap, ICubeRenderer renderer, int tier) {
        super(metaTileEntityId, recipeMap, renderer, tier, true);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCobblestoneGenerator(metaTileEntityId, ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES, ClayTextures.COBBLESTONE_GENERATOR_OVERLAY, getTier());
    }

    @Override
    protected ClayRecipeLogicEnergy createWorkable(ClayRecipeMap<?> recipeMap) {
        return new MetaTileEntityCobblestoneGenerator.CobblestoneGeneratorRecipeLogic(this, ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES, () -> energyContainer, getTier());
    }

    @Override
    public void onNeighborChanged() {
        super.onNeighborChanged();
        checkAdjacentFluids();
    }

    private void checkAdjacentFluids() {
        if (getWorld() == null || getWorld().isRemote) {
            hasValidFluids = false;
            return;
        }
        boolean hasLava = false;
        boolean hasWater = false;
        for (EnumFacing side : EnumFacing.VALUES) {
            if (hasLava && hasWater) {
                break;
            }

            if (side == frontFacing || side.getAxis().isVertical()) {
                continue;
            }

            Block block = getWorld().getBlockState(getPos().offset(side)).getBlock();
            if (block == Blocks.FLOWING_LAVA || block == Blocks.LAVA) {
                hasLava = true;
            } else if (block == Blocks.FLOWING_WATER || block == Blocks.WATER) {
                hasWater = true;
            }
        }
        this.hasValidFluids = hasLava && hasWater;
    }

    @Override
    public <T> void addNotifiedInput(T input) {
        super.addNotifiedInput(input);
        onNeighborChanged();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("hasValidFluids", hasValidFluids);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (data.hasKey("hasValidFluids")) {
            this.hasValidFluids = data.getBoolean("hasValidFluids");
        }
    }

    protected class CobblestoneGeneratorRecipeLogic extends ClayRecipeLogicEnergy {

        public CobblestoneGeneratorRecipeLogic(MetaTileEntity metaTileEntity, ClayRecipeMap<?> recipeMap, Supplier<IClayEnergyContainer> energyContainer, int tier) {
            super(metaTileEntity, recipeMap, energyContainer, tier);
        }

        @Override
        protected boolean shouldSearchForRecipes() {
            return hasValidFluids && super.shouldSearchForRecipes();
        }
    }
}
