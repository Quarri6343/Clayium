package clayium.loaders.recipe;

import clayium.common.blocks.BlockClayMachineHull;
import gregtech.api.recipes.ModHandler;

import static clayium.common.blocks.ClayMetaBlocks.CLAY_MACHINE_HULL;
import static clayium.common.items.ClayMetaItems.*;

public class ClaySmeltingRecipeLoader {

    public static void Init(){
        //CLAY_MACHINE_HULL
        ModHandler.addSmeltingRecipe(CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.RAW_CLAY), CLAY_MACHINE_HULL.getItemVariant(BlockClayMachineHull.BlockType.CLAY), 0.3f);
        //CLAY_ROLLING_PIN
        ModHandler.addSmeltingRecipe(CLAY_ROLLING_PIN_RAW.getStackForm(), CLAY_ROLLING_PIN.getStackForm(), 0.3f);
        //CLAY_SLICER
        ModHandler.addSmeltingRecipe(CLAY_SLICER_RAW.getStackForm(), CLAY_SLICER.getStackForm(), 0.3f);
        //CLAY_SPATULA
        ModHandler.addSmeltingRecipe(CLAY_SPATULA_RAW.getStackForm(), CLAY_SPATULA.getStackForm(), 0.3f);
    }
}
