package clayium.client;

import clayium.api.ClayValues;
import gregtech.client.renderer.texture.cube.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.client.renderer.texture.cube.OrientedOverlayRenderer.OverlayFace.*;

@Mod.EventBusSubscriber(modid = ClayValues.MODID, value = Side.CLIENT)
public class ClayTextures {

    //CASINGS
    public static final SimpleSidedCubeRenderer[] TIER_CASINGS = new SimpleSidedCubeRenderer[ClayValues.TN.length];
    public static final SimpleSidedCubeRenderer DENSE_CLAY = new SimpleSidedCubeRenderer("casings/dense_clay");

    //OVERLAYS
    public static final SimpleOverlayRenderer ITEM_OUTPUT_OVERLAY;
    public static final SimpleOverlayRenderer ITEM_AUTO_OUTPUT_OVERLAY;
    public static final OrientedOverlayRenderer CLAY_WORKTABLE_OVERLAY = new OrientedOverlayRenderer("clay_worktable", TOP, BOTTOM, SIDE, FRONT, BACK);
    public static final OrientedOverlayRenderer CLAY_CRAFTING_BOARD_OVERLAY = new OrientedOverlayRenderer("clay_crafting_board", TOP);
    public static final OrientedOverlayRenderer CLAY_BENDING_MACHINE_OVERLAY = new OrientedOverlayRenderer("machines/clay_bending_machine", FRONT);
    public static final OrientedOverlayRenderer CLAY_WIRE_DRAWING_MACHINE_OVERLAY = new OrientedOverlayRenderer("machines/clay_wire_drawing_machine", FRONT);
    public static final OrientedOverlayRenderer CLAY_PIPE_DRAWING_MACHINE_OVERLAY = new OrientedOverlayRenderer("machines/clay_pipe_drawing_machine", FRONT);
    public static final OrientedOverlayRenderer CLAY_CUTTING_MACHINE_OVERLAY = new OrientedOverlayRenderer("machines/clay_cutting_machine", FRONT);
    public static final OrientedOverlayRenderer CLAY_LATHE_OVERLAY = new OrientedOverlayRenderer("machines/clay_lathe", FRONT);
    public static final OrientedOverlayRenderer CLAY_COBBLESTONE_GENERATOR_OVERLAY = new OrientedOverlayRenderer("machines/clay_cobblestone_generator", FRONT);
    public static final OrientedOverlayRenderer CLAY_MILLING_MACHINE_OVERLAY = new OrientedOverlayRenderer("machines/clay_milling_machine", FRONT);
    public static final OrientedOverlayRenderer CLAY_GRINDER_OVERLAY = new OrientedOverlayRenderer("machines/clay_grinder", FRONT);
    public static final OrientedOverlayRenderer CLAY_CONDENSER_OVERLAY = new OrientedOverlayRenderer("machines/clay_condenser", FRONT);
    public static final OrientedOverlayRenderer CLAY_DECOMPOSER_OVERLAY = new OrientedOverlayRenderer("machines/clay_decomposer", FRONT);
    public static final OrientedOverlayRenderer CLAY_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("machines/clay_assembler", FRONT);
    public static final OrientedOverlayRenderer CLAY_CENTRIFUGE_OVERLAY = new OrientedOverlayRenderer("machines/clay_centrifuge", FRONT);
    public static final OrientedOverlayRenderer CLAY_ENERGETIC_CLAY_CONDENSER_OVERLAY = new OrientedOverlayRenderer("machines/energetic_clay_condenser", FRONT);
    public static final OrientedOverlayRenderer CLAY_INSCRIBER_OVERLAY = new OrientedOverlayRenderer("machines/clay_inscriber", FRONT);
    public static final OrientedOverlayRenderer CLAY_WATER_WHEEL_OVERLAY = new OrientedOverlayRenderer("machines/clay_water_wheel", FRONT);

    public static void preInit() {

    }

    static {
        TIER_CASINGS[0] = new SimpleSidedCubeRenderer("casings/rawclaymachinehull_0");
        for (int i = 1; i < 14; i++) {
            TIER_CASINGS[i] = new SimpleSidedCubeRenderer("casings/machinehull_" + i);
        }

        ITEM_OUTPUT_OVERLAY = new SimpleOverlayRenderer("overlay/overlay_item_output");
        ITEM_AUTO_OUTPUT_OVERLAY = new SimpleOverlayRenderer("overlay/overlay_auto_item_output");
    }
}
