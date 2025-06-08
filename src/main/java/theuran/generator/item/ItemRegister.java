package theuran.generator.item;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import theuran.generator.ModGenerator;
import theuran.generator.config.ItemConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRegister {
    public static Map<String, BaseItem> itemMap = new HashMap<>();

    public static void register() {
        Gson gson = new Gson();

        try {
            List<String> fileNames;

            try (InputStream stream = ItemRegister.class.getClassLoader().getResourceAsStream("assets/" + ModGenerator.MODID + "/entries/extra/itemIndex.json")) {
                InputStreamReader reader = new InputStreamReader(stream);

                fileNames = gson.fromJson(reader, new TypeToken<List<String>>() {}.getType());
                stream.close();
                reader.close();
            }

            for (String fileName : fileNames) {
                try (InputStream stream = ItemRegister.class.getClassLoader().getResourceAsStream("assets/" + ModGenerator.MODID + "/entries/item/" + fileName + ".json")) {
                    InputStreamReader reader = new InputStreamReader(stream);
                    ItemConfig config = gson.fromJson(reader, ItemConfig.class);

                    stream.close();
                    reader.close();

                    register(fileName, config.tab, config.maxStackSize);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void register(String id, String tab, int maxStackSize) {
        itemMap.put(id, new BaseItem(id, tab, maxStackSize));
    }
}
