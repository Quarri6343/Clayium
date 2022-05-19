package clayium.common.metatileentities;

import clayium.api.gui.ClayGuiTextures;
import clayium.api.metatileentity.ManualMetaTileEntity;
import clayium.api.recipes.ClayRecipeMap;
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
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
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
                .widget(new ClickButtonWidget(40, 45, 20, 20, "", this::AddEnergy)
                        .setButtonTexture(GuiTextures.BUTTON_ITEM_OUTPUT)
                        .setShouldClientCallback(true))
                .slot(this.exportItems, 0, 144, 24, true, false, GuiTextures.SLOT)
                .build(getHolder(), player);
    }

    protected void AddDisplayText(List<ITextComponent> textList) {
        textList.add(new TextComponentString("Clay Energy:"));
        textList.add(new TextComponentString(Long.toString(this.energyContainer.getEnergyStored())));
    }

    public void AddEnergy(Widget.ClickData data){
        this.energyContainer.addEnergy(10);
    }
}
