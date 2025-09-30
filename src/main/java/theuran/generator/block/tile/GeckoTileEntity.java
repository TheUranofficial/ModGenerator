package theuran.generator.block.tile;

import net.minecraft.tileentity.TileEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GeckoTileEntity extends TileEntity implements IAnimatable {
    public final AnimationFactory factory = new AnimationFactory(this);
    public final String name;

    public GeckoTileEntity(String name) {
        this.name = name;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
