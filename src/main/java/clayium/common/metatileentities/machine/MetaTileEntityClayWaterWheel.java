package clayium.common.metatileentities.machine;

import clayium.api.capability.ClayiumCapabilities;
import clayium.api.capability.IClayEnergyContainer;
import clayium.api.metatileentity.ClayTieredMetaTileEntity;
import clayium.client.ClayTextures;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static gregtech.api.capability.GregtechDataCodes.IS_WORKING;

public class MetaTileEntityClayWaterWheel extends ClayTieredMetaTileEntity {

    private final long energyPerTick;

    private boolean isActive = false;
    private boolean isPaused = false;

    public MetaTileEntityClayWaterWheel(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        this.energyPerTick = tier * 8L;
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityClayWaterWheel(metaTileEntityId, getTier());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.machine.world_accelerator.description"));
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote) {
            if (isPaused) {
                if (isActive) {
                    setActive(false);
                }
                return;
            }
            if (!isActive) {
                setActive(true);
            }
            energyContainer.addEnergy(energyPerTick);

            World world = getWorld();
            BlockPos currentPos = getPos();
            for (EnumFacing neighbourFace : EnumFacing.VALUES) {
                TileEntity neighbourTile = world.getTileEntity(currentPos.offset(neighbourFace));
                if (neighbourTile != null) {
                    IClayEnergyContainer container = neighbourTile.getCapability(ClayiumCapabilities.CAPABILITY_CLAY_ENERGY_CONTAINER, neighbourFace.getOpposite());
                    if (container == null || container.getEnergyCanBeInserted() == 0)
                        continue;
                    long addedEnergy = container.addEnergy(energyPerTick);
                    energyContainer.removeEnergy(addedEnergy);
                }
            }
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        ClayTextures.CLAY_WATER_WHEEL_OVERLAY.renderOrientedState(renderState, translation, pipeline, getFrontFacing(), isActive, isWorkingEnabled());
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("isPaused", isPaused);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        isPaused = data.getBoolean("isPaused");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isPaused);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isPaused = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == IS_WORKING) {
            this.isActive = buf.readBoolean();
            scheduleRenderUpdate();
        }
    }

    protected void setActive(boolean active) {
        this.isActive = active;
        markDirty();
        if (!getWorld().isRemote) {
            writeCustomData(IS_WORKING, buf -> buf.writeBoolean(active));
        }
    }

    public boolean isWorkingEnabled() {
        return !isPaused;
    }

    public void setWorkingEnabled(boolean b) {
        isPaused = !b;
        notifyBlockUpdate();
    }

//    @Override
//    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
//        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
//            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
//        }
//        return super.getCapability(capability, side);
//    }
}

