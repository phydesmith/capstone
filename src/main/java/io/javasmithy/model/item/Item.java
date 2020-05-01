package io.javasmithy.model.item;

import io.javasmithy.model.entity.Entity;

/** Interface for items
 * @author Peter Hyde-Smith
 */
public interface Item {
    public void setValue(int value);
    public int getValue();
    public void setOwner(Entity e);
    public Entity getOwner();
    public ItemType getType();
    public String getName();
}
