package clayium.api.capability.impl;

import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IVentable;
import gregtech.api.damagesources.DamageSources;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.ConfigHolder;
import gregtech.common.advancement.GTTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.IFluidTank;

import javax.annotation.Nonnull;

public class RecipeLogicManual extends ClayAbstractRecipeLogic {

    private final IFluidTank steamFluidTank;
    private final double conversionRate; //energy units per steam
    private final int tier;

    private EnumFacing outputSide;

    public RecipeLogicManual(MetaTileEntity tileEntity, RecipeMap<?> recipeMap, IFluidTank steamFluidTank, double conversionRate, int tier) {
        super(tileEntity, recipeMap);
        this.steamFluidTank = steamFluidTank;
        this.conversionRate = conversionRate;
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
        return (long) Math.ceil(steamFluidTank.getFluidAmount() * conversionRate);
    }

    @Override
    protected long getEnergyCapacity() {
        return (long) Math.floor(steamFluidTank.getCapacity() * conversionRate);
    }

    @Override
    protected boolean drawEnergy(int recipeCEt, boolean simulate) {
        int resultDraw = (int) Math.ceil(recipeCEt / conversionRate);
        return resultDraw >= 0 && steamFluidTank.getFluidAmount() >= resultDraw &&
                steamFluidTank.drain(resultDraw, !simulate) != null;
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
