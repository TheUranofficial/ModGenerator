package theuran.generator;

import com.google.gson.Gson;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import theuran.generator.config.ItemGroupConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Mod(modid = ModGenerator.MODID, name = ModGenerator.NAME, version = ModGenerator.VERSION)
public class ModGenerator {
    public static final String MODID = "generator";
    public static final String NAME = "Mod Generator";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "theuran." + MODID + ".ClientProxy", serverSide = "theuran." + MODID + ".CommonProxy")
    public static CommonProxy proxy;

    public static Map<String, CreativeTabs> creativeTabs = new HashMap<>();

    public ModGenerator() {
        MinecraftForge.EVENT_BUS.register(new RegisterHandler());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    static {
        Gson gson = new Gson();
        ItemGroupConfig[] configs;

        try (InputStream stream = ModGenerator.class.getClassLoader().getResourceAsStream("assets/" + MODID + "/entries/extra/itemGroup.json")) {
            if (stream == null) throw new IOException("itemGroup.json not found");
            try (InputStreamReader reader = new InputStreamReader(stream)) {
                configs = gson.fromJson(reader, ItemGroupConfig[].class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (configs.length == 0) {
            throw new RuntimeException("No creative tab configs defined.");
        }

        ItemGroupConfig mainTab = null;
        for (ItemGroupConfig config : configs) {
            if (config.isDefaultTab) {
                mainTab = config;
                break;
            }
        }

        if (mainTab == null) {
            mainTab = configs[0];
        }

        mainTab.addToCreativeTabMap(creativeTabs, "main");

        for (ItemGroupConfig config : configs) {
            if (config != mainTab) {
                config.addToCreativeTabMap(creativeTabs);
            }
        }
    }
}