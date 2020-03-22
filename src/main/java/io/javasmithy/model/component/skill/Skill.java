package io.javasmithy.model.component.skill;

import io.javasmithy.model.component.ability.Ability;

public enum Skill{
    //Strength
    ATHLETICS("Athletics", " desc placeholder", Ability.STRENGTH),

    //Dexterity
    ACROBATICS("Acrobatics", " desc placeholder", Ability.DEXTERITY),
    SLEIGHT_OF_HAND("Sleight of Hand", " desc placeholder", Ability.DEXTERITY),
    STEALTH("Stealth", " desc placeholder", Ability.DEXTERITY),

    //Intelligence
    ARCANA("Arcana", " desc placeholder", Ability.INTELLIGENCE),
    HISTORY("History", " desc placeholder", Ability.INTELLIGENCE),
    INVESTIGATION("Investigation", " desc placeholder", Ability.INTELLIGENCE),
    NATURE("Nature", " desc placeholder", Ability.INTELLIGENCE),
    RELIGION("Religion", " desc placeholder", Ability.INTELLIGENCE),

    //Wisdom
    ANIMAL_HANDLING("Animal Handling", " desc placeholder", Ability.WISDOM),
    INSIGHT("Insight", " desc placeholder", Ability.WISDOM),
    MEDICINE("Medicine", " desc placeholder", Ability.WISDOM),
    PERCEPTION("Perception", " desc placeholder", Ability.WISDOM),
    SURVIVAL("Survival", " desc placeholder", Ability.WISDOM),

    //Charisma
    DECEPTION("Deception", " desc placeholder", Ability.CHARISMA),
    INTIMIDATION("Intimidation", " desc placeholder", Ability.CHARISMA),
    PERFORMANCE("Performance", " desc placeholder", Ability.CHARISMA),
    PERSUASION("Persuasion", " desc placeholder", Ability.CHARISMA);

    private String name;
    private String description;
    private Ability ability;

    private Skill(String name, String description, Ability ability){
        this.name = name;
        this.description = description;
        this.ability = ability;
    }

    public Ability getAbility(){
        return this.ability;
    }
    public String getName(){
        return this.name();
    }
    public String getDescription(){
        return this.description;
    }

    public String toString(){
        return this.name;
    }
}