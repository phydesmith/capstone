package io.javasmithy.model.item;

import io.javasmithy.model.entity.Entity;


public class WeaponConfig {
    private WeaponType weaponType;
    private String name;
    private int dmgDie;
    private int dmgDieQty;
    private WeaponType type;
    private Entity owner;
    private boolean isEquipped;
    private int value;
    private int atkRange;

    public Weapon generate(){
        Weapon weapon = new Weapon();
        weapon.setName(this.name);
        weapon.setDmgDie(dmgDie);
        weapon.setDmgDieQty(dmgDieQty);
        weapon.setType(type);
        weapon.setValue(value);
        weapon.setAtkRange(atkRange);
        return weapon;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
}
