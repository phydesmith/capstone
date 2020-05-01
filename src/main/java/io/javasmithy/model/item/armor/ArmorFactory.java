package io.javasmithy.model.item.armor;

import com.google.gson.Gson;
import io.javasmithy.model.item.weapons.WeaponFactory;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


/**Class that loads all armors from armors.json into memory in 'armor configs' that allow armors to be created
 * @author Peter Hyde-Smith
 */
public class ArmorFactory {
    private static URL resource = WeaponFactory.class.getResource("/data/armors.json");
    /**
     * GSON instance loads configs from json
     */
    private static Gson gson = new Gson();
    /**
     * Map of types to configs
     */
    private static Map<ArmorType, ArmorConfig> armorConfigMap;
    static {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(resource.toURI()));
            ArmorConfig[] armors = gson.fromJson(reader, ArmorConfig[].class);
            armorConfigMap = new HashMap<ArmorType, ArmorConfig>();

            for (int i = 0; i < armors.length; i++){
                armorConfigMap.put(armors[i].getArmorType(), armors[i] );
            }

        } catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    public static Armor createArmor(ArmorType type){
        /**
         * This method uses the armor map to get an armor config and create a new instance of the armor.
         * @param type The type of armor to create.
         */
        Armor armor = armorConfigMap.get(type).generate();
        return armor;
        /**
         * @return a new instance of armor based on the parameter type.
         */
    }
}


