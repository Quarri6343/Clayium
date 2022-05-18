package clayium.api.capability;

import net.minecraft.util.EnumFacing;

public interface IClayEnergyContainer {

    /**
     * @return if this container accepts energy from the given side
     */
    boolean inputsEnergy(EnumFacing side);

    /**
     * This changes the amount stored.
     * <b>This should only be used internally</b> (f.e. draining while working or filling while generating).
     * For transfer between blocks use {#acceptEnergyFromNetwork(EnumFacing, long, long)}!!!
     *
     * @param differenceAmount amount of energy to add (>0) or remove (<0)
     * @return amount of energy added or removed
     */
    long changeEnergy(long differenceAmount);

    /**
     * Adds specified amount of energy to this energy container
     *
     * @param energyToAdd amount of energy to add
     * @return amount of energy added
     */
    default long addEnergy(long energyToAdd) {
        return changeEnergy(energyToAdd);
    }

    /**
     * Removes specified amount of energy from this energy container
     *
     * @param energyToRemove amount of energy to remove
     * @return amount of energy removed
     */
    default long removeEnergy(long energyToRemove) {
        return changeEnergy(-energyToRemove);
    }

    /**
     * @return the maximum amount of energy that can be inserted
     */
    default long getEnergyCanBeInserted() {
        return getEnergyCapacity() - getEnergyStored();
    }

    /**
     * @return amount of currently stored energy
     */
    long getEnergyStored();

    /**
     * @return maximum amount of storable energy
     */
    long getEnergyCapacity();

    /**
     * @return input eu/s
     */
    default long getInputPerSec() {return 0L;}

    /**
     * @return output eu/s
     */
    default long getOutputPerSec() {return 0L;}

    /**
     * @return true if information like energy capacity should be hidden from TOP.
     * Useful for cables
     */
    default boolean isOneProbeHidden() {
        return false;
    }

    IClayEnergyContainer DEFAULT = new IClayEnergyContainer() {

        @Override
        public boolean inputsEnergy(EnumFacing enumFacing) {
            return false;
        }

        @Override
        public long changeEnergy(long l) {
            return 0;
        }

        @Override
        public long getEnergyStored() {
            return 0;
        }

        @Override
        public long getEnergyCapacity() {
            return 0;
        }
    };
}
