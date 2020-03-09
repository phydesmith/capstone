package io.javasmithy.model.entity;

import io.javasmithy.model.component.abilities.*;
import io.javasmithy.model.component.hitpoints.*;

public class CharacterEntity{
    AbilityScores abilityScores;
    HitPoints hp;


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
        abilityScores = new AbilityScores(AbilityScoreFactory.generateScores());
    }

}