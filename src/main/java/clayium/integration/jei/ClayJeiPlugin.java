package clayium.integration.jei;

import clayium.api.ClayValues;
import clayium.api.capability.impl.ClayAbstractRecipeLogic;
import clayium.api.recipes.ClayRecipe;
import clayium.api.recipes.ClayRecipeMap;
import clayium.common.metatileentities.ClayMetaTileEntities;
import clayium.integration.jei.recipe.ClayRecipeMapCategory;
import clayium.integration.jei.recipe.ClayRecipeWrapper;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IMultipleRecipeMaps;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.machines.RecipeMapFurnace;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.GTJeiPlugin;
import gregtech.integration.jei.GTOreCategory;
import gregtech.integration.jei.multiblock.MultiblockInfoCategory;
import gregtech.integration.jei.recipe.IntCircuitCategory;
import gregtech.integration.jei.recipe.RecipeMapCategory;
import gregtech.integration.jei.recipe.primitive.MaterialTreeCategory;
import gregtech.integration.jei.recipe.primitive.OreByProductCategory;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JEIPlugin
public class ClayJeiPlugin implements IModPlugin {

    public static IJeiRuntime jeiRuntime;
    public static IGuiHelper guiHelper;

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {
        ClayJeiPlugin.jeiRuntime = jeiRuntime;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        guiHelper = registry.getJeiHelpers().getGuiHelper();
        for (ClayRecipeMap<?> recipeMap : ClayRecipeMap.getRecipeMaps()) {
            if (!recipeMap.isHidden) {
                registry.addRecipeCategories(new ClayRecipeMapCategory(recipeMap, registry.getJeiHelpers().getGuiHelper()));
            }
        }
    }

    @Override
    public void register(IModRegistry registry) {
        for (ClayRecipeMap<?> recipeMap : ClayRecipeMap.getRecipeMaps()) {
            if (!recipeMap.isHidden) {
                Stream<ClayRecipe> recipeStream = recipeMap.getRecipeList().stream()
                        .filter(recipe -> !recipe.isHidden() && recipe.hasValidInputsForDisplay());

                if (recipeMap.getSmallRecipeMap() != null) {
                    Collection<ClayRecipe> smallRecipes = recipeMap.getSmallRecipeMap().getRecipeList();
                    recipeStream = recipeStream.filter(recipe -> !smallRecipes.contains(recipe));
                }

                registry.addRecipes(
                        recipeStream.map(r -> new ClayRecipeWrapper(recipeMap, r)).collect(Collectors.toList()),
                        GTValues.MODID + ":" + recipeMap.unlocalizedName
                );
            }
        }

        for (ResourceLocation metaTileEntityId : GregTechAPI.MTE_REGISTRY.getKeys()) {
            MetaTileEntity metaTileEntity = GregTechAPI.MTE_REGISTRY.getObject(metaTileEntityId);
            assert metaTileEntity != null;
            if (metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null) != null) {
                IControllable workableCapability = metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);
                //only registers clay machines with clay recipemaps
                if (workableCapability instanceof ClayAbstractRecipeLogic) {
                    ClayAbstractRecipeLogic logic = (ClayAbstractRecipeLogic) workableCapability;
                    if (metaTileEntity instanceof IMultipleRecipeMaps) {
//                        for (RecipeMap<?> recipeMap : ((IMultipleRecipeMaps) metaTileEntity).getAvailableRecipeMaps()) {
//                            registerRecipeMapCatalyst(registry, recipeMap, metaTileEntity);
//                        }
//                    } else
                    }
                    else if (logic.getRecipeMap() != null) {
                        registerRecipeMapCatalyst(registry, logic.getRecipeMap(), metaTileEntity);
                    }
                }
            }
        }

        for (MetaTileEntity machine : ClayMetaTileEntities.CLAY_COBBLESTONE_GENERATOR) {
            if (machine == null) continue;
            registry.addIngredientInfo(machine.getStackForm(), VanillaTypes.ITEM,
                    "recipemap.clay_cobblestone_generator.jei_description");
        }
    }

    private static void registerRecipeMapCatalyst(IModRegistry registry, ClayRecipeMap<?> recipeMap, MetaTileEntity metaTileEntity) {
        registry.addRecipeCatalyst(metaTileEntity.getStackForm(), GTValues.MODID + ":" + recipeMap.unlocalizedName);
        if (recipeMap.getSmallRecipeMap() != null) {
            registry.addRecipeCatalyst(metaTileEntity.getStackForm(), GTValues.MODID + ":" + recipeMap.getSmallRecipeMap().unlocalizedName);
            return;
        }
        ClayRecipeMapCategory category = ClayRecipeMapCategory.getCategoryMap().get(recipeMap);

        if (category != null) {
            category.setIcon(metaTileEntity.getStackForm());
        }
    }
}
