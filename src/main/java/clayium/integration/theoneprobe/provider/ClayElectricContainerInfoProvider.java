package clayium.integration.theoneprobe.provider;

import clayium.api.ClayValues;
import clayium.api.capability.ClayiumCapabilities;
import clayium.api.capability.IClayEnergyContainer;
import clayium.api.util.ClayUtility;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.integration.theoneprobe.provider.CapabilityInfoProvider;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class ClayElectricContainerInfoProvider extends CapabilityInfoProvider<IClayEnergyContainer> {

    @Override
    protected Capability<IClayEnergyContainer> getCapability() {
        return ClayiumCapabilities.CAPABILITY_CLAY_ENERGY_CONTAINER;
    }

    @Override
    public String getID() {
        return "clayium:clay_energy_container_provider";
    }

    @Override
    protected boolean allowDisplaying(IClayEnergyContainer capability) {
        return !capability.isOneProbeHidden();
    }

    @Override
    protected void addProbeInfo(IClayEnergyContainer capability, IProbeInfo probeInfo, TileEntity tileEntity, EnumFacing sideHit) {
        long energyStored = capability.getEnergyStored();
        long maxStorage = capability.getEnergyCapacity();
        if (maxStorage == 0) return; //do not add empty max storage progress bar
        IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        String additionalSpacing = tileEntity.hasCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, sideHit) ? "   " : "";
        horizontalPane.text(TextStyleClass.INFO + "{*clayium.top.clay_energy_stored*} " + additionalSpacing);
        horizontalPane.text(ClayUtility.getCEWithUnit(energyStored) + "/" + ClayUtility.getCEWithUnit(maxStorage) + "CE");
    }

}
