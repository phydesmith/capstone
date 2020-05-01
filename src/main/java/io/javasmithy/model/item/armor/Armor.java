package io.javasmithy.model.item.armor;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.item.Equippable;
import io.javasmithy.model.item.Item;

/** This class is used to represent armors that character objects use for protection against ttacks.
 * @author Peter Hyde-Smith
 */
public class Armor implements Item, Equippable {
    /**
     * Type of armor
     */
    private ArmorType armorType;
    private String name;
    /**
     * Modifier used in attack checks
     */
    private int acVal;
    private Entity owner;
    private boolean isEquipped;
    private int value;

    public Armor (String name, int acVal, Entity owner){
        this.name = name;
        this.acVal = acVal;
        this.owner = owner;
    }

    public Armor(){};

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

    public void setArmorType(ArmorType type){
        this.armorType = type;
    }

    public ArmorType getType(){
        return this.armorType;
    }

    @Override
    public String toString(){
        return this.name + " (" + this.value + "gp)"
                + "\nAC Bonus: +" + this.acVal
                + "\nEquipped: " + this.isEquipped;
    }
}
