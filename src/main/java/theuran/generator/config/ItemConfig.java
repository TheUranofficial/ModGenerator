package theuran.generator.config;

public class ItemConfig {
    public String tab;
    public int maxStackSize;

    @Override
    public String toString() {
        return "Tab: " + this.tab +
                ", MaxStackSize: " + this.maxStackSize;
    }
}
