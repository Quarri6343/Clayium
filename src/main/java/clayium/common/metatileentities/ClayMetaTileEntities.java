package clayium.common.metatileentities;


import clayium.api.ClayValues;
import clayium.common.metatileentities.storage.MetaTileEntityClayCraftingBoard;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class ClayMetaTileEntities {

//    public static GTESimpleMachineMetaTileEntity[] EXTREME_MIXER = new GTESimpleMachineMetaTileEntity[1]; //ZPM Only
    public static ClayWorkTable CLAY_WORKTABLE;
    public static MetaTileEntityClayCraftingBoard CLAY_CRAFTING_BOARD;

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

        //MACHINE SECTION
//        EXTREME_MIXER[0]=registerMetaTileEntity(11007,
//        new GTESimpleMachineMetaTileEntity(gteId(String.format("%s.%s", "extreme_mixer", GTValues.VN[7].toLowerCase())), GTERecipeMaps.EXTREME_MIXER_RECIPES, GTETextures.EXTREME_MIXER_OVERLAY, 7, true, GTUtility.hvCappedTankSizeFunction));


        //multiblocks :14000~
//        SAWMILL = registerMetaTileEntity(12001, new MetaTileEntitySawmill(gteId("sawmill")));
    }

    @Nonnull
    private static ResourceLocation clayId(String name) {
        return new ResourceLocation(ClayValues.MODID, name);
    }
}
