package io.javasmithy.model.component.ability;

import java.util.List;

import java.util.Map;
import java.util.HashMap;

import io.javasmithy.model.component.race.*;

/** Converts a scorelist into an AbilityScore object that has methods that are useful to mechanics.
 * @author Peter Hyde-Smith
 */
public class AbilityScoreFactory{

    /**
     * Creates an AbilityScores object from a score list and race.
     * @param scoreList validated score list
     * @param race enum used for score bonuses
     * @return an ability score object
     */
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