package theuran.generator;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import software.bernie.geckolib3.GeckoLib;
import theuran.generator.block.BlockRegister;
import theuran.generator.block.GeckoBlock;
import theuran.generator.block.tile.GeckoTileEntity;
import theuran.generator.client.renderer.tile.GeckoRenderer;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        BlockRegister.blockMap.forEach((id, instance) -> {
            if (instance instanceof GeckoBlock) {
                ClientRegistry.bindTileEntitySpecialRenderer(GeckoTileEntity.class, new GeckoRenderer());
            }
        });
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        GeckoLib.initialize();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
