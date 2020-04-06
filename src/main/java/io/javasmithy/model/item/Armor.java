package io.javasmithy.model.item;

import io.javasmithy.model.entity.Entity;

public class Armor implements Item, Equippable {
    private String name;
    private int acVal;
    private Entity owner;
    private boolean isEquipped;
    private int value;

    public Armor (String name, int acVal, Entity owner){
        this.name = name;
        this.acVal = acVal;
        this.owner = owner;
    }

    public Armor (String name, int acVal){
        this(name, acVal, null);
    }

    @Override
    public void setEquipped(boolean status) {
        this.isEquipped = status;
    }

    @Override
    public boolean isEquipped() {
        return isEquipped;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Entity getOwner() {
        return this.owner;
    }
    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAcVal() {
        return acVal;
    }

    public void setAcVal(int acVal) {
        this.acVal = acVal;
    }

    @Override
    public String toString(){
        return this.name
                + "\n+" + this.acVal;
    }
}
