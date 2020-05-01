package io.javasmithy.model.component.ability;
/** Enums used to provide a friendly way to organize which scores are called in the code. Useful in Ability Scores object and with raw skill checks vs a particular ability score.
 * @author Peter Hyde-Smith
 */
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