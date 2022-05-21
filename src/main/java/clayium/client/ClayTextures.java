package clayium.client;

import clayium.api.ClayValues;
import gregtech.client.renderer.texture.cube.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.client.renderer.texture.cube.OrientedOverlayRenderer.OverlayFace.*;

@Mod.EventBusSubscriber(modid = ClayValues.MODID, value = Side.CLIENT)
public class ClayTextures {

    //CASINGS
    public static final SimpleSidedCubeRenderer CLAY_MACHINE_HULL_0 = new SimpleSidedCubeRenderer("casings/machinehull-0");
    public static final SimpleSidedCubeRenderer DENSE_CLAY = new SimpleSidedCubeRenderer("casings/dense_clay");

    //OVERLAYS
    public static final OrientedOverlayRenderer CLAY_WORKTABLE_OVERLAY = new OrientedOverlayRenderer("clay_worktable", TOP, BOTTOM, SIDE, FRONT, BACK);
    public static final OrientedOverlayRenderer CLAY_CRAFTING_BOARD_OVERLAY = new OrientedOverlayRenderer("clay_crafting_board", TOP);

    public static void preInit() {

    }
}
