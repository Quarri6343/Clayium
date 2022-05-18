package clayium.client;

import clayium.api.ClayValues;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.client.renderer.texture.cube.OrientedOverlayRenderer.OverlayFace.*;

@Mod.EventBusSubscriber(modid = ClayValues.MODID, value = Side.CLIENT)
public class ClayTextures {

    public static final SimpleSidedCubeRenderer CLAY_MACHINE_HULL_0 = new SimpleSidedCubeRenderer("machinehull-0");

//    public static OrientedOverlayRenderer EXTREME_MIXER_OVERLAY = new OrientedOverlayRenderer("extreme_mixer", FRONT, SIDE, TOP);
    public static final OrientedOverlayRenderer CLAY_WORKTABLE_OVERLAY = new OrientedOverlayRenderer("clayworktable", TOP, BOTTOM, SIDE, FRONT, BACK);

    public static void preInit() {
//
    }
}
