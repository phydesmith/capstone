package io.javasmithy.model.room;

import io.javasmithy.model.entity.Entity;

import java.util.List;

public interface Room {
    public void initEntities();
    public void setPlayerEntity(Entity player);
    public List<Entity> getEntities();
    public void setInitiativeOrder();
    public Entity getNextEntity();
}
