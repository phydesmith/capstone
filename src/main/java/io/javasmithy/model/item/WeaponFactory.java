package io.javasmithy.model.item;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *  source http://tutorials.jenkov.com/java-json/boon-objectmapper.html
 *  https://attacomsian.com/blog/gson-read-json-file#   <---- this has how to create a map
 *  https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
 */

public class WeaponFactory {
    private static String path = "target\\classes\\data\\weapons.json";
    private static Gson gson = new Gson();
    private static Map<WeaponType, WeaponConfig> weaponConfigMap;
    static {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            WeaponConfig[] weapons = gson.fromJson(reader, WeaponConfig[].class);
            weaponConfigMap = new HashMap<WeaponType, WeaponConfig>();

            for (int i = 0; i < weapons.length; i++){
                weaponConfigMap.put(weapons[i].getWeaponType(), weapons[i] );
            }

        } catch (IOException e){
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

