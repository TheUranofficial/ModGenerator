package theuran.generator.block;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import theuran.generator.ModGenerator;
import theuran.generator.config.BlockConfig;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockRegister {
    public static Map<String, BaseBlock> blockMap = new HashMap<>();

    public static void register() {
        Gson gson = new Gson();

        try {
            List<String> fileNames;

            try (InputStream stream = BlockRegister.class.getClassLoader().getResourceAsStream("assets/" + ModGenerator.modId + "/entries/extra/blockIndex.json")) {
                InputStreamReader reader = new InputStreamReader(stream);

                fileNames = gson.fromJson(reader, new TypeToken<List<String>>() {}.getType());
                stream.close();
                reader.close();
            }

            for (String fileName : fileNames) {
                try (InputStream stream = BlockRegister.class.getClassLoader().getResourceAsStream("assets/" + ModGenerator.modId + "/entries/block/" + fileName + ".json")) {
                    InputStreamReader reader = new InputStreamReader(stream);
                    BlockConfig config = gson.fromJson(reader, BlockConfig.class);

                    stream.close();
                    reader.close();

                    if (config.type.equals("base")) {
                        registerBase(fileName, config.lightLevel, config.tab);
                    } else if (config.type.equals("model")) {
                        registerModel(fileName, config.lightLevel, config.tab, config.isPassable, config.hasBox);
                    } else if (config.type.equals("gecko")) {
                        registerGecko(fileName, config.lightLevel, config.tab, config.isPassable);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerBase(String id, float lightLevel, String tab) {
        blockMap.put(id, new BaseBlock(id, lightLevel, tab));
    }

    private static void registerModel(String id, float lightLevel, String tab, boolean isPassable, boolean hasBox) {
        blockMap.put(id, new ModelBlock(id, lightLevel, tab, isPassable, hasBox));
    }

    private static void registerGecko(String id, float lightLevel, String tab, boolean isPassable) {
        blockMap.put(id, new GeckoBlock(id, lightLevel, tab, isPassable));
    }
}