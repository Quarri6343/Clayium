package clayium.common.metatileentities;

import clayium.api.metatileentity.ManualMetaTileEntity;
import clayium.api.recipes.ClayRecipeMaps;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ClayWorkTable extends ManualMetaTileEntity {

    public ClayWorkTable(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, ClayRecipeMaps.CLAY_WORKTABLE_RECIPES, Textures.ALLOY_SMELTER_OVERLAY);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new ClayWorkTable(metaTileEntityId);
    }

    @Override
    protected boolean isBrickedCasing() {
        return true;
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(1, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(1, this, true);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player)
                .slot(this.importItems, 0, 10, 25, GuiTextures.SLOT_STEAM.get(false))
                .progressBar(workableHandler::getProgressPercent, 39, 26, 100, 16,
                        GuiTextures.PROGRESS_BAR_ARROW_STEAM.get(false), ProgressWidget.MoveType.HORIZONTAL, workableHandler.getRecipeMap())
                .slot(this.exportItems, 0, 147, 25, true, false, GuiTextures.SLOT_STEAM.get(false))
                .build(getHolder(), player);
    }
}
