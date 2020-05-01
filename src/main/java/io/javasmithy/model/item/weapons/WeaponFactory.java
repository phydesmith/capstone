package io.javasmithy.model.item.weapons;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/** Class that loads all weapons from weapons.json into memory in 'weapon configs' that allow weapons to be created
 * @author Peter Hyde-Smith
 */
public class WeaponFactory {
    private static URL resource = WeaponFactory.class.getResource("/data/weapons.json");
    /**
     * GSON instance loads configs from json
     */
    private static Gson gson = new Gson();
    /**
     * Map of types to configs
     */
    private static Map<WeaponType, WeaponConfig> weaponConfigMap;
    static {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(resource.toURI()));
            WeaponConfig[] weapons = gson.fromJson(reader, WeaponConfig[].class);
            weaponConfigMap = new HashMap<WeaponType, WeaponConfig>();

            for (int i = 0; i < weapons.length; i++){
                weaponConfigMap.put(weapons[i].getWeaponType(), weapons[i] );
            }

        } catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    public static Weapon createWeapon(WeaponType type){
        /**
         * This method uses the weapon map to get a weapon config and create a new instance of the weapon.
         * @param type The type of weapon to create.
         */
        Weapon weapon = weaponConfigMap.get(type).generate();
        return weapon;
        /**
         * @return a new instance of weapon based on the parameter type.
         */
    }
}

