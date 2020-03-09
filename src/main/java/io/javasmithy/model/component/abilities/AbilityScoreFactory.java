package io.javasmithy.model.component.abilities;

import java.util.Map;
import java.util.HashMap;

import io.javasmithy.util.Generator;

public class AbilityScoreFactory{
    private static final Abilities[] abilities = Abilities.values();

    public static Map<Abilities, Integer> generateScores(){
        Map<Abilities, Integer> scoreMap = new HashMap<Abilities, Integer>();
        for(Abilities ability : abilities ){
            scoreMap.put(ability, Generator.generateAbilityScore());
        }
        return scoreMap;
    }

}