package io.javasmithy.model.room;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.entity.monster.Monster;
import io.javasmithy.model.position.PointGrid;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class EncounterRoom implements Room {
    private Queue<Entity> initiativeOrder;
    private List<Entity> monsters;
    private PointGrid grid;

    public EncounterRoom(){
        initEntities();
    }

    @Override
    public void initEntities() {
        //  load entities

    }

    @Override
    public void setPlayerEntity(Entity player) {

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

    @Override
    public Entity getNextEntity() {
        return null;
    }

    public void setGrid(PointGrid grid) {
        this.grid = grid;
    }

    public void setMonsterSpriteGrid(){
        System.out.println("DEBUG - SETTING SPRITE GRID");
        for (int i = 0 ; i < monsters.size(); i++){
            ((Monster)this.monsters.get(i)).setSpriteGrid(this.grid);
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
