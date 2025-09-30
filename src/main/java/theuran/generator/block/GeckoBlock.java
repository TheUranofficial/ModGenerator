package theuran.generator.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import theuran.generator.block.tile.GeckoTileEntity;

import javax.annotation.Nullable;

public class GeckoBlock extends ModelBlock implements ITileEntityProvider {
    public GeckoBlock(String id, float lightLevel, String tab, boolean isPassable) {
        super(id, lightLevel, tab, isPassable, false);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        return this.box;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new GeckoTileEntity(this.getRegistryName().getResourcePath());
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}