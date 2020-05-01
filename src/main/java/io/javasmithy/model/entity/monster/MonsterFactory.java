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

/** Class that loads all monsters from mopnsters.json into memory in 'monster configs' that allow monsters to be created
 * @author Peter Hyde-Smith
 */
public class MonsterFactory {
    private static URL resource = WeaponFactory.class.getResource("/data/monsters.json");
    /**
     * GSON instance loads configs from json
     */
    private static Gson gson = new Gson();
    /**
     * Map of types to configs
     */
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
