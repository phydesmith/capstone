package io.javasmithy.model.component.archetype;

public enum Archetype {
    CLERIC("Cleric", "A priestly champion who wields divine magic in service of a higher power", 8),
    FIGHTER("Fighter", "A master of martial combat, skilled with a variety of weapons and armor", 10),
    WIZARD("Wizard", "A scholarly magic-user capable of manipulating the structures of reality", 6),
    ROGUE("Rogue", "A scoundrel who uses stealth and trickery to overcome obstacles and enemies", 8);
    
    private String name;
    private String description;
    private int hitDie;

    private Archetype(String name, String description, int hitDie){
        this.name = name;
        this.description = description;
        this.hitDie = hitDie;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getHitDie() {
        return hitDie;
    }

    public String toString(){
        return this.name;
    }
}

