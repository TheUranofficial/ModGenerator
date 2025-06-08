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
                    .setRegistryName(new ResourceLocation(ModGenerator.MODID, id))
                    .setUnlocalizedName(ModGenerator.MODID + "." + id));
        });
        ItemRegister.itemMap.forEach((id, instance) -> {
            event.getRegistry().register(instance);
        });
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelsRegister(ModelRegistryEvent event) {
        BlockRegister.blockMap.forEach((id, instance) -> {
            ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(instance),
                    0,
                    new ModelResourceLocation(
                            ModGenerator.MODID + ":" + id,
                            "inventory"
                    )
            );
        });
        ItemRegister.itemMap.forEach((id, instance) -> {
            ModelLoader.setCustomModelResourceLocation(
                    instance,
                    0,
                    new ModelResourceLocation(
                            ModGenerator.MODID + ":" + id,
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