package clayium.api.metatileentity;

import clayium.api.capability.IClayEnergyContainer;
import clayium.api.capability.impl.ClayEnergyContainerHandler;
import clayium.client.ClayTextures;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.capability.impl.EnergyContainerHandler.IEnergyChangeListener;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Iterator;

public abstract class ClayTieredMetaTileEntity extends MetaTileEntity implements ITieredMetaTileEntity {

    private final int tier;
    protected IClayEnergyContainer energyContainer;

    public ClayTieredMetaTileEntity(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId);
        this.tier = tier;
        reinitializeEnergyContainer();
    }

    protected void reinitializeEnergyContainer() {
        this.energyContainer = new ClayEnergyContainerHandler(this, (long)Math.pow(10, tier) * 1000, tier);
    }

    @Override
    public int getActualComparatorValue() {
        long energyStored = energyContainer.getEnergyStored();
        long energyCapacity = energyContainer.getEnergyCapacity();
        float f = energyCapacity == 0L ? 0.0f : energyStored / (energyCapacity * 1.0f);
        return MathHelper.floor(f * 14.0f) + (energyStored > 0 ? 1 : 0);
    }

    @SideOnly(Side.CLIENT)
    protected SimpleSidedCubeRenderer getBaseRenderer() {
        return ClayTextures.TIER_CASINGS[tier];
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
    }

    /**
     * Tier of machine determines it's input voltage, storage and generation rate
     *
     * @return tier of this machine
     */
    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public String getHarvestTool() {
        return "pickaxe";
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_COVERABLE) {
            return GregtechTileCapabilities.CAPABILITY_COVERABLE.cast(this);
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.getFluidInventory().getTankProperties().length > 0) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.getFluidInventory());
        } else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.getItemInventory().getSlots() > 0) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.getItemInventory());
        } else {
            T capabilityResult = null;
            Iterator var4 = this.mteTraits.iterator();

            while(var4.hasNext()) {
                MTETrait mteTrait = (MTETrait)var4.next();
                capabilityResult = mteTrait.getCapability(capability);
                if (capabilityResult != null) {
                    break;
                }
            }

            if (side != null && capabilityResult instanceof IClayEnergyContainer) {
                IClayEnergyContainer energyContainer = (IClayEnergyContainer)capabilityResult;
                if (!energyContainer.inputsEnergy(side)) {
                    return null;
                }
            }

            return capabilityResult;
        }
    }
}
