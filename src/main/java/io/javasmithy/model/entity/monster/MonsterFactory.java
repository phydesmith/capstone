package io.javasmithy.model.entity.monster;

import com.google.gson.Gson;
import io.javasmithy.view.Sprite;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *  source http://tutorials.jenkov.com/java-json/boon-objectmapper.html
 *  https://attacomsian.com/blog/gson-read-json-file#   <---- this has how to create a map
 *  https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
 */

public class MonsterFactory {
    private static String path = "target\\classes\\data\\monsters.json";
    private static Gson gson = new Gson();
    private static Map<MonsterType, MonsterConfig> monsterMap;
    static {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            MonsterConfig[] monsters = gson.fromJson(reader, MonsterConfig[].class);
            monsterMap = new HashMap<MonsterType, MonsterConfig>();

            for (int i = 0; i < monsters.length; i++){
                monsterMap.put(monsters[i].getMonsterType(), monsters[i] );
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Monster createMonster(MonsterType type){
        Monster monster = monsterMap.get(type).generate();
        monster.setSprite(new Sprite());
        monster.getSprite().setImage(new Image(MonsterFactory.class.getClassLoader().getResource( type.getImgPath() ).toExternalForm()) );
        monster.initHP();
        return monster;
    }
}
