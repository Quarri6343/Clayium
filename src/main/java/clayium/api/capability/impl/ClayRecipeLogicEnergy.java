package clayium.api.capability.impl;

import clayium.api.capability.IClayEnergyContainer;
import clayium.api.recipes.ClayRecipeMap;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;

import java.util.function.Supplier;

public class ClayRecipeLogicEnergy extends ClayAbstractRecipeLogic {

    protected final Supplier<IClayEnergyContainer> energyContainer;
    private final int tier;

    public ClayRecipeLogicEnergy(MetaTileEntity tileEntity, ClayRecipeMap<?> recipeMap, Supplier<IClayEnergyContainer> energyContainer, int tier) {
        super(tileEntity, recipeMap);
        this.energyContainer = energyContainer;
        this.tier = tier;
    }

    @Override
    protected long getEnergyInputPerSecond() {
        return energyContainer.get().getInputPerSec();
    }

    @Override
    protected long getEnergyStored() {
        return energyContainer.get().getEnergyStored();
    }

    @Override
    protected long getEnergyCapacity() {
        return energyContainer.get().getEnergyCapacity();
    }

    @Override
    protected boolean drawEnergy(int recipeCEt, boolean simulate) {
        long resultEnergy = getEnergyStored() - recipeCEt;
        if (resultEnergy >= 0L && resultEnergy <= getEnergyCapacity()) {
            if (!simulate) energyContainer.get().changeEnergy(-recipeCEt);
            return true;
        } else return false;
    }

    @Override
    protected long getMaxTier() {
        return tier;
    }
}
