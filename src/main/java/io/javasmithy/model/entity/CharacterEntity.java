package io.javasmithy.model.entity;

import io.javasmithy.model.component.abilities.AbilityScores;
import io.javasmithy.model.component.archetype.Archetype;
import io.javasmithy.model.component.hitpoints.*;
import io.javasmithy.model.component.race.Race;

public class CharacterEntity implements Entity{
    
    //  Biographical Information
    private String charName = " def ";
    private String alignment;
    private int age;
    private String description;

    //  Character Base Mechanics and Stats
    private Race race;
    private AbilityScores abilityScores;
    private Archetype archetype;
    private int speed;

    //  Character Counters
    private HitPoints hp;
    private int level;
    private int currentXP;
    private int gold;

    //  Character Static Combat Mechanics
    //private ArmorClass ac;
    
    //  Character Calculated Interaction and Combat Mechanics
    //  attack rolls bonus
    //  initiative bonus
    //  skill modifiers


    public CharacterEntity(){
        init();
    }

    private void init(){
        this.level = 1;
    }

    public void setAbilityScores(AbilityScores abilityScores){
        System.out.println("DEBUG " + abilityScores);
        this.abilityScores = abilityScores;
    }
    public AbilityScores AbilityScores(){
        return this.abilityScores;
    }

    public Race getRace(){
        return this.race;
    }
    public void setRace(Race race){
        this.race = race;
    }
    
    public int getLevel(){
        return this.level;
    }
    public void setLevel(int i ){
        this.level = i;
    }
    public void levelUp(){
        this.level++;
    }
    
    public String getCharName() {
        return charName;
    }
    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getAlignment() {
        return alignment;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Archetype getArchetype() {
        return archetype;
    }
    public void setArchetype(Archetype archetype) {
        this.archetype = archetype;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public HitPoints getHp() {
        return hp;
    }
    public void setHp(HitPoints hp) {
        this.hp = hp;
    }

    public int getCurrentXP() {
        return currentXP;
    }
    public void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public String toString(){
        String str = "";
        str += "\n NAME: " + this.charName + "\n RACE: " + this.race + "\n" + this.abilityScores;
        return str;
    }
}