package clayium.common;

import clayium.api.ClayValues;
import clayium.api.capability.ClaySimpleCapabilityManager;
import clayium.api.unification.material.ClayMaterials;
import clayium.common.blocks.ClayMetaBlocks;
import clayium.common.items.ClayMetaItems;
import clayium.common.metatileentities.ClayMetaTileEntities;
import clayium.integration.theoneprobe.ClayTheOneProbeCompatibility;
import clayium.loaders.recipe.ClayRecipeManager;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.block.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;

import static clayium.common.blocks.ClayMetaBlocks.*;

@Mod.EventBusSubscriber(modid = ClayValues.MODID)
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ClaySimpleCapabilityManager.init();
        ClayMetaBlocks.init();
        ClayMetaItems.init();
        ClayMetaTileEntities.init();
    }

    public void init(FMLInitializationEvent e) {
        if (Loader.isModLoaded(GTValues.MODID_TOP)) {
            ClayTheOneProbeCompatibility.registerCompatibility();
        }
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(COMPRESSED_CLAY);
        registry.register(CLAY_ORE);
        registry.register(CLAY_MACHINE_HULL);
//        event.getRegistry().register(BLOCK_SAWMILL_CONVEYOR);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(createItemBlock(COMPRESSED_CLAY, VariantItemBlock::new));
        registry.register(createItemBlock(CLAY_ORE, VariantItemBlock::new));
        registry.register(createItemBlock(CLAY_MACHINE_HULL, VariantItemBlock::new));
//        event.getRegistry().register(createItemBlock(BLOCK_SAWMILL_CONVEYOR, ItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return itemBlock;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(GregTechAPI.MaterialEvent event) {
        ClayMaterials.init();
    }

    @SubscribeEvent()
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        // Main recipe registration
        // This is called AFTER GregTech registers recipes, so
        // anything here is safe to call removals in
        ClayRecipeManager.init();
    }
}
