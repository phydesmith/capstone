package io.javasmithy.model.room;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.entity.monster.Monster;
import io.javasmithy.model.position.PointGrid;
import io.javasmithy.util.Generator;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class EncounterRoom implements Room {
    private Queue<Entity> initiativeOrder;
    private List<Entity> monsters;
    private PointGrid grid;
    private RoomType type;

    public EncounterRoom(){

    }

    public EncounterRoom(RoomType type){
        this.type = type;
    }

    public void setRoomType(RoomType type){
        this.type= type;
    }
    public RoomType getRoomType(){
        return this.type;
    }

    public PointGrid getGrid(){
        return this.grid;
    }

    @Override
    public List<Entity> getEntities() {
        return this.monsters;
    }
    @Override
    public void setEntities(List<Entity> entityList){
        this.monsters = entityList;
    }

    @Override
    public void setInitiativeOrder() {

    }

    public void setGrid(PointGrid grid) {
        this.grid = grid;
    }

    public void setMonsterSpriteGrid(){
        System.out.println("Log: setting monster sprite grid.");
        for (int i = 0 ; i < monsters.size(); i++){
            ((Monster)this.monsters.get(i)).setSpriteGrid(this.grid);
        }
    }
    public void randomizeMonsterStarts(){
        if (monsters == null) return;
        for (int i = 0 ; i < monsters.size(); i++){
            ((Monster)this.monsters.get(i)).setColumn(Generator.generate(this.grid.getWidth(), 1)-1);
            ((Monster)this.monsters.get(i)).setRow(Generator.generate(this.grid.getHeight(), 1)-1);
            this.monsters.get(i).getSprite().setPos();
            System.out.println("Log: " + monsters.get(i).getName() + " position set to ROW: " + this.monsters.get(i).getRow() + " COLUMN: " + this.monsters.get(i).getColumn());

        }
    }

    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < monsters.size();i++){
            str+= monsters.get(i).toString();
        }
        return str;
    }

}
