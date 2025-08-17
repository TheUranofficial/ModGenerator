package theuran.generator.config;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import theuran.generator.ModGenerator;

import java.util.Map;

public class ItemGroupConfig {
    public String id;
    public String icon;
    public boolean isDefaultTab;

    public void addToCreativeTabMap(Map<String, CreativeTabs> map) {
        this.addToCreativeTabMap(map, this.id);
    }

    public void addToCreativeTabMap(Map<String, CreativeTabs> map, String idInMap) {
        map.put(idInMap, new CreativeTabs(ModGenerator.modId + "." + this.id) {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(ItemGroupConfig.this.icon)));
            }
        });
    }

    @Override
    public String toString() {
        return "Id: " + this.id + ", Icon:" + this.icon + ", IsDefaultTab:" + this.isDefaultTab;
    }
}
