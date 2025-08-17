package theuran.generator.client.renderer.tile;

import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import theuran.generator.block.tile.GeckoTileEntity;
import theuran.generator.client.model.tile.GeckoModel;

public class GeckoRenderer extends GeoBlockRenderer<GeckoTileEntity> {
    public GeckoRenderer() {
        super(new GeckoModel());
    }
}
