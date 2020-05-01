package io.javasmithy.model.room;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.position.PointGrid;

import java.util.List;

/** Interface to allow for different sub types of rooms.
 * @author Peter Hyde-Smith
 */
public interface Room {
    public List<Entity> getEntities();
    public void setEntities(List<Entity> entityList);
    public void setInitiativeOrder();
    public PointGrid getGrid();
}
