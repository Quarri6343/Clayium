package clayium.common.metatileentities;


import clayium.api.ClayValues;
import clayium.api.metatileentity.ClaySimpleMachineMetaTileEntity;
import clayium.api.recipes.ClayRecipeMaps;
import clayium.client.ClayTextures;
import clayium.common.metatileentities.machine.MetaTileEntityCobblestoneGenerator;
import clayium.common.metatileentities.storage.MetaTileEntityClayCraftingBoard;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.electric.MetaTileEntityRockBreaker;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class ClayMetaTileEntities {

//    public static GTESimpleMachineMetaTileEntity[] EXTREME_MIXER = new GTESimpleMachineMetaTileEntity[1]; //ZPM Only
    public static ClayWorkTable CLAY_WORKTABLE;
    public static MetaTileEntityClayCraftingBoard CLAY_CRAFTING_BOARD;
    public static ClayManualMiner CLAY_MANUAL_MINER;
    public static final ClaySimpleMachineMetaTileEntity[] BENDING_MACHINE = new ClaySimpleMachineMetaTileEntity[8];
    public static final ClaySimpleMachineMetaTileEntity[] WIRE_DRAWING_MACHINE = new ClaySimpleMachineMetaTileEntity[4];
    public static final ClaySimpleMachineMetaTileEntity[] PIPE_DRAWING_MACHINE = new ClaySimpleMachineMetaTileEntity[4];
    public static final ClaySimpleMachineMetaTileEntity[] CUTTING_MACHINE = new ClaySimpleMachineMetaTileEntity[4];
    public static final ClaySimpleMachineMetaTileEntity[] LATHE = new ClaySimpleMachineMetaTileEntity[4];
    public static final ClaySimpleMachineMetaTileEntity[] COBBLESTONE_GENERATOR = new ClaySimpleMachineMetaTileEntity[8];

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
        BENDING_MACHINE[0] = registerMetaTileEntity(13011,
        new ClaySimpleMachineMetaTileEntity(clayId("bending_machine.1"), ClayRecipeMaps.BENDING_MACHINE_RECIPES, ClayTextures.BENDING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));

        WIRE_DRAWING_MACHINE[0] = registerMetaTileEntity(13021,
                new ClaySimpleMachineMetaTileEntity(clayId("wire_drawing_machine.1"), ClayRecipeMaps.WIRE_DRAWING_MACHINE_RECIPES, ClayTextures.WIRE_DRAWING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));

        PIPE_DRAWING_MACHINE[0] = registerMetaTileEntity(13031,
                new ClaySimpleMachineMetaTileEntity(clayId("pipe_drawing_machine.1"), ClayRecipeMaps.PIPE_DRAWING_MACHINE_RECIPES, ClayTextures.PIPE_DRAWING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));

        CUTTING_MACHINE[0] = registerMetaTileEntity(13041,
                new ClaySimpleMachineMetaTileEntity(clayId("cutting_machine.1"), ClayRecipeMaps.CUTTING_MACHINE_RECIPES, ClayTextures.CUTTING_MACHINE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));

        LATHE[0] = registerMetaTileEntity(13051,
                new ClaySimpleMachineMetaTileEntity(clayId("lathe.1"), ClayRecipeMaps.LATHE_RECIPES, ClayTextures.LATHE_OVERLAY, 1, true, GTUtility.hvCappedTankSizeFunction));

        COBBLESTONE_GENERATOR[0] = registerMetaTileEntity(13061,
                new MetaTileEntityCobblestoneGenerator(clayId("cobblestone_generator.1"), ClayRecipeMaps.COBBLESTONE_GENERATOR_RECIPES, ClayTextures.COBBLESTONE_GENERATOR_OVERLAY, 1));

        //multiblocks :14000~
//        SAWMILL = registerMetaTileEntity(12001, new MetaTileEntitySawmill(gteId("sawmill")));
    }

    @Nonnull
    private static ResourceLocation clayId(String name) {
        return new ResourceLocation(ClayValues.MODID, name);
    }
}
