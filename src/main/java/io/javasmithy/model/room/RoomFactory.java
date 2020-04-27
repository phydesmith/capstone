package io.javasmithy.model.room;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.entity.monster.Monster;
import io.javasmithy.model.entity.monster.MonsterFactory;
import io.javasmithy.model.entity.monster.MonsterType;

import java.util.ArrayList;
import java.util.List;

public class RoomFactory {

    public static Room createRoom(RoomType roomType){
        if (roomType == RoomType.ROOM_1){
            return createRoom1();
        } else if (roomType == RoomType.ROOM_2){
            return createRoom2();
        } else return null;
    }

    private static Room createRoom1(){
        Room room = new EncounterRoom();
        List<Entity> monsters = new ArrayList<Entity>();
        for(int i =0; i < 2; i++){ // set to 1 for debug
            monsters.add(MonsterFactory.createMonster(MonsterType.ZOMBIE));
        }
        room.setEntities(monsters);
        ((EncounterRoom)room).setGrid(RoomType.ROOM_1.getGrid());
        ((EncounterRoom) room).setMonsterSpriteGrid();
        return room;
    }

    private static Room createRoom2(){
        Room room = new EncounterRoom();
        List<Entity> monsters = new ArrayList<Entity>();
        for(int i =0; i < 2; i++){
            monsters.add(MonsterFactory.createMonster(MonsterType.GOBLIN));
        }
        room.setEntities(monsters);
        ((EncounterRoom)room).setGrid(RoomType.ROOM_2.getGrid());
        ((EncounterRoom) room).setMonsterSpriteGrid();
        return room;
    }

}
