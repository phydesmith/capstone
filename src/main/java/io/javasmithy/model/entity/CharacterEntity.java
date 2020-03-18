package io.javasmithy.model.entity;

import io.javasmithy.model.component.abilities.AbilityScores;
import io.javasmithy.model.component.hitpoints.*;
import io.javasmithy.model.component.race.Race;

public class CharacterEntity implements Entity{
    private String charName = " def ";
    private AbilityScores abilityScores;
    private HitPoints hp;
    private Race race;


    // hitpoints
    // armor class
    // race
    // class
    // background
    // alignment
    // name
    // speed


    public CharacterEntity(){
        init();
    }

    private void init(){
        
    }

    public void setAbilityScores(AbilityScores abilityScores){
        System.out.println("DEBUG " + abilityScores);
        this.abilityScores = abilityScores;
    }
    public void applyRacialAbilityBonuses(){
        
    }

    public void setRace(Race race){
        this.race = race;
    }
    public Race getRace(){
        return this.race;
    }
    
    public String toString(){
        String str = "";
        str += "\n NAME: " + this.charName + "\n RACE: " + this.race + "\n" + this.abilityScores;
        return str;
    }
}