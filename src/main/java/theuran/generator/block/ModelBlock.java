package theuran.generator.block;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import theuran.generator.ModGenerator;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ModelBlock extends BaseBlock {
    public AxisAlignedBB box;
    public boolean isPassable;

    public ModelBlock(String id, float lightLevel, String tab, boolean isPassable, boolean hasBox) {
        super(id, lightLevel, tab);

        this.isPassable = isPassable;

        if (hasBox) {
            //opyat spasibo mishormiku za kod nizhe napisanniy
            try {
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream("assets/" + ModGenerator.MODID + "/models/block/" + id + ".json");
                InputStreamReader reader = new InputStreamReader(stream);

                JsonObject json = new JsonParser().parse(reader).getAsJsonObject();

                stream.close();
                reader.close();

                double minX = 16, minY = 16, minZ = 16;
                double maxX = 0, maxY = 0, maxZ = 0;

                for (JsonElement element : json.getAsJsonArray("elements")) {
                    JsonObject cube = element.getAsJsonObject();
                    JsonArray from = cube.getAsJsonArray("from");
                    JsonArray to = cube.getAsJsonArray("to");

                    minX = Math.min(minX, from.get(0).getAsDouble());
                    minY = Math.min(minY, from.get(1).getAsDouble());
                    minZ = Math.min(minZ, from.get(2).getAsDouble());

                    maxX = Math.max(maxX, to.get(0).getAsDouble());
                    maxY = Math.max(maxY, to.get(1).getAsDouble());
                    maxZ = Math.max(maxZ, to.get(2).getAsDouble());
                }

                this.box = new AxisAlignedBB(
                        minX / 16, minY / 16, minZ / 16,
                        maxX / 16, maxY / 16, maxZ / 16
                );
            } catch (Exception e) {
                this.box = FULL_BLOCK_AABB;
            }
        } else {
            this.box = FULL_BLOCK_AABB;
        }

        this.setDefaultState(this.getBlockState().getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BlockHorizontal.FACING);
    }

    @Override
    public boolean isPassable(IBlockAccess access, BlockPos pos) {
        return this.isPassable;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        switch (state.getValue(BlockHorizontal.FACING)) {
            case EAST:
                return new AxisAlignedBB(
                        1 - this.box.maxZ, this.box.minY, this.box.minX,
                        1 - this.box.minZ, this.box.maxY, this.box.maxX
                );
            case SOUTH:
                return new AxisAlignedBB(
                        1 - this.box.maxX, this.box.minY, 1 - this.box.maxZ,
                        1 - this.box.minX, this.box.maxY, 1 - this.box.minZ
                );
            case WEST:
                return new AxisAlignedBB(
                        this.box.minZ, this.box.minY, 1 - this.box.maxX,
                        this.box.maxZ, this.box.maxY, 1 - this.box.minX
                );
            default:
                return this.box;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        return this.isPassable ? NULL_AABB : state.getBoundingBox(access, pos);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
