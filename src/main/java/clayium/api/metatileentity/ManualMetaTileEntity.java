package clayium.api.metatileentity;

import clayium.api.capability.impl.RecipeLogicManual;
import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FilteredFluidHandler;
import gregtech.api.capability.impl.FluidTankList;
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
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

public abstract class ManualMetaTileEntity extends MetaTileEntity {

    protected static final int STEAM_CAPACITY = 16000;

    protected final ICubeRenderer renderer;
    protected RecipeLogicManual workableHandler;
    protected FluidTank steamFluidTank;

    public ManualMetaTileEntity(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, int tier) {
        super(metaTileEntityId);
        this.workableHandler = new RecipeLogicManual(this,
                recipeMap, steamFluidTank, 1.0, tier);
        this.renderer = renderer;
    }

    @Override
    public boolean isActive() {
        return workableHandler.isActive() && workableHandler.isWorkingEnabled();
    }

    @SideOnly(Side.CLIENT)
    protected SimpleSidedCubeRenderer getBaseRenderer() {
        if (isBrickedCasing()) {
            return Textures.STEAM_BRICKED_CASING_BRONZE;
        } else {
            return Textures.STEAM_CASING_BRONZE;
        }
    }

    @Override
    public int getDefaultPaintingColor() {
        return 0xFFFFFF;
    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        if (!playerIn.isSneaking()) {
            EnumFacing currentVentingSide = workableHandler.getOutputSide();
            if (currentVentingSide == facing ||
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

    protected boolean isBrickedCasing() {
        return false;
    }

    @Override
    public FluidTankList createImportFluidHandler() {
        this.steamFluidTank = new FilteredFluidHandler(STEAM_CAPACITY)
                .setFillPredicate(ModHandler::isSteam);
        return new FluidTankList(false, steamFluidTank);
    }

    public ModularUI.Builder createUITemplate(EntityPlayer player) {
        return ModularUI.builder(GuiTextures.BACKGROUND_STEAM.get(false), 176, 166)
                .label(6, 6, getMetaFullName()).shouldColor(false)
                .widget(new ImageWidget(79, 42, 18, 18, GuiTextures.INDICATOR_NO_STEAM.get(false))
                        .setPredicate(() -> workableHandler.isHasNotEnoughEnergy()))
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT_STEAM.get(false), 0);
    }

    @Override
    public SoundEvent getSound() {
        return workableHandler.getRecipeMap().getSound();
    }
}
