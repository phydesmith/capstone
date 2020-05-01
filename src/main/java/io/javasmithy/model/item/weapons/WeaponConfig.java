package io.javasmithy.model.item.weapons;

import io.javasmithy.model.component.attack.AttackType;

/** POJO for storing weapon configs for creation
 * @author Peter Hyde-Smith
 */
public class WeaponConfig {
    private WeaponType weaponType;
    private AttackType attackType;
    private String name;
    private int dmgDie;
    private int dmgDieQty;
    private int value;
    private int atkRange;

    public Weapon generate(){
        Weapon weapon = new Weapon();
        weapon.setName(this.name);
        weapon.setDmgDie(dmgDie);
        weapon.setDmgDieQty(dmgDieQty);
        weapon.setWeaponType(weaponType);
        weapon.setAttackType(attackType);
        weapon.setValue(value);
        weapon.setAtkRange(atkRange);
        return weapon;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
}
