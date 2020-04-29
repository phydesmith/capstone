package io.javasmithy.model.room;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.position.PointGrid;

import java.util.List;

public interface Room {
    public List<Entity> getEntities();
    public void setEntities(List<Entity> entityList);
    public void setInitiativeOrder();
    public PointGrid getGrid();
}
