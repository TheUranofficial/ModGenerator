package theuran.generator.config;

public class BlockConfig {
    public String type;
    public String tab;
    public float lightLevel;
    public boolean hasBox;
    public boolean isPassable;

    @Override
    public String toString() {
        return "Type: " + this.type +
                ", LightLevel:" + this.lightLevel +
                ", Tab:" + this.tab +
                ", HasBox: " + this.hasBox +
                ", IsPassable: " + this.isPassable;
    }
}