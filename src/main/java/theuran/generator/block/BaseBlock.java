package theuran.generator.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import theuran.generator.ModGenerator;

public class BaseBlock extends Block {
    public BaseBlock(String id, float lightLevel, String tab) {
        super(Material.ROCK);

        this.setCreativeTab(tab == null ? ModGenerator.creativeTabs.get("main") : ModGenerator.creativeTabs.get(tab));
        this.setBlockUnbreakable();
        this.setLightLevel(lightLevel);
        this.setRegistryName(new ResourceLocation(ModGenerator.modId, id));
        this.setUnlocalizedName(ModGenerator.modId + "." + id);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}