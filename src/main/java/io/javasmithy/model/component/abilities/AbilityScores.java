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

    public String toString(){
        String str = "\n Ability Scores" + "\n ---------------- ";
        Abilities[] abilities = Abilities.values();
        for(Abilities ability : abilities ){
            str += "\n" + ability + " " + scores.get(ability) + " | " + getModifier(ability);
        }
        return str;
    }

}