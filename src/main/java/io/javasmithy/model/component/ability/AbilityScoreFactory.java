package io.javasmithy.model.component.ability;

import java.util.List;

import java.util.Map;
import java.util.HashMap;

import io.javasmithy.model.component.race.*;

public class AbilityScoreFactory{

    public static AbilityScores createAbilityScores(List<Integer> scoreList, Race race){
        Map<Ability, Integer> scoreMap = new HashMap<Ability, Integer>();
        int index = 0;
        for(Ability ability : Ability.values() ){
            scoreMap.put(ability, scoreList.get(index));
            index++;
        }
        return new AbilityScores(scoreMap, race);
    }

}