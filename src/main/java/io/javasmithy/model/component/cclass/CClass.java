package io.javasmithy.model.component.cclass;

import io.javasmithy.model.component.ability.Ability;
import io.javasmithy.model.component.skill.Skill;


import java.util.List;

public enum CClass {
    CLERIC("Cleric", "A priestly champion who wields divine magic in service of a higher power", 8, 
            List.of(Ability.WISDOM, Ability.CHARISMA), 
            List.of(Skill.HISTORY,
                    Skill.INSIGHT,
                    Skill.MEDICINE,
                    Skill.PERSUASION,
                    Skill.RELIGION),
            2
    ),
    FIGHTER("Fighter", "A master of martial combat, skilled with a variety of weapons and armor", 10,
            List.of(Ability.STRENGTH, Ability.CONSTITUTION), 
            List.of(Skill.ACROBATICS,
                    Skill.ANIMAL_HANDLING,
                    Skill.ATHLETICS,
                    Skill.HISTORY,
                    Skill.INSIGHT,
                    Skill.INTIMIDATION,
                    Skill.PERCEPTION,
                    Skill.SURVIVAL),
            2
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
                Skill.STEALTH),
            4
    ),
    WIZARD("Wizard", "A scholarly magic-user capable of manipulating the structures of reality", 6,
        List.of(Ability.INTELLIGENCE, Ability.WISDOM), 
        List.of(Skill.ARCANA,
                Skill.HISTORY,
                Skill.INSIGHT,
                Skill.INVESTIGATION,
                Skill.MEDICINE,
                Skill.RELIGION),
        2
    );

    /**
     * Character Class name.
     */
    private String name;
    /**
     * Description of charater class.
     */
    private String description;
    /**
     * Hit die is used in calculating hitpoints.
     */
    private int hitDie;
    /**
     * Number of skills a character gains from being of class type.
     */
    private int skillCount;

    /**
     * List of abilities the class receives a proficiency bonus in when conducting saving throws.
     */
    private List<Ability> proficientSavingThrows; // saving throws that the character class is proficient in
    /**
     * List of skills the class is proficient in.
     */
    private List<Skill> skills; // list of possible skills the character class can choose from

    private CClass(String name, String description, int hitDie, List<Ability> savingThrows, List<Skill> skills, int skillCount){
        this.name = name;
        this.description = description;
        this.hitDie = hitDie;
        this.proficientSavingThrows = savingThrows;
        this.skills = skills;
        this.skillCount = skillCount;
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
    public int getSkillCount(){
        return this.skillCount;
    }

    public String toString(){
        return this.name;
    }
}

