package io.javasmithy.model.entity;

import io.javasmithy.model.component.level.*;
import io.javasmithy.model.component.ability.AbilityScores;
import io.javasmithy.model.component.cclass.CClass;
import io.javasmithy.model.component.background.Background;
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
    private CClass cClass;
    private Background background;
    private int speed;

    //  Character Counters
    private HitPoints hp;
    private Level level;
    private int currentXP;
    private int gold;

    //  Character Static Combat Mechanics
    //private ArmorClass ac;
    
    //  Character Calculated Interaction and Combat Mechanics
    //  saving throws
    //  attack rolls bonus
    //  initiative bonus
    //  skill modifiers


    public CharacterEntity(){
        init();
    }

    private void init(){
        initLevel();
    }

    public void setAbilityScores(AbilityScores abilityScores){
        System.out.println("DEBUG " + abilityScores);
        this.abilityScores = abilityScores;
    }
    public AbilityScores getAbilityScores(){
        return this.abilityScores;
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

    public CClass getCClass() {
        return cClass;
    }
    public void setCClass(CClass charclass) {
        this.cClass = cClass;
    }

    public Background getBackground(){
        return this.background;
    }
    public void setBackground(Background background){
        this.background = background;
    }

    public String getCharName() {
        return charName;
    }
    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel(){
        return this.level;
    }
    private void initLevel(){
        //  Sets level to Level 1
        this.level = Level.getNewLevel(1);
    }
    private void setLevel(int i ){
        this.level = Level.getNewLevel(i);
    }
    private void levelUp(){
        this.level = Level.getNewLevel(this.level.getLevelValue()+1);
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

    public Race getRace(){
        return this.race;
    }
    public void setRace(Race race){
        this.race = race;
    }

    public String toString(){
        String str = "";
        str += "\n Name: \t" + this.charName 
            + "\n Race: \t" + this.race
            + "\n Background: \t" + this.background
            + "\n Class: \t" + this.cClass
            + "\n" + this.abilityScores;
        return str;
    }
}