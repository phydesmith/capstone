package io.javasmithy.model.item.weapons;

import io.javasmithy.model.component.Attack.AttackType;
import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.item.Equippable;
import io.javasmithy.model.item.Item;

public class Weapon implements Item, Equippable {
    private String name;
    private int dmgDie;
    private int dmgDieQty;
    private WeaponType weaponType;
    private AttackType attackType;
    private Entity owner;
    private boolean isEquipped;
    private int value;
    private int atkRange;

    public Weapon(){
        this("", 1, 1, 1, WeaponType.LONGSWORD, AttackType.MELEE);
    }

    public Weapon(String name, int dmgDie, int dmgDieQty, int atkRange, WeaponType weaponType, AttackType attackType){
        this.name = name;
        this.dmgDie = dmgDie;
        this.dmgDieQty = dmgDieQty;
        this.weaponType = weaponType;
        this.attackType = attackType;
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

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType type) {
        this.attackType = type;
    }

    public WeaponType getWeaponType(){
        return this.weaponType;
    }
    public void setWeaponType(WeaponType weaponType){
        this.weaponType = weaponType;
    }

    public int getAtkRange(){
        return this.atkRange;
    }

    public void setAtkRange(int r){ this.atkRange = r;}

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
                + "\n" + this.attackType;
    }

}
