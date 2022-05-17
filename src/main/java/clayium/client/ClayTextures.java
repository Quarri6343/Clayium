package clayium.client;

import clayium.api.ClayValues;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ClayValues.MODID, value = Side.CLIENT)
public class ClayTextures {

//    public static OrientedOverlayRenderer EXTREME_MIXER_OVERLAY = new OrientedOverlayRenderer("extreme_mixer", FRONT, SIDE, TOP);
//    public static SimpleOverlayRenderer GREENHOUSE_CASING;

    public static void preInit() {
//        SAWMILL_CASING = new SimpleOverlayRenderer("sawmill_casing");
    }
}
