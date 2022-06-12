package clayium.api.capability;

import clayium.api.ClayValues;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClayValues.MODID)
public class ClayiumCapabilities {

    @CapabilityInject(IClayEnergyContainer.class)
    public static Capability<IClayEnergyContainer> CAPABILITY_CLAY_ENERGY_CONTAINER = null;
}
