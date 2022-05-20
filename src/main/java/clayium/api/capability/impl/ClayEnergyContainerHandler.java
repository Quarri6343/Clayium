package clayium.api.capability.impl;

import clayium.api.capability.IClayEnergyContainer;
import gregtech.api.GTValues;
import gregtech.api.capability.FeCompat;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.ConfigHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ClayEnergyContainerHandler extends MTETrait implements IClayEnergyContainer {

    protected final long maxCapacity;
    protected long energyStored;
    protected final int tier;

    protected long lastEnergyInputPerSec = 0;
    protected long lastEnergyOutputPerSec = 0;
    protected long energyInputPerSec = 0;
    protected long energyOutputPerSec = 0;

    protected long amps = 0;

    public ClayEnergyContainerHandler(MetaTileEntity tileEntity, long maxCapacity, int tier) {
        super(tileEntity);
        this.maxCapacity = maxCapacity;
        this.tier = tier;
    }

    @Override
    public long getInputPerSec() {
        return lastEnergyInputPerSec;
    }

    @Override
    public long getOutputPerSec() {
        return lastEnergyOutputPerSec;
    }

    @Override
    public String getName() {
        return "EnergyContainer";
    }

    @Override
    public int getNetworkID() {
        return TraitNetworkIds.TRAIT_ID_ENERGY_CONTAINER;
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
//        if (capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) {
//            return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(this);
//        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setLong("EnergyStored", energyStored);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        this.energyStored = compound.getLong("EnergyStored");
        notifyEnergyListener(true);
    }

    @Override
    public long getEnergyStored() {
        return this.energyStored;
    }

    public void setEnergyStored(long energyStored) {
        if (energyStored > this.energyStored) {
            energyInputPerSec += energyStored - this.energyStored;
        } else {
            energyOutputPerSec += this.energyStored - energyStored;
        }
        this.energyStored = energyStored;
        if (!metaTileEntity.getWorld().isRemote) {
            metaTileEntity.markDirty();
            notifyEnergyListener(false);
        }
    }

    protected void notifyEnergyListener(boolean isInitialChange) {
//        if (metaTileEntity instanceof IEnergyChangeListener) {
//            ((IEnergyChangeListener) metaTileEntity).onEnergyChanged(this, isInitialChange);
//        }
    }

    public boolean dischargeOrRechargeEnergyContainers(IItemHandlerModifiable itemHandler, int slotIndex) {
        ItemStack stackInSlot = itemHandler.getStackInSlot(slotIndex);
        if (stackInSlot.isEmpty()) { // no stack to charge/discharge
            return false;
        }

        IElectricItem electricItem = stackInSlot.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (electricItem != null) {
            return handleElectricItem(electricItem);
        } else if (ConfigHolder.compat.energy.nativeEUToFE) {
            IEnergyStorage energyStorage = stackInSlot.getCapability(CapabilityEnergy.ENERGY, null);
            if (energyStorage != null) {
                return handleForgeEnergyItem(energyStorage);
            }
        }
        return false;
    }

    private boolean handleElectricItem(IElectricItem electricItem) {
        int machineTier = tier;
        int chargeTier = Math.min(machineTier, electricItem.getTier());
        double chargePercent = getEnergyStored() / (getEnergyCapacity() * 1.0);

        // Check if the item is a battery (or similar), and if we can receive some amount of energy
        if (electricItem.canProvideChargeExternally() && getEnergyCanBeInserted() > 0) {

            // Drain from the battery if we are below half energy capacity, and if the tier matches
            if (chargePercent <= 0.5 && chargeTier == machineTier) {
                long dischargedBy = electricItem.discharge(getEnergyCanBeInserted(), machineTier, false, true, false);
                addEnergy(dischargedBy);
                return dischargedBy > 0L;
            }
        }

        // Else, check if we have above 50% power
        if (chargePercent > 0.5) {
            long chargedBy = electricItem.charge(getEnergyStored(), chargeTier, false, false);
            removeEnergy(chargedBy);
            return chargedBy > 0;
        }
        return false;
    }

    private boolean handleForgeEnergyItem(IEnergyStorage energyStorage) {
        double chargePercent = getEnergyStored() / (getEnergyCapacity() * 1.0);

        if (chargePercent > 0.5) {
            long chargedBy = FeCompat.insertEu(energyStorage, GTValues.V[tier]);
            removeEnergy(chargedBy);
            return chargedBy > 0;
        }
        return false;
    }

    @Override
    public void update() {
        amps = 0;
        if (getMetaTileEntity().getWorld().isRemote)
            return;
        if (metaTileEntity.getOffsetTimer() % 20 == 0) {
            lastEnergyOutputPerSec = energyOutputPerSec;
            lastEnergyInputPerSec = energyInputPerSec;
            energyOutputPerSec = 0;
            energyInputPerSec = 0;
        }
    }

    @Override
    public long getEnergyCapacity() {
        return this.maxCapacity;
    }

    @Override
    public boolean inputsEnergy(EnumFacing side) {
        return false;
    }

    @Override
    public long changeEnergy(long energyToAdd) {
        long oldEnergyStored = getEnergyStored();
        long newEnergyStored = (maxCapacity - oldEnergyStored < energyToAdd) ? maxCapacity : (oldEnergyStored + energyToAdd);
        if (newEnergyStored < 0)
            newEnergyStored = 0;
        setEnergyStored(newEnergyStored);
        return newEnergyStored - oldEnergyStored;
    }

    public interface IEnergyChangeListener {
        void onEnergyChanged(IEnergyContainer container, boolean isInitialChange);
    }
}
