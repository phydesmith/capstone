package io.javasmithy.model.component.skill;

import io.javasmithy.model.component.ability.Ability;

public enum Skill{
    //Strength
    ATHLETICS("Athletics", "Feats of general athleticism", Ability.STRENGTH),

    //Dexterity
    ACROBATICS("Acrobatics", " Tumbling, flips and jumps related to agility.", Ability.DEXTERITY),
    SLEIGHT_OF_HAND("Sleight of Hand", "Tricker involving misdirection with objects about the person.", Ability.DEXTERITY),
    STEALTH("Stealth", "Ability to remain unseen.", Ability.DEXTERITY),

    //Intelligence
    ARCANA("Arcana", "Domain of knowledge pertaining to anything magical, potions, spells, arcane signs and symbols", Ability.INTELLIGENCE),
    HISTORY("History", "Knowledge of historical matters - generally non-magical", Ability.INTELLIGENCE),
    INVESTIGATION("Investigation", "Ability to find and follow leads where others would not see them, be it personal interaction or via books or written sources", Ability.INTELLIGENCE),
    NATURE("Nature", "Knowledge of the natural world.", Ability.INTELLIGENCE),
    RELIGION("Religion", "Knowledge of gods and deities as well as their respective religions, rituals, ceremonies and practices.", Ability.INTELLIGENCE),

    //Wisdom
    ANIMAL_HANDLING("Animal Handling", "Comfort and knowledge of handling animals.", Ability.WISDOM),
    INSIGHT("Insight", "Ability to determine true intentions of a creature - lying or actions.", Ability.WISDOM),
    MEDICINE("Medicine", "Non-magical medicines and applications of them to heal.", Ability.WISDOM),
    PERCEPTION("Perception", "Ability to spot hear or detect - general awareness of situations.", Ability.WISDOM),
    SURVIVAL("Survival", "Survival in hostile environments, be it hunting game or checking for tracks of wild animals.", Ability.WISDOM),

    //Charisma
    DECEPTION("Deception", "Ability to actively deceive someone and hide intentions.", Ability.CHARISMA),
    INTIMIDATION("Intimidation", "Bully or threaten an opponent into compliance.", Ability.CHARISMA),
    PERFORMANCE("Performance", "Perform via spoken word, song or general ability to entertain doing while do an action.", Ability.CHARISMA),
    PERSUASION("Persuasion", "Gain the trust of a person or opponent via things like flattery, logic or appeals to reason. ", Ability.CHARISMA);

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
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }

    public String toString(){
        return this.name;
    }
}