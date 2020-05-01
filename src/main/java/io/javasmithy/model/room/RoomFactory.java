package io.javasmithy.model.room;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.entity.monster.Monster;
import io.javasmithy.model.entity.monster.MonsterFactory;
import io.javasmithy.model.entity.monster.MonsterType;

import java.util.ArrayList;
import java.util.List;

/** Depending on room type returns a room with a pgrid and monsters.
 * @author Peter Hyde-Smith
 */
public class RoomFactory {

    /** returns a room that based on room type
     * @param roomType the type of room to return
     * @return room to return
     */
    public static Room createRoom(RoomType roomType){
        if (roomType == RoomType.ROOM_1){
            return createRoom1();
        } else if (roomType == RoomType.ROOM_2){
            return createRoom2();
        } else return new EncounterRoom(RoomType.ROOM_0);
    }

    private static Room createRoom1(){
        Room room = new EncounterRoom(RoomType.ROOM_1);
        List<Entity> monsters = new ArrayList<Entity>();
        for(int i =0; i < 2; i++){ // set to 1 for debug
            monsters.add(MonsterFactory.createMonster(MonsterType.ZOMBIE));
            String name = monsters.get(i).getName() + " " + i;
            ((Monster) monsters.get(i)).setName( name);
        }
        room.setEntities(monsters);
        ((EncounterRoom)room).setGrid(RoomType.ROOM_1.getGrid());
        ((EncounterRoom) room).setMonsterSpriteGrid();
        return room;
    }

    private static Room createRoom2(){
        Room room = new EncounterRoom(RoomType.ROOM_2);
        List<Entity> monsters = new ArrayList<Entity>();
        for(int i =0; i < 2; i++){
            monsters.add(MonsterFactory.createMonster(MonsterType.GOBLIN));
            String name = monsters.get(i).getName() + " " + i;
            ((Monster) monsters.get(i)).setName( name);
        }
        room.setEntities(monsters);
        ((EncounterRoom)room).setGrid(RoomType.ROOM_2.getGrid());
        ((EncounterRoom) room).setMonsterSpriteGrid();
        return room;
    }

}
