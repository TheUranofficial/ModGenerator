package theuran.generator.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import theuran.generator.ModGenerator;

public class BaseItem extends Item {
    public BaseItem(String id, String tab, int maxStackSize) {
        this.setCreativeTab(tab == null ? ModGenerator.creativeTabs.get("main") : ModGenerator.creativeTabs.get(tab));
        this.setMaxStackSize(maxStackSize);
        this.setRegistryName(new ResourceLocation(ModGenerator.modId, id));
        this.setUnlocalizedName(ModGenerator.modId + "." + id);
    }
}
