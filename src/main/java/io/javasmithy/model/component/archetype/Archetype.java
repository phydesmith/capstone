package io.javasmithy.model.component.archetype;

public enum Archetype {
    FIGHTER("Fighter", "Brawler, warrior, muscle - all names for the fighter who's home is in the midst of a melee."),
    WIZARD("Wizard", "Through hours of studying, the wizard masters the arcane and forbidden knowledge."),
    ROGUE("Rogue", "From assassins to pick-pockets, those who value stealth and finesse over direct confrontation are generally considered rogues."),
    CLERIC("Cleric", "Divine devotees, who's power and magic stems from their deity.");

    private String name;
    private String description;

    private Archetype(String name, String description){
        this.name = name;
        this.description = description;
    }
}

