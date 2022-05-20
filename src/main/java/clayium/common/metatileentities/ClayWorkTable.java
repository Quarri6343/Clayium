package clayium.common.metatileentities;

import clayium.api.metatileentity.ManualMetaTileEntity;
import clayium.api.recipes.ClayRecipeMaps;
import clayium.client.ClayTextures;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.AdvancedTextWidget;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.List;

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
        return new NotifiableItemStackHandler(1, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(1, this, true);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player)
                .slot(this.importItems, 0, 20, 24, GuiTextures.SLOT)
                .progressBar(workableHandler::getProgressPercent, 39, 26, 100, 16,
                        GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL, workableHandler.getRecipeMap())
                .widget(new AdvancedTextWidget(100, 45, this::AddDisplayText, 0xFFFFFF))
                .widget(new ClickButtonWidget(40, 45, 20, 20, "", this::OnClick)
                        .setButtonTexture(GuiTextures.BUTTON_ITEM_OUTPUT)
                        .setShouldClientCallback(true))
                .slot(this.exportItems, 0, 144, 24, true, false, GuiTextures.SLOT)
                .build(getHolder(), player);
    }

    protected void AddDisplayText(List<ITextComponent> textList) {
        textList.add(new TextComponentString("Clay Energy:"));
        textList.add(new TextComponentString(Long.toString(this.energyContainer.getEnergyStored())));
    }

    public void OnClick(Widget.ClickData data){
        this.energyContainer.addEnergy(10);
    }
}
