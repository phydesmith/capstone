package io.javasmithy.model.room;

import io.javasmithy.model.entity.Entity;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class EncounterRoom implements Room {
    private Queue<Entity> initiativeOrder;

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
        return null;
    }

    @Override
    public void setInitiativeOrder() {

    }

    @Override
    public Entity getNextEntity() {
        return null;
    }
}
