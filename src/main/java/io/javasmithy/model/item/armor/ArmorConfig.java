package io.javasmithy.model.item.armor;

public class ArmorConfig {
    private ArmorType armorType;
    private String name;
    private int acVal;
    private int value;

    public Armor generate(){
        Armor armor = new Armor();
        armor.setArmorType(this.armorType);
        armor.setName(this.name);
        armor.setAcVal(this.acVal);
        armor.setValue(this.value);
        return armor;
    }

    public ArmorType getArmorType() {
        return armorType;
    }
}