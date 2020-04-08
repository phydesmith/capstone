package io.javasmithy.model.item;

import io.javasmithy.model.entity.Entity;

public class Weapon implements Item, Equippable {
    private String name;
    private int dmgDie;
    private int dmgDieQty;
    private WeaponType type;
    private Entity owner;
    private boolean isEquipped;
    private int value;
    private int atkRange;

    public Weapon(){
        this("", 1, 1, 1, WeaponType.MELEE);
    }

    public Weapon(String name, int dmgDie, int dmgDieQty, int atkRange, WeaponType type){
        this.name = name;
        this.dmgDie = dmgDie;
        this.dmgDieQty = dmgDieQty;
        this.type = type;
        this.atkRange = atkRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDmgDie() {
        return dmgDie;
    }

    public void setDmgDie(int dmgDie) {
        this.dmgDie = dmgDie;
    }

    public int getDmgDieQty() {
        return dmgDieQty;
    }

    public void setDmgDieQty(int dmgDieQty) {
        this.dmgDieQty = dmgDieQty;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public int getAtkRange(){
        return this.atkRange;
    };

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
    public void setOwner(Entity owner){
        this.owner = owner;
    }

    @Override
    public void setEquipped(boolean status) {
        this.isEquipped = status;
    }

    @Override
    public boolean isEquipped() {
        return this.isEquipped;
    }

    @Override
    public String toString() {
        return "\n" + this.name
                + "\n" + this.dmgDieQty + "d" + dmgDie
                + "\n" + this.type;
    }

}