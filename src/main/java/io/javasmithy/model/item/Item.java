package io.javasmithy.model.item;

import io.javasmithy.model.entity.Entity;

public interface Item {
    public void setValue(int value);
    public int getValue();
    public void setOwner(Entity e);
    public Entity getOwner();
}
