package clayium.api.recipes;

import clayium.api.recipes.builders.ClaySimpleRecipeBuilder;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.sound.GTSounds;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

//@ZenExpansion("mods.clayium.recipe.ClayRecipeMaps")
//@ZenRegister
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
}
