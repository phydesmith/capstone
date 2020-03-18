package io.javasmithy.model.component.abilities;

import java.util.List;

import java.util.Map;
import java.util.HashMap;

import io.javasmithy.model.component.race.*;

public class AbilityScoreFactory{
    private static final Abilities[] abilities = Abilities.values();

    public static AbilityScores createAbilityScores(List<Integer> scoreList, Race race){
        Map<Abilities, Integer> scoreMap = new HashMap<Abilities, Integer>();
        int index = 0;
        for(Abilities ability : abilities ){
            scoreMap.put(ability, scoreList.get(index));
            index++;
        }
        return new AbilityScores(scoreMap, race);
    }

}