package clayium.api.capability.impl;

import clayium.api.capability.IClayEnergyContainer;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;

public class RecipeLogicManual extends ClayAbstractRecipeLogic {

    private final IClayEnergyContainer clayEnergyContainer;
    private final int tier;

    private EnumFacing outputSide;

    public RecipeLogicManual(MetaTileEntity tileEntity, RecipeMap<?> recipeMap, IClayEnergyContainer clayEnergyContainer, int tier) {
        super(tileEntity, recipeMap);
        this.clayEnergyContainer = clayEnergyContainer;
        this.tier = tier;
    }

    @Override
    public void onFrontFacingSet(EnumFacing newFrontFacing) {
        if (outputSide == null) {
            setOutputSide(newFrontFacing.getOpposite());
        }
    }

    public EnumFacing getOutputSide() {
        return outputSide == null ? EnumFacing.SOUTH : outputSide;
    }

    public void setOutputSide(EnumFacing outputSide) {
        this.outputSide = outputSide;
        if (!metaTileEntity.getWorld().isRemote) {
            metaTileEntity.markDirty();
            writeCustomData(GregtechDataCodes.VENTING_SIDE, buf -> buf.writeByte(outputSide.getIndex()));
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.VENTING_SIDE) {
            this.outputSide = EnumFacing.VALUES[buf.readByte()];
            getMetaTileEntity().scheduleRenderUpdate();
        }
    }

    @Override
    public void writeInitialData(@Nonnull PacketBuffer buf) {
        super.writeInitialData(buf);
        buf.writeByte(getOutputSide().getIndex());
    }

    @Override
    public void receiveInitialData(@Nonnull PacketBuffer buf) {
        super.receiveInitialData(buf);
        this.outputSide = EnumFacing.VALUES[buf.readByte()];
    }

    @Override
    public void update() {
        if (getMetaTileEntity().getWorld().isRemote)
            return;
        super.update();
    }

    @Override
    protected boolean checkRecipe(Recipe recipe) {
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
        compound.setInteger("VentingSide", getOutputSide().getIndex());
        return compound;
    }

    @Override
    public void deserializeNBT(@Nonnull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        this.outputSide = EnumFacing.VALUES[compound.getInteger("VentingSide")];
    }
}
