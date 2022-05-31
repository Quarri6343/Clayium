package clayium.common.metatileentities;


import clayium.api.ClayValues;
import clayium.api.metatileentity.ClaySimpleMachineMetaTileEntity;
import clayium.api.recipes.ClayRecipeMaps;
import clayium.client.ClayTextures;
import clayium.common.metatileentities.machine.MetaTileEntityCobblestoneGenerator;
import clayium.common.metatileentities.storage.MetaTileEntityClayCraftingBoard;
import gregtech.api.util.GTUtility;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class ClayMetaTileEntities {

//    public static GTESimpleMachineMetaTileEntity[] EXTREME_MIXER = new GTESimpleMachineMetaTileEntity[1]; //ZPM Only
    public static ClayWorkTable CLAY_WORKTABLE;
    public static MetaTileEntityClayCraftingBoard CLAY_CRAFTING_BOARD;
    public static ClayManualMiner CLAY_MANUAL_MINER;

    public static final ClaySimpleMachineMetaTileEntity[] CLAY_BENDING_MACHINE = new ClaySimpleMachineMetaTileEntity[8];
    public static final ClaySimpleMachineMetaTileEntity[] CLAY_WIRE_DRAWING_MACHINE = new ClaySimpleMachineMetaTileEntity[4];
    public static final ClaySimpleMachineMetaTileEntity[] CLAY_PIPE_DRAWING_MACHINE = new ClaySimpleMachineMetaTileEntity[4];
    public static final ClaySimpleMachineMetaTileEntity[] CLAY_CUTTING_MACHINE = new ClaySimpleMachineMetaTileEntity[4];
    public static final ClaySimpleMachineMetaTileEntity[] CLAY_LATHE = new ClaySimpleMachineMetaTileEntity[4];
    public static final ClaySimpleMachineMetaTileEntity[] CLAY_COBBLESTONE_GENERATOR = new ClaySimpleMachineMetaTileEntity[8];

    public static final ClaySimpleMachineMetaTileEntity[] CLAY_MILLING_MACHINE = new ClaySimpleMachineMetaTileEntity[3];
    public static final ClaySimpleMachineMetaTileEntity[] CLAY_GRINDER = new ClaySimpleMachineMetaTileEntity[6];
    public static final ClaySimpleMachineMetaTileEntity[] CLAY_CONDENSER = new ClaySimpleMachineMetaTileEntity[7];
    public static final ClaySimpleMachineMetaTileEntity[] CLAY_DECOMPOSER = new ClaySimpleMachineMetaTileEntity[4];

//    public static MetaTileEntitySawmill SAWMILL;

    public static void init() {
        /*
         * FOR ADDON DEVELOPERS:
         *
         * GTCEu will not take more than 2000 IDs. Anything past ID 1999
         * is considered FAIR GAME, take whatever you like.
         *
         * If you would like to reserve IDs, feel free to reach out to the
         * development team and claim a range of IDs! We will mark any
         * claimed ranges below this comment. Max value is 32767.
         *
         * - Gregicality / Shadows of Greg: 2000-3999
         * - Gregification: 4000-4499
         * - GregTech Food Option: 8500-8999
         * - HtmlTech: 9000-9499
         * - PCM's Ore Addon: 9500-9999
         * - GCM: 10000-10099
         * - MechTech: 10100-10499
         * - MBT 10500 - 10999
         * - CT(MBT) 32000 - ~
         * - FREE RANGE 11000-32767
         */

        //blocks :13001~

        //MANUAL AGE SECTION
        CLAY_WORKTABLE = registerMetaTileEntity(13001, new ClayWorkTable(clayId("clay_worktable")));
        CLAY_CRAFTING_BOARD = registerMetaTileEntity(13002, new MetaTileEntityClayCraftingBoard(clayId("clay_crafting_board")));
        CLAY_MANUAL_MINER = registerMetaTileEntity(13003, new ClayManualMiner(clayId("clay_manual_miner")));

        //MACHINE SECTION
        CLAY_BENDING_MACHINE[0] = registerMetaTileEntity(13011,
        new ClaySimpleMachineMetaTileEntity(clayId("clay_bending_machine.1"), ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES, ClayTextures.BENDING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));
        CLAY_BENDING_MACHINE[1] = registerMetaTileEntity(13012,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_bending_machine.2"), ClayRecipeMaps.CLAY_BENDING_MACHINE_RECIPES, ClayTextures.BENDING_MACHINE_OVERLAY, 2, true, GTUtility.hvCappedTankSizeFunction));

        CLAY_WIRE_DRAWING_MACHINE[0] = registerMetaTileEntity(13021,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_wire_drawing_machine.1"), ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES, ClayTextures.WIRE_DRAWING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));
        CLAY_WIRE_DRAWING_MACHINE[1] = registerMetaTileEntity(13022,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_wire_drawing_machine.2"), ClayRecipeMaps.CLAY_WIRE_DRAWING_MACHINE_RECIPES, ClayTextures.WIRE_DRAWING_MACHINE_OVERLAY, 2, true, GTUtility.hvCappedTankSizeFunction));

        CLAY_PIPE_DRAWING_MACHINE[0] = registerMetaTileEntity(13031,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_pipe_drawing_machine.1"), ClayRecipeMaps.CLAY_PIPE_DRAWING_MACHINE_RECIPES, ClayTextures.PIPE_DRAWING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));
        CLAY_PIPE_DRAWING_MACHINE[1] = registerMetaTileEntity(13032,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_pipe_drawing_machine.2"), ClayRecipeMaps.CLAY_PIPE_DRAWING_MACHINE_RECIPES, ClayTextures.PIPE_DRAWING_MACHINE_OVERLAY, 2, true, GTUtility.hvCappedTankSizeFunction));

        CLAY_CUTTING_MACHINE[0] = registerMetaTileEntity(13041,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_cutting_machine.1"), ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES, ClayTextures.CUTTING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));
        CLAY_CUTTING_MACHINE[1] = registerMetaTileEntity(13042,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_cutting_machine.2"), ClayRecipeMaps.CLAY_CUTTING_MACHINE_RECIPES, ClayTextures.CUTTING_MACHINE_OVERLAY, 2, true, GTUtility.hvCappedTankSizeFunction));

        CLAY_LATHE[0] = registerMetaTileEntity(13051,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_lathe.1"), ClayRecipeMaps.CLAY_LATHE_RECIPES, ClayTextures.LATHE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));
        CLAY_LATHE[1] = registerMetaTileEntity(13052,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_lathe.2"), ClayRecipeMaps.CLAY_LATHE_RECIPES, ClayTextures.LATHE_OVERLAY, 2, true, GTUtility.hvCappedTankSizeFunction));

        CLAY_COBBLESTONE_GENERATOR[0] = registerMetaTileEntity(13061,
                new MetaTileEntityCobblestoneGenerator(clayId("clay_cobblestone_generator.1"), ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES, ClayTextures.COBBLESTONE_GENERATOR_OVERLAY, 1));
        CLAY_COBBLESTONE_GENERATOR[1] = registerMetaTileEntity(13062,
                new MetaTileEntityCobblestoneGenerator(clayId("clay_cobblestone_generator.2"), ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES, ClayTextures.COBBLESTONE_GENERATOR_OVERLAY, 2));

        CLAY_MILLING_MACHINE[0] = registerMetaTileEntity(13071,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_milling_machine.1"), ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES, ClayTextures.MILLING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));

        CLAY_GRINDER[0] = registerMetaTileEntity(13081,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_grinder.1"), ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES, ClayTextures.GRINDER_OVERLAY, 2, true, GTUtility.hvCappedTankSizeFunction));

        CLAY_CONDENSER[0] = registerMetaTileEntity(13091,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_condenser.1"), ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES, ClayTextures.CONDENSER_OVERLAY, 2, true, GTUtility.hvCappedTankSizeFunction));

        CLAY_DECOMPOSER[0] = registerMetaTileEntity(13101,
                new ClaySimpleMachineMetaTileEntity(clayId("clay_decomposer.1"), ClayRecipeMaps.CLAY_COBBLESTONE_GENERATOR_RECIPES, ClayTextures.DECOMPOSER_OVERLAY, 2, true, GTUtility.hvCappedTankSizeFunction));

        //multiblocks :14000~
//        SAWMILL = registerMetaTileEntity(12001, new MetaTileEntitySawmill(gteId("sawmill")));
    }

    @Nonnull
    private static ResourceLocation clayId(String name) {
        return new ResourceLocation(ClayValues.MODID, name);
    }
}
