package io.javasmithy.model.item.weapons;

import io.javasmithy.model.component.attack.AttackType;
import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.item.Equippable;
import io.javasmithy.model.item.Item;

/** This class is used to represent weapons that character objects use for attacks.
 * @author Peter Hyde-Smith
 */
public class Weapon implements Item, Equippable {
    private String name;
    /** Represents sides of damage die
     */
    private int dmgDie;
    /** Represents qty of damage dice.
     */
    private int dmgDieQty;
    /** Type of weapon.
     */
    private WeaponType weaponType;
    /** Determines type of attack bonuses to use of weapon.
     */
    private AttackType attackType;
    private Entity owner;
    private boolean isEquipped;
    /** Currency value of weapon.
     */
    private int value;
    /** Range of weapon in grid spaces.
     */
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

    public WeaponType getType(){
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
        return "" + this.name + " (" + this.value + "gp)"
                + "\n" + this.dmgDieQty + "d" + dmgDie
                + "\n" + this.attackType
                + "\n" + this.isEquipped;
    }

}
