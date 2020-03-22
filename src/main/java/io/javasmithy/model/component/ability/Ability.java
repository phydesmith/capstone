package io.javasmithy.model.component.ability;

public enum Ability{
    STRENGTH("Strength", "Str"),
    DEXTERITY("Dexterity", "Dex"),
    CONSTITUTION("Constitution", "Con"),
    INTELLIGENCE("Intelligence", "Int"),
    WISDOM("Wisdom", "Wis"),
    CHARISMA("Charisma", "Cha");

    private String fullName;
    private String shortName;

    private Ability(String fullName, String shortName){
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName(){
        return this.fullName;
    }
    public String getShortName(){
        return this.shortName;
    }

    public String toString(){
        return this.fullName;
    }


}