package clayium.integration.theoneprobe.provider;

import clayium.api.ClayValues;
import clayium.api.capability.ClayiumCapabilities;
import clayium.api.capability.IClayEnergyContainer;
import clayium.api.util.ClayUtility;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.integration.theoneprobe.provider.CapabilityInfoProvider;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;

public class ClayElectricContainerInfoProvider extends CapabilityInfoProvider<IClayEnergyContainer> {

    @Override
    public String getID() {
        return GTValues.MODID + ":energy_container_provider";
    }

    @Nonnull
    @Override
    protected Capability<IClayEnergyContainer> getCapability() {
        return ClayiumCapabilities.CAPABILITY_CLAY_ENERGY_CONTAINER;
    }

    @Override
    protected boolean allowDisplaying(@Nonnull IClayEnergyContainer capability) {
        return !capability.isOneProbeHidden();
    }

    @Override
    protected void addProbeInfo(@Nonnull IClayEnergyContainer capability, @Nonnull IProbeInfo probeInfo, EntityPlayer player, @Nonnull TileEntity tileEntity, @Nonnull IProbeHitData data) {
        long maxStorage = capability.getEnergyCapacity();
        if (maxStorage == 0) return; // do not add empty max storage progress bar
        IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        horizontalPane.text(TextStyleClass.INFO + "{*clayium.top.clay_energy_stored*} ");
        horizontalPane.text(ClayUtility.getCEWithUnit(capability.getEnergyStored()) + "/" + ClayUtility.getCEWithUnit(maxStorage) + "CE");
    }

}
