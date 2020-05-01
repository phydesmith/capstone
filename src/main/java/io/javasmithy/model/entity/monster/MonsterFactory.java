package io.javasmithy.model.entity.monster;

import com.google.gson.Gson;
import io.javasmithy.model.item.weapons.WeaponFactory;
import io.javasmithy.view.Sprite;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *  source http://tutorials.jenkov.com/java-json/boon-objectmapper.html
 *  https://attacomsian.com/blog/gson-read-json-file#   <---- this has how to create a map
 *  https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
 */

public class MonsterFactory {
    private static URL resource = WeaponFactory.class.getResource("/data/monsters.json");
    private static Gson gson = new Gson();
    private static Map<MonsterType, MonsterConfig> monsterMap;
    static {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(resource.toURI()));
            MonsterConfig[] monsters = gson.fromJson(reader, MonsterConfig[].class);
            monsterMap = new HashMap<MonsterType, MonsterConfig>();

            for (int i = 0; i < monsters.length; i++){
                monsterMap.put(monsters[i].getMonsterType(), monsters[i] );
            }

        } catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    public static Monster createMonster(MonsterType type){
        /**
         * This method uses the monster map to get a monster template and create a new instance of the monster.
         * @param type The type of monster to create.
         */
        Monster monster = monsterMap.get(type).generate();
        monster.setSprite(new Sprite());
        monster.getSprite().setImage(new Image(MonsterFactory.class.getResource( type.getImgPath() ).toExternalForm()) );
        monster.initHP();
        return monster;
        /**
         * @return a new instance of monster based on the parameter type.
         */
    }
}
