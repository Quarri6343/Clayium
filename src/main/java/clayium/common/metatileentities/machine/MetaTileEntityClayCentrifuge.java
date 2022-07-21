package clayium.common.metatileentities.machine;

import clayium.api.metatileentity.ClaySimpleMachineMetaTileEntity;
import clayium.api.recipes.ClayRecipeMap;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

public class MetaTileEntityClayCentrifuge extends ClaySimpleMachineMetaTileEntity {

    private final int outputAmount;

    public MetaTileEntityClayCentrifuge(ResourceLocation metaTileEntityId, ClayRecipeMap<?> recipeMap, int outputAmount, ICubeRenderer renderer, int tier) {
        super(metaTileEntityId, recipeMap, renderer, tier, true);
        this.outputAmount = outputAmount;
        initializeInventory();
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(outputAmount, this, true);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityClayCentrifuge(metaTileEntityId, workable.getRecipeMap(), outputAmount, renderer, getTier());
    }

    @Override
    public int getItemOutputLimit() {
        return outputAmount;
    }
}
