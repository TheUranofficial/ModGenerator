package theuran.generator.config;

public class BlockConfig {
    public String type;
    public String tab;
    public float lightLevel;

    @Override
    public String toString() {
        return "Type: " + this.type + ", LightLevel:" + this.lightLevel + ", Tab:" + this.tab;
    }
}