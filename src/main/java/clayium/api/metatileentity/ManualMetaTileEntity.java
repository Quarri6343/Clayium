package clayium.api.metatileentity;

import clayium.api.capability.IClayEnergyContainer;
import clayium.api.capability.impl.ClayEnergyContainerHandler;
import clayium.api.capability.impl.RecipeLogicManual;
import clayium.api.gui.ClayModularUI;
import clayium.api.recipes.ClayRecipeMap;
import clayium.api.util.ClayUtility;
import clayium.client.ClayTextures;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.Widget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;

import static clayium.api.ClayValues.microCE;


public abstract class ManualMetaTileEntity extends MetaTileEntity {

    protected static final int CE_CAPACITY = 1000 * microCE;

    protected final ICubeRenderer renderer;
    protected RecipeLogicManual workableHandler;
    protected final IClayEnergyContainer energyContainer;
    private final int tier;

    public ManualMetaTileEntity(ResourceLocation metaTileEntityId, ClayRecipeMap<?> recipeMap, ICubeRenderer renderer, int tier) {
        super(metaTileEntityId);
        this.energyContainer = new ClayEnergyContainerHandler(this, CE_CAPACITY, tier);
        this.workableHandler = new RecipeLogicManual(this,
                recipeMap, energyContainer, tier);
        this.renderer = renderer;
        this.tier = tier;
    }

    @Override
    public boolean isActive() {
        return workableHandler.isActive() && workableHandler.isWorkingEnabled();
    }

    @SideOnly(Side.CLIENT)
    protected SimpleSidedCubeRenderer getBaseRenderer() {
        return ClayTextures.TIER_CASINGS[0];
    }

    @Override
    public int getDefaultPaintingColor() {
        return 0xFFFFFF;
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
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(1, this, false);
    }

    public ClayModularUI.Builder createUITemplate(EntityPlayer player) {
        return ClayModularUI.builder(GuiTextures.BACKGROUND, 176, 166)
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT, 0);
    }

    @Override
    public SoundEvent getSound() {
        return workableHandler.getRecipeMap().getSound();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gui.Common.tier", tier));
        super.addInformation(stack, player, tooltip, advanced);
    }

    public void OnWorkButtonClick(Widget.ClickData data){
        this.energyContainer.addEnergy(50);
    }

    protected void AddDisplayText(List<ITextComponent> textList) {
        textList.add(new TextComponentString(I18n.format("gui.Common.energy", ClayUtility.getCEWithUnit(this.energyContainer.getEnergyStored()))));
    }

    @Override
    public String getHarvestTool() {
        return "pickaxe";
    }
}
