package io.javasmithy.model.component.abilities;

import java.util.Map;

public class AbilityScores{
    Map<Abilities, Integer> scores;

    public AbilityScores(Map<Abilities, Integer> scores){
        this.scores = scores;
    }

    public int getModifier(Abilities ability){
        return (this.scores.get(ability)-10)/2;
    }

}