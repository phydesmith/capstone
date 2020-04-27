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
    public void randomizeMonsterStarts(){
        for (int i = 0 ; i < monsters.size(); i++){
            ((Monster)this.monsters.get(i)).setColumn(Generator.generate(this.grid.getWidth(), 1));
            ((Monster)this.monsters.get(i)).setRow(Generator.generate(this.grid.getHeight(), 1));
            this.monsters.get(i).getSprite().setPos();
            System.out.println("ROW: " + this.monsters.get(i).getRow() + " COLUMN: " + this.monsters.get(i).getColumn());

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
