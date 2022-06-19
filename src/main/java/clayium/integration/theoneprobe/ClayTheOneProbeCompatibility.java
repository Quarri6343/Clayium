package clayium.integration.theoneprobe;

import clayium.integration.theoneprobe.provider.ClayElectricContainerInfoProvider;
import gregtech.integration.theoneprobe.provider.*;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class ClayTheOneProbeCompatibility {

    public static void registerCompatibility() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new ClayElectricContainerInfoProvider());
    }
}