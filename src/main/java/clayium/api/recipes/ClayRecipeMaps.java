package clayium.api.recipes;

import clayium.api.recipes.builders.ClaySimpleRecipeBuilder;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.sound.GTSounds;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.clayium.recipe.ClayRecipeMaps")
@ZenRegister
public class ClayRecipeMaps {

//    @ZenProperty
//    public static final RecipeMap<SimpleRecipeBuilder> EXTREME_MIXER_RECIPES = new RecipeMap<>("extreme_mixer", 0, 9, 0, 1, 0, 0, 0, 1, new SimpleRecipeBuilder(), false)
//            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
//            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
//            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
//            .setSound(GTSounds.MIXER);
    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_WORKTABLE_RECIPES = new ClayRecipeMap<>("clay_worktable", 1, 3, 1, 2, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
        .setSound(GTSounds.FORGE_HAMMER);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_MINER_RECIPES = new ClayRecipeMap<>("clay_miner", 1, 1, 1, 4, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.MINER);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_BENDING_MACHINE_RECIPES = new ClayRecipeMap<>("clay_bending_machine", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.BENDER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.MOTOR);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_WIRE_DRAWING_MACHINE_RECIPES = new ClayRecipeMap<>("clay_wire_drawing_machine", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.WIREMILL_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_WIREMILL, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.MOTOR);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_PIPE_DRAWING_MACHINE_RECIPES = new ClayRecipeMap<>("clay_pipe_drawing_machine", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.WIREMILL_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_WIREMILL, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.MOTOR);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_CUTTING_MACHINE_RECIPES = new ClayRecipeMap<>("clay_cutting_machine", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.SAWBLADE_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.CUTTER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.CUT);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_LATHE_RECIPES = new ClayRecipeMap<>("clay_lathe", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.PIPE_OVERLAY_1)
            .setSlotOverlay(true, false, GuiTextures.PIPE_OVERLAY_2)
            .setSpecialTexture(98, 24, 5, 18, GuiTextures.PROGRESS_BAR_LATHE_BASE)
            .setProgressBar(GuiTextures.PROGRESS_BAR_LATHE, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.CUT);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_COBBLESTONE_GENERATOR_RECIPES = new ClayRecipeMap<>("clay_cobblestone_generator", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_MILLING_MACHINE_RECIPES = new ClayRecipeMap<>("clay_milling_machine", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.BENDER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.MOTOR);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_GRINDER_RECIPES = new ClayRecipeMap<>("clay_grinder", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.MOTOR);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_CONDENSER_RECIPES = new ClayRecipeMap<>("clay_condenser", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.COMPRESSOR_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.COMPRESSOR);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_DECOMPOSER_RECIPES = new ClayRecipeMap<>("clay_decomposer", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.BOXED_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_UNPACKER, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.ASSEMBLER);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_ASSEMBLER_RECIPES = new ClayRecipeMap<>("clay_assembler", 1, 2, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.ASSEMBLER);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_CENTRIFUGE_RECIPES = new ClayRecipeMap<>("clay_centrifuge", 1, 1, 1, 4, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, true, GuiTextures.CENTRIFUGE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.CENTRIFUGE);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> ENERGETIC_CLAY_CONDENSER_RECIPES = new ClayRecipeMap<>("energetic_clay_condenser", 1, 1, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.COMPRESSOR_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.COMPRESSOR);

    @ZenProperty
    public static final ClayRecipeMap<ClaySimpleRecipeBuilder> CLAY_INSCRIBER_RECIPES = new ClayRecipeMap<>("clay_inscriber", 1, 2, 1, 1, 0, 0, 0, 0, new ClaySimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSounds.ASSEMBLER);
}