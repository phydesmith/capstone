package io.javasmithy.model.component.cclass;

import io.javasmithy.model.component.ability.Ability;
import io.javasmithy.model.component.skill.Skill;


import java.util.List;

public enum CClass {
    CLERIC("Cleric", "A priestly champion who wields divine magic in service of a higher power", 8, 
            List.of(Ability.WISDOM, Ability.CHARISMA), 
            List.of(Skill.HISTORY, Skill.INSIGHT, Skill.MEDICINE, Skill.PERSUASION, Skill.RELIGION)
    ),
    FIGHTER("Fighter", "A master of martial combat, skilled with a variety of weapons and armor", 10,
            List.of(Ability.STRENGTH, Ability.CONSTITUTION), 
            List.of(Skill.ACROBATICS, Skill.ANIMAL_HANDLING, Skill.ATHLETICS, Skill.HISTORY, Skill.INSIGHT, Skill.INTIMIDATION, Skill.PERCEPTION, Skill.SURVIVAL)
    ),
    ROGUE("Rogue", "A scoundrel who uses stealth and trickery to overcome obstacles and enemies", 8,
        List.of(Ability.DEXTERITY , Ability.INTELLIGENCE), 
        List.of(Skill.ACROBATICS, 
                Skill.ATHLETICS, 
                Skill.DECEPTION, 
                Skill.INSIGHT, 
                Skill.INTIMIDATION,
                Skill.INVESTIGATION, 
                Skill.PERCEPTION,
                Skill.PERFORMANCE,
                Skill.PERSUASION,
                Skill.SLEIGHT_OF_HAND,
                Skill.STEALTH)
    ),
    WIZARD("Wizard", "A scholarly magic-user capable of manipulating the structures of reality", 6,
        List.of(Ability.INTELLIGENCE, Ability.WISDOM), 
        List.of(Skill.ARCANA, Skill.HISTORY, Skill.INSIGHT, Skill.INVESTIGATION,  Skill.MEDICINE, Skill.RELIGION)
    );

    
    private String name;
    private String description;
    private int hitDie;

    private List<Ability> proficientSavingThrows; // saving throws that the character class is proficient in
    private List<Skill> skills; // list of possible skills the character class can choose from

    private CClass(String name, String description, int hitDie, List<Ability> savingThrows, List<Skill> skills){
        this.name = name;
        this.description = description;
        this.hitDie = hitDie;
        this.proficientSavingThrows = savingThrows;
        this.skills = skills;
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
    public List<Ability> getProficientSavingThrows(){
        return this.proficientSavingThrows;
    }
    public List<Skill> getSkillList(){
        return this.skills;
    }

    public String toString(){
        return this.name;
    }
}

