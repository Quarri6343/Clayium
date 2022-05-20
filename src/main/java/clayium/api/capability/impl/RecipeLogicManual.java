package clayium.api.capability.impl;

import clayium.api.capability.IClayEnergyContainer;
import clayium.api.recipes.ClayRecipe;
import clayium.api.recipes.ClayRecipeMap;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;

public class RecipeLogicManual extends ClayAbstractRecipeLogic {

    private final IClayEnergyContainer clayEnergyContainer;
    private final int tier;

    public RecipeLogicManual(MetaTileEntity tileEntity, ClayRecipeMap<?> recipeMap, IClayEnergyContainer clayEnergyContainer, int tier) {
        super(tileEntity, recipeMap);
        this.clayEnergyContainer = clayEnergyContainer;
        this.tier = tier;
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
    }

    @Override
    public void writeInitialData(@Nonnull PacketBuffer buf) {
        super.writeInitialData(buf);
    }

    @Override
    public void receiveInitialData(@Nonnull PacketBuffer buf) {
        super.receiveInitialData(buf);
    }

    @Override
    public void update() {
        if (getMetaTileEntity().getWorld().isRemote)
            return;
        super.update();
    }

    @Override
    protected boolean checkRecipe(ClayRecipe recipe) {
        return super.checkRecipe(recipe);
    }

    @Override
    protected void completeRecipe() {
        super.completeRecipe();
    }

    @Override
    protected long getEnergyInputPerSecond() {
        return 0;
    }

    @Override
    protected long getEnergyStored() {
        return (long) clayEnergyContainer.getEnergyStored();
    }

    @Override
    protected long getEnergyCapacity() {
        return (long) clayEnergyContainer.getEnergyCapacity();
    }

    @Override
    protected boolean drawEnergy(int recipeCEt, boolean simulate) {
        if(simulate)
            return recipeCEt >= 0 && clayEnergyContainer.getEnergyStored() >= recipeCEt;
        else
            return recipeCEt >= 0 && clayEnergyContainer.getEnergyStored() >= recipeCEt &&
                clayEnergyContainer.removeEnergy(recipeCEt) != 0;
    }

    @Override
    protected long getMaxTier() {
        return tier;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        return compound;
    }

    @Override
    public void deserializeNBT(@Nonnull NBTTagCompound compound) {
        super.deserializeNBT(compound);
    }
}
