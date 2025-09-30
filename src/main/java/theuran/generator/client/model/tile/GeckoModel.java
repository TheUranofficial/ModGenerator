package theuran.generator.client.model.tile;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import theuran.generator.ModGenerator;
import theuran.generator.block.tile.GeckoTileEntity;

public class GeckoModel extends AnimatedGeoModel<GeckoTileEntity> {
    @Override
    public ResourceLocation getModelLocation(GeckoTileEntity geckoTileEntity) {
        return new ResourceLocation(ModGenerator.modId, "geo/" + geckoTileEntity.name + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GeckoTileEntity geckoTileEntity) {
        return new ResourceLocation(ModGenerator.modId, "textures/" + geckoTileEntity.name + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GeckoTileEntity geckoTileEntity) {
        return new ResourceLocation(ModGenerator.modId, "animations/" + geckoTileEntity.name + ".animation.json");
    }
}