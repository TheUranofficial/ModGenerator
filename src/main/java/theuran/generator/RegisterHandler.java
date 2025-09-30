package theuran.generator;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import theuran.generator.block.BlockRegister;
import theuran.generator.block.GeckoBlock;
import theuran.generator.item.ItemRegister;

public class RegisterHandler {
    @SubscribeEvent
    public void onBlocksRegister(RegistryEvent.Register<Block> event) {
        BlockRegister.blockMap.forEach((id, instance) -> {
            event.getRegistry().register(instance);
        });
    }

    @SubscribeEvent
    public void onItemsRegister(RegistryEvent.Register<Item> event) {
        BlockRegister.blockMap.forEach((id, instance) -> {
            event.getRegistry().register(new ItemBlock(instance)
                    .setRegistryName(new ResourceLocation(ModGenerator.modId, id))
                    .setUnlocalizedName(ModGenerator.modId + "." + id));
        });
        ItemRegister.itemMap.forEach((id, instance) -> {
            event.getRegistry().register(instance);
        });
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelsRegister(ModelRegistryEvent event) {
        BlockRegister.blockMap.forEach((id, instance) -> {
            if (!(instance instanceof GeckoBlock)) {
                ModelLoader.setCustomModelResourceLocation(
                        Item.getItemFromBlock(instance),
                        0,
                        new ModelResourceLocation(
                                ModGenerator.modId + ":" + id,
                                "inventory"
                        )
                );
            }
        });
        ItemRegister.itemMap.forEach((id, instance) -> {
            ModelLoader.setCustomModelResourceLocation(
                    instance,
                    0,
                    new ModelResourceLocation(
                            ModGenerator.modId + ":" + id,
                            "inventory"
                    )
            );
        });
    }

    static {
        BlockRegister.register();
        ItemRegister.register();
    }
}