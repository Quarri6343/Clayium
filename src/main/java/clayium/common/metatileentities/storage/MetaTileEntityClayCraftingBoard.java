package clayium.common.metatileentities.storage;

import clayium.client.ClayTextures;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer;
import gregtech.common.metatileentities.storage.MetaTileEntityWorkbench;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityClayCraftingBoard extends MetaTileEntityWorkbench {

    private static final int tier = 0;

    public MetaTileEntityClayCraftingBoard(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityClayCraftingBoard(this.metaTileEntityId);
    }

    @Override
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of(getBaseRenderer().getParticleSprite(), getDefaultPaintingColor());
    }

    @SideOnly(Side.CLIENT)
    protected SimpleSidedCubeRenderer getBaseRenderer() {
        return ClayTextures.DENSE_CLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        int paintingColor = getPaintingColorForRendering();
        pipeline = ArrayUtils.add(pipeline, new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(paintingColor)));
        getBaseRenderer().render(renderState, translation, pipeline);
        ClayTextures.CLAY_CRAFTING_BOARD_OVERLAY.renderOrientedState(renderState, translation, pipeline, getFrontFacing(), false, false);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gui.Common.tier", tier));
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("clayium.machine.clay_crafting_board.tooltip.1"));
        tooltip.add(I18n.format("clayium.machine.clay_crafting_board.tooltip.2"));
        tooltip.add(I18n.format("clayium.machine.clay_crafting_board.tooltip.3"));
    }
}
