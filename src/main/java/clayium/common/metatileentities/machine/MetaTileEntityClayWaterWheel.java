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
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

    private final long baseEnergyPerTick;
    private final int range = 1;

    private boolean isActive = false;
    private boolean isPaused = false;

    private int waterCount;

    public MetaTileEntityClayWaterWheel(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        this.baseEnergyPerTick = tier * 8L;
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
    public void onNeighborChanged() {
        super.onNeighborChanged();
        checkNearbyWater();
    }

    @Override
    public <T> void addNotifiedInput(T input) {
        super.addNotifiedInput(input);
        onNeighborChanged();
    }

    @Override
    public void update() {
        super.update();

        if (getOffsetTimer() % 20 == 0) {
            checkNearbyWater();
        }

        if (!getWorld().isRemote) {
            if (isPaused || waterCount == 0) {
                if (isActive) {
                    setActive(false);
                }
                return;
            }
            if (!isActive) {
                setActive(true);
            }
            energyContainer.addEnergy(baseEnergyPerTick * waterCount);

            World world = getWorld();
            BlockPos currentPos = getPos();
            for (EnumFacing neighbourFace : EnumFacing.VALUES) {
                if (energyContainer.getEnergyStored() < baseEnergyPerTick)
                    break;

                TileEntity neighbourTile = world.getTileEntity(currentPos.offset(neighbourFace));
                if (neighbourTile != null) {
                    IClayEnergyContainer container = neighbourTile.getCapability(ClayiumCapabilities.CAPABILITY_CLAY_ENERGY_CONTAINER, neighbourFace.getOpposite());
                    if (container == null || container.getEnergyCanBeInserted() == 0)
                        continue;
                    long addedEnergy = container.addEnergy(baseEnergyPerTick * waterCount);
                    energyContainer.removeEnergy(addedEnergy);
                }
            }
        }
    }

    private void checkNearbyWater() {
        if (getWorld() == null || getWorld().isRemote) {
            waterCount = 0;
            return;
        }

        int waterCount = 0;
        for (BlockPos blockPos : BlockPos.getAllInBox(this.getPos().add(-range, -range, -range), this.getPos().add(range, range, range))) {
            IBlockState blockState = getWorld().getBlockState(blockPos);
            Block block = blockState.getBlock();
            if (block == Blocks.FLOWING_WATER || block == Blocks.WATER) {
                waterCount++;
            }
        }
        this.waterCount = waterCount;
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
        data.setInteger("waterCount", waterCount);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        isPaused = data.getBoolean("isPaused");
        if (data.hasKey("waterCount")) {
            this.waterCount = data.getInteger("waterCount");
        }
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

