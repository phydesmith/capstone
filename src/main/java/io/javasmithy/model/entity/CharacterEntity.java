package io.javasmithy.model.entity;

import io.javasmithy.model.component.abilities.AbilityScores;
import io.javasmithy.model.component.hitpoints.*;
import io.javasmithy.model.component.race.Race;

public class CharacterEntity implements Entity{
    String charName = " def ";
    AbilityScores abilityScores;
    HitPoints hp;
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

    public String toString(){
        String str = "";
        str += "\n" + this.charName + "\n" + this.abilityScores;
        return str;
    }
}