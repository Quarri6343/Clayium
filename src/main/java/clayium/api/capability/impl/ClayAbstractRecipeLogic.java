package clayium.api.capability.impl;

import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.ParallelLogicType;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.RecipePropertyStorage;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class ClayAbstractRecipeLogic extends MTETrait implements IWorkable {

    private final RecipeMap<?> recipeMap;

    protected Recipe previousRecipe;

    protected boolean canRecipeProgress = true;

    protected int progressTime;
    protected int maxProgressTime;
    protected int recipeCEt;
    protected List<FluidStack> fluidOutputs;
    protected NonNullList<ItemStack> itemOutputs;

    protected boolean isActive;
    protected boolean workingEnabled = true;
    protected boolean hasNotEnoughEnergy;
    protected boolean wasActiveAndNeedsUpdate;
    protected boolean isOutputsFull;
    protected boolean invalidInputsForRecipes;

    public ClayAbstractRecipeLogic(MetaTileEntity tileEntity, RecipeMap<?> recipeMap) {
        super(tileEntity);
        this.recipeMap = recipeMap;
    }

    public ClayAbstractRecipeLogic(MetaTileEntity tileEntity, RecipeMap<?> recipeMap, boolean hasPerfectOC) {
        super(tileEntity);
        this.recipeMap = recipeMap;
    }

    protected abstract long getEnergyInputPerSecond();

    protected abstract long getEnergyStored();

    protected abstract long getEnergyCapacity();

    protected abstract boolean drawEnergy(int recipeCEt, boolean simulate);

    protected abstract long getMaxVoltage();

    protected IItemHandlerModifiable getInputInventory() {
        return metaTileEntity.getImportItems();
    }

    protected IItemHandlerModifiable getOutputInventory() {
        return metaTileEntity.getExportItems();
    }

    protected IMultipleTankHandler getInputTank() {
        return metaTileEntity.getImportFluids();
    }

    protected IMultipleTankHandler getOutputTank() {
        return metaTileEntity.getExportFluids();
    }

    @Override
    public String getName() {
        return "RecipeMapWorkable";
    }

    @Override
    public int getNetworkID() {
        return TraitNetworkIds.TRAIT_ID_WORKABLE;
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
        if (capability == GregtechTileCapabilities.CAPABILITY_WORKABLE) {
            return GregtechTileCapabilities.CAPABILITY_WORKABLE.cast(this);
        } else if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
//        else if (capability == GregtechTileCapabilities.CAPABILITY_RECIPE_LOGIC) {
//            return ClayiumTileCapabilities.CLAY_CAPABILITY_RECIPE_LOGIC.cast(this);
//        }
        return null;
    }

    @Override
    public void update() {
        World world = getMetaTileEntity().getWorld();
        if (world != null && !world.isRemote) {
            if (workingEnabled) {
                if (getMetaTileEntity().getOffsetTimer() % 20 == 0)
                    this.canRecipeProgress = canProgressRecipe();

                if (progressTime > 0) {
                    updateRecipeProgress();
                }
                //check everything that would make a recipe never start here.
                if (progressTime == 0 && shouldSearchForRecipes()) {
                    trySearchNewRecipe();
                }
            }
            if (wasActiveAndNeedsUpdate) {
                this.wasActiveAndNeedsUpdate = false;
                setActive(false);
            }
        }
    }

    /**
     * DO NOT use the recipeMap field directly, EVER
     *
     * @return the current RecipeMap of the logic
     */
    public RecipeMap<?> getRecipeMap() {
        return this.recipeMap;
    }

    public Recipe getPreviousRecipe() {
        return previousRecipe;
    }

    protected boolean shouldSearchForRecipes() {
        return canWorkWithInputs() && canFitNewOutputs();
    }

    protected boolean hasNotifiedInputs() {
        return (metaTileEntity.getNotifiedItemInputList().size() > 0 ||
                metaTileEntity.getNotifiedFluidInputList().size() > 0);
    }

    protected boolean hasNotifiedOutputs() {
        return (metaTileEntity.getNotifiedItemOutputList().size() > 0 ||
                metaTileEntity.getNotifiedFluidOutputList().size() > 0);
    }

    protected boolean canFitNewOutputs() {
        // if the output is full check if the output changed so we can process recipes results again.
        if (this.isOutputsFull && !hasNotifiedOutputs()) {
            if (!hasNotifiedInputs() && checkPreviousRecipe()) {
                return false;
            }
        }
        this.isOutputsFull = false;
        metaTileEntity.getNotifiedItemOutputList().clear();
        metaTileEntity.getNotifiedFluidOutputList().clear();
        return true;
    }

    protected boolean canWorkWithInputs() {
        // if the inputs were bad last time, check if they've changed before trying to find a new recipe.
        if (this.invalidInputsForRecipes && !hasNotifiedInputs()) return false;
        else {
            this.invalidInputsForRecipes = false;
        }
        return true;
    }

    public void invalidateInputs() {
        this.invalidInputsForRecipes = true;
    }

    public void invalidateOutputs() {
        this.isOutputsFull = true;
    }

    protected void updateRecipeProgress() {
        if (canRecipeProgress && drawEnergy(recipeCEt, true)) {
            drawEnergy(recipeCEt, false);
            //as recipe starts with progress on 1 this has to be > only not => to compensate for it
            if (++progressTime > maxProgressTime) {
                completeRecipe();
            }
            if (this.hasNotEnoughEnergy && getEnergyInputPerSecond() > 19L * recipeCEt) {
                this.hasNotEnoughEnergy = false;
            }
        } else if (recipeCEt > 0) {
            //only set hasNotEnoughEnergy if this recipe is consuming recipe
            //generators always have enough energy
            this.hasNotEnoughEnergy = true;
            //if current progress value is greater than 2, decrement it by 2
            if (progressTime >= 2) {
                if (ConfigHolder.machines.recipeProgressLowEnergy) {
                    this.progressTime = 1;
                } else {
                    this.progressTime = Math.max(1, progressTime - 2);
                }
            }
        }
    }

    /**
     *
     * @return {@code true} if the recipe can progress, else false
     */
    protected boolean canProgressRecipe() {
        return true;
    }

    /**
     * used to force the workable to search for new recipes
     * use sparingly
     */
    public void forceRecipeRecheck() {
        trySearchNewRecipe();
    }

    protected void trySearchNewRecipe() {
        long maxVoltage = getMaxVoltage();
        Recipe currentRecipe;
        IItemHandlerModifiable importInventory = getInputInventory();
        IMultipleTankHandler importFluids = getInputTank();

        // see if the last recipe we used still works
        if (checkPreviousRecipe())
            currentRecipe = this.previousRecipe;
            // If there is no active recipe, then we need to find one.
        else {
            currentRecipe = findRecipe(maxVoltage, importInventory, importFluids);
        }
        // If a recipe was found, then inputs were valid. Cache found recipe.
        if (currentRecipe != null) {
            this.previousRecipe = currentRecipe;
        }
        this.invalidInputsForRecipes = (currentRecipe == null);

        // proceed if we have a usable recipe.
        if (currentRecipe != null && checkRecipe(currentRecipe)) {
            prepareRecipe(currentRecipe);
        }
        // Inputs have been inspected.
        metaTileEntity.getNotifiedItemInputList().clear();
        metaTileEntity.getNotifiedFluidInputList().clear();
    }

    /**
     * @return true if the previous recipe is valid and can be run again
     */
    protected boolean checkPreviousRecipe() {
        return this.previousRecipe != null && this.previousRecipe.matches(false, getInputInventory(), getInputTank());
    }

    /**
     * checks the recipe before preparing it
     *
     * @param recipe the recipe to check
     * @return true if the recipe is allowed to be used, else false
     */
    protected boolean checkRecipe(Recipe recipe) {
        return true;
    }

    /**
     * prepares the recipe to be run
     * <p>
     * the recipe is attempted to be run in parallel
     * the potentially parallel recipe is then checked to exist
     * if it exists, it is checked whether the recipe is able to be run with the current inputs
     * <p>
     * if the above conditions are met, the recipe is engaged to be run
     *
     * @param recipe the recipe to prepare
     * @return true if the recipe was successfully prepared, else false
     */
    protected boolean prepareRecipe(Recipe recipe) {

        recipe = recipe.trimRecipeOutputs(recipe, getRecipeMap(), metaTileEntity.getItemOutputLimit(), metaTileEntity.getFluidOutputLimit());

        if (recipe != null && setupAndConsumeRecipeInputs(recipe, getInputInventory())) {
            setupRecipe(recipe);
            return true;
        }
        return false;
    }

    public Enum<ParallelLogicType> getParallelLogicType() {
        return ParallelLogicType.MULTIPLY;
    }

    protected int getMinTankCapacity(@Nonnull IMultipleTankHandler tanks) {
        if (tanks.getTanks() == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (IFluidTank fluidTank : tanks.getFluidTanks()) {
            result = Math.min(fluidTank.getCapacity(), result);
        }
        return result;
    }

    protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {

        if(!isRecipeMapValid(getRecipeMap())) {
            return null;
        }

        return getRecipeMap().findRecipe(maxVoltage, inputs, fluidInputs, getMinTankCapacity(getOutputTank()));
    }

    public boolean isRecipeMapValid(RecipeMap<?> recipeMap) {
        return true;
    }

    protected static boolean areItemStacksEqual(ItemStack stackA, ItemStack stackB) {
        return (stackA.isEmpty() && stackB.isEmpty()) ||
                (ItemStack.areItemsEqual(stackA, stackB) &&
                        ItemStack.areItemStackTagsEqual(stackA, stackB));
    }

    /**
     * Determines if the provided recipe is possible to run from the provided inventory, or if there is anything preventing
     * the Recipe from being completed.
     * <p>
     * Will consume the inputs of the Recipe if it is possible to run.
     *
     * @param recipe          - The Recipe that will be consumed from the inputs and ran in the machine
     * @param importInventory - The inventory that the recipe should be consumed from.
     *                        Used mainly for Distinct bus implementation for multiblocks to specify
     *                        a specific bus
     * @return - true if the recipe is successful, false if the recipe is not successful
     */
    protected boolean setupAndConsumeRecipeInputs(Recipe recipe, IItemHandlerModifiable importInventory) {
        if (!hasEnoughPower(new int[]{recipe.getEUt(), recipe.getDuration()})){
            return false;
        }

        IItemHandlerModifiable exportInventory = getOutputInventory();
        IMultipleTankHandler importFluids = getInputTank();
        IMultipleTankHandler exportFluids = getOutputTank();

        // We have already trimmed outputs and chanced outputs at this time
        // Attempt to merge all outputs + chanced outputs into the output bus, to prevent voiding chanced outputs
        if (!metaTileEntity.canVoidRecipeItemOutputs() && !GTTransferUtils.addItemsToItemHandler(exportInventory, true, recipe.getAllItemOutputs())) {
            this.isOutputsFull = true;
            return false;
        }

        // We have already trimmed fluid outputs at this time
        if(!metaTileEntity.canVoidRecipeFluidOutputs() && !GTTransferUtils.addFluidsToFluidHandler(exportFluids, true, recipe.getFluidOutputs())) {
            this.isOutputsFull = true;
            return false;
        }

        this.isOutputsFull = false;
        return recipe.matches(true, importInventory, importFluids);
    }

    protected boolean hasEnoughPower(@Nonnull int[] resultOverclock) {
        // Format of resultOverclock: EU/t, duration
        int totalEUt = resultOverclock[0] * resultOverclock[1];

        //RIP Ternary
        // Power Consumption case
        if (totalEUt >= 0) {
            int capacity;
            // If the total consumed power is greater than half the internal capacity
            if (totalEUt > getEnergyCapacity() / 2) {
                // Only draw 1A of power from the internal buffer to allow for recharging of the internal buffer from
                // external sources
                capacity = resultOverclock[0];
            } else {
                // If the total consumed power is less than half the capacity, just drain the whole thing
                capacity = totalEUt;
            }

            // Return true if we have energy energy stored to progress the recipe, either 1A or the whole amount
            return getEnergyStored() >= capacity;
        }
        // Power Generation case
        else {
            // This is the EU/t generated by the generator
            int power = resultOverclock[0];
            // Return true if we can fit at least 1A of energy into the energy output
            return getEnergyStored() - (long) power <= getEnergyCapacity();
        }
    }

    /**
     * applies standard logic for overclocking, where each overclock modifies energy and duration
     *
     * @param recipeEUt         the EU/t of the recipe to overclock
     * @param maximumVoltage    the maximum voltage the recipe is allowed to be run at
     * @param recipeDuration    the duration of the recipe to overclock
     * @param durationDivisor   the value to divide the duration by for each overclock
     * @param voltageMultiplier the value to multiply the voltage by for each overclock
     * @param maxOverclocks     the maximum amount of overclocks allowed
     * @return an int array of {OverclockedEUt, OverclockedDuration}
     */
    public static int[] standardOverclockingLogic(int recipeEUt, long maximumVoltage, int recipeDuration, double durationDivisor, double voltageMultiplier, int maxOverclocks) {
        int overclockedEUt = recipeEUt;
        double overclockedDuration = recipeDuration;

        while (overclockedEUt * voltageMultiplier <= GTValues.V[GTUtility.getTierByVoltage(maximumVoltage)] && overclockedDuration / durationDivisor > 0 && maxOverclocks > 0) {
            overclockedEUt *= voltageMultiplier;
            overclockedDuration /= durationDivisor;
            maxOverclocks--;
        }
        return new int[]{overclockedEUt, (int) Math.ceil(overclockedDuration)};
    }

    /**
     * sets up the recipe to be run
     *
     * @param recipe the recipe to run
     */
    protected void setupRecipe(Recipe recipe) {
        this.progressTime = 1;
        setMaxProgress(recipe.getDuration());
        this.recipeCEt = recipe.getEUt();
        this.fluidOutputs = GTUtility.copyFluidList(recipe.getAllFluidOutputs(metaTileEntity.getFluidOutputLimit()));
        this.itemOutputs = GTUtility.copyStackList(recipe.getResultItemOutputs(GTUtility.getTierByVoltage(recipeCEt), getRecipeMap()));

        if (this.wasActiveAndNeedsUpdate) {
            this.wasActiveAndNeedsUpdate = false;
        } else {
            this.setActive(true);
        }
    }

    /**
     * completes the recipe which was being run, and performs actions done upon recipe completion
     */
    protected void completeRecipe() {
        GTTransferUtils.addItemsToItemHandler(getOutputInventory(), false, itemOutputs);
        GTTransferUtils.addFluidsToFluidHandler(getOutputTank(), false, fluidOutputs);
        this.progressTime = 0;
        setMaxProgress(0);
        this.recipeCEt = 0;
        this.fluidOutputs = null;
        this.itemOutputs = null;
        this.hasNotEnoughEnergy = false;
        this.wasActiveAndNeedsUpdate = true;
    }

    public double getProgressPercent() {
        return getMaxProgress() == 0 ? 0.0 : getProgress() / (getMaxProgress() * 1.0);
    }

    @Override
    public int getProgress() {
        return progressTime;
    }

    @Override
    public int getMaxProgress() {
        return maxProgressTime;
    }

    public int getRecipeCEt() {
        return recipeCEt;
    }

    /**
     * sets the amount of ticks of running time to finish the recipe
     *
     * @param maxProgress the amount of ticks to set
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgressTime = maxProgress;
        metaTileEntity.markDirty();
    }

    protected void setActive(boolean active) {
        if (this.isActive != active) {
            this.isActive = active;
            metaTileEntity.markDirty();
            World world = metaTileEntity.getWorld();
            if (world != null && !world.isRemote) {
                writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(active));
            }
        }
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        this.workingEnabled = workingEnabled;
        metaTileEntity.markDirty();
        World world = metaTileEntity.getWorld();
        if (world != null && !world.isRemote) {
            writeCustomData(GregtechDataCodes.WORKING_ENABLED, buf -> buf.writeBoolean(workingEnabled));
        }
    }

    public boolean isHasNotEnoughEnergy() {
        return hasNotEnoughEnergy;
    }

    @Override
    public boolean isWorkingEnabled() {
        return workingEnabled;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public boolean isWorking() {
        return isActive && !hasNotEnoughEnergy && workingEnabled;
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            this.isActive = buf.readBoolean();
            getMetaTileEntity().scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.workingEnabled = buf.readBoolean();
            getMetaTileEntity().scheduleRenderUpdate();
        }
    }

    @Override
    public void writeInitialData(@Nonnull PacketBuffer buf) {
        buf.writeBoolean(this.isActive);
        buf.writeBoolean(this.workingEnabled);
    }

    @Override
    public void receiveInitialData(@Nonnull PacketBuffer buf) {
        this.isActive = buf.readBoolean();
        this.workingEnabled = buf.readBoolean();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setBoolean("WorkEnabled", workingEnabled);
        compound.setBoolean("CanRecipeProgress", canRecipeProgress);
        if (progressTime > 0) {
            compound.setInteger("Progress", progressTime);
            compound.setInteger("MaxProgress", maxProgressTime);
            compound.setInteger("RecipeEUt", this.recipeCEt);
            NBTTagList itemOutputsList = new NBTTagList();
            for (ItemStack itemOutput : itemOutputs) {
                itemOutputsList.appendTag(itemOutput.writeToNBT(new NBTTagCompound()));
            }
            NBTTagList fluidOutputsList = new NBTTagList();
            for (FluidStack fluidOutput : fluidOutputs) {
                fluidOutputsList.appendTag(fluidOutput.writeToNBT(new NBTTagCompound()));
            }
            compound.setTag("ItemOutputs", itemOutputsList);
            compound.setTag("FluidOutputs", fluidOutputsList);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(@Nonnull NBTTagCompound compound) {
        this.workingEnabled = compound.getBoolean("WorkEnabled");
        this.canRecipeProgress = compound.getBoolean("CanRecipeProgress");
        this.progressTime = compound.getInteger("Progress");
        this.isActive = false;
        if (progressTime > 0) {
            this.isActive = true;
            this.maxProgressTime = compound.getInteger("MaxProgress");
            this.recipeCEt = compound.getInteger("RecipeEUt");
            NBTTagList itemOutputsList = compound.getTagList("ItemOutputs", Constants.NBT.TAG_COMPOUND);
            this.itemOutputs = NonNullList.create();
            for (int i = 0; i < itemOutputsList.tagCount(); i++) {
                this.itemOutputs.add(new ItemStack(itemOutputsList.getCompoundTagAt(i)));
            }
            NBTTagList fluidOutputsList = compound.getTagList("FluidOutputs", Constants.NBT.TAG_COMPOUND);
            this.fluidOutputs = new ArrayList<>();
            for (int i = 0; i < fluidOutputsList.tagCount(); i++) {
                this.fluidOutputs.add(FluidStack.loadFluidStackFromNBT(fluidOutputsList.getCompoundTagAt(i)));
            }
        }
    }

}
