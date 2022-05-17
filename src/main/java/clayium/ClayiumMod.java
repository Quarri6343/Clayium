package clayium;
import clayium.api.ClayValues;
import clayium.common.CommonProxy;
import gregtech.api.GTValues;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ClayValues.MODID,
        name = "Clayium",
        acceptedMinecraftVersions = "[1.12,1.13)",
        dependencies = GTValues.MOD_VERSION_DEP)
public class ClayiumMod {

    @SidedProxy(modId = ClayValues.MODID, clientSide = "clayium.client.ClientProxy", serverSide = "clayium.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static ClayiumMod instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}