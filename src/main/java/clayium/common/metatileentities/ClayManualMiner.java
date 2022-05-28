package clayium.common.metatileentities;

import clayium.api.gui.ClayGuiTextures;
import clayium.api.metatileentity.ManualMetaTileEntity;
import clayium.api.recipes.ClayRecipeMaps;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.AdvancedTextWidget;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ClayManualMiner extends ManualMetaTileEntity {

    private static final int tier = 0;

    public ClayManualMiner(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, ClayRecipeMaps.CLAY_MINER_RECIPES, Textures.STEAM_MINER_OVERLAY, tier);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new ClayManualMiner(metaTileEntityId);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        super.createImportItemHandler();
        return new NotifiableItemStackHandler(1, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(4, this, true);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player)
                .slot(this.importItems, 0, 53, 34, GuiTextures.SLOT)
                .progressBar(workableHandler::getProgressPercent, 79, 35, 21, 18,
                        GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL, workableHandler.getRecipeMap())
                .widget(new AdvancedTextWidget(10, 70, this::AddDisplayText, 0xFFFFFF))
                .widget(new ClickButtonWidget(78, 50, 16, 16, "", this::OnWorkButtonClick)
                        .setButtonTexture(ClayGuiTextures.BUTTON_GEAR)
                        .setShouldClientCallback(true))
                .slot(exportItems, 0, 107, 25, true, false, GuiTextures.SLOT)
                .slot(exportItems, 1, 125, 25, true, false, GuiTextures.SLOT)
                .slot(exportItems, 2, 107, 43, true, false, GuiTextures.SLOT)
                .slot(exportItems, 3, 125, 43, true, false, GuiTextures.SLOT)
                .build(getHolder(), player);
    }
}
