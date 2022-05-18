package clayium.api.metatileentity;

import clayium.api.capability.IClayEnergyContainer;
import clayium.api.capability.impl.ClayEnergyContainerHandler;
import clayium.api.capability.impl.RecipeLogicManual;
import clayium.api.gui.ClayGuiTextures;
import clayium.client.ClayTextures;
import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.capability.impl.FilteredFluidHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ImageWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;


public abstract class ManualMetaTileEntity extends MetaTileEntity {

    protected static final int CE_CAPACITY = 1000;

    protected final ICubeRenderer renderer;
    protected RecipeLogicManual workableHandler;
    protected final IClayEnergyContainer energyContainer;

    public ManualMetaTileEntity(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, int tier) {
        super(metaTileEntityId);
        this.energyContainer = new ClayEnergyContainerHandler(this, CE_CAPACITY, 1000, 1000, 1000, 1000, tier);
        this.workableHandler = new RecipeLogicManual(this,
                recipeMap, energyContainer, tier);
        this.renderer = renderer;
    }

    @Override
    public boolean isActive() {
        return workableHandler.isActive() && workableHandler.isWorkingEnabled();
    }

    @SideOnly(Side.CLIENT)
    protected SimpleSidedCubeRenderer getBaseRenderer() {
        return ClayTextures.CLAY_MACHINE_HULL_0;
    }

    @Override
    public int getDefaultPaintingColor() {
        return 0xFFFFFF;
    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        if (!playerIn.isSneaking()) {
            EnumFacing currentOutputSide = workableHandler.getOutputSide();
            if (currentOutputSide == facing ||
                    getFrontFacing() == facing) return false;
            workableHandler.setOutputSide(facing);
            return true;
        }
        return super.onWrenchClick(playerIn, hand, facing, hitResult);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of(getBaseRenderer().getParticleSprite(), getPaintingColorForRendering());
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        IVertexOperation[] colouredPipeline = ArrayUtils.add(pipeline, new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering())));
        getBaseRenderer().render(renderState, translation, colouredPipeline);
        renderer.renderOrientedState(renderState, translation, pipeline, getFrontFacing(), workableHandler.isActive(), workableHandler.isWorkingEnabled());
        Textures.STEAM_VENT_OVERLAY.renderSided(workableHandler.getOutputSide(), renderState, translation, pipeline);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(1, this, false);
    }

    public ModularUI.Builder createUITemplate(EntityPlayer player) {
        return ModularUI.builder(GuiTextures.BACKGROUND, 176, 166)
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT, 0);
    }

    @Override
    public SoundEvent getSound() {
        return workableHandler.getRecipeMap().getSound();
    }
}
