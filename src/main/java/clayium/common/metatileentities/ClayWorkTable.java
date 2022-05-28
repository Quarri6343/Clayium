package clayium.common.metatileentities;

import clayium.api.gui.ClayGuiTextures;
import clayium.api.metatileentity.ManualMetaTileEntity;
import clayium.api.recipes.ClayRecipeMaps;
import clayium.client.ClayTextures;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.AdvancedTextWidget;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ClayWorkTable extends ManualMetaTileEntity {

    private static final int tier = 0;

    public ClayWorkTable(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, ClayRecipeMaps.CLAY_WORKTABLE_RECIPES, ClayTextures.CLAY_WORKTABLE_OVERLAY, tier);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new ClayWorkTable(metaTileEntityId);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        super.createImportItemHandler();
        return new NotifiableItemStackHandler(3, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(2, this, true);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player)
                .slot(this.importItems, 0, 34, 25, GuiTextures.SLOT)
                .slot(this.importItems, 1, 53, 25, GuiTextures.SLOT)
                .slot(this.importItems, 2, 42, 44, GuiTextures.SLOT)
                .progressBar(workableHandler::getProgressPercent, 78, 25, 20, 18,
                        GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL, workableHandler.getRecipeMap())
                .widget(new AdvancedTextWidget(10, 70, this::AddDisplayText, 0xFFFFFF))
                .widget(new ClickButtonWidget(78, 50, 16, 16, "", this::OnWorkButtonClick)
                        .setButtonTexture(ClayGuiTextures.BUTTON_GEAR)
                        .setShouldClientCallback(true))
                .slot(this.exportItems, 0, 107, 25, true, false, GuiTextures.SLOT)
                .slot(this.exportItems, 1, 126, 25, true, false, GuiTextures.SLOT)
                .build(getHolder(), player);
    }
}
