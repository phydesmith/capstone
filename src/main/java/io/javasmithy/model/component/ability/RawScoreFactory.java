package io.javasmithy.model.component.ability;

import io.javasmithy.util.Generator;

import java.util.List;
import java.util.ArrayList;

/** This class generates a list of raw scores that can be passed to an ability score factory.
 * @author Peter Hyde-Smith
 */
public class RawScoreFactory{
    /**
     * Minimum total sum of scores for score list to be valid.
     */
    private static final int MIN_SCORE = 69; // 69 is total of standard array

    /**
     * Generates a raw score list, checks that it is greater than the MIN_SCORE and returns it. If it is not, it rerolls until it finds a valid one.
     * @return list of valid scores
     */
    public static List<Integer> generateValidScoreList(){
        List<Integer> scoreList = generateRawScoreList();
        while (!totalGreaterThanMin(scoreList)){
            scoreList = generateRawScoreList();
        }
        return scoreList;
    }

    /**
     * Generates six ability scores and puts them into a list - uses Generator.generateAbilityScore()
     * @return list of six ability scores not checked for total
     */
    private static List<Integer> generateRawScoreList(){
        List<Integer> scoreList = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++){
            scoreList.add(Generator.generateAbilityScore());
        }
        return scoreList;
    }

    /**
     * method checks score list to ensure it is greater than MIN_SCORE
     * @param scoreList raw score list to check
     * @return boolean whether or not score list is valid
     */
    private static boolean totalGreaterThanMin(List<Integer> scoreList){
        int total = 0;
        for (int i =0; i < scoreList.size(); i++){
            total+=scoreList.get(i);
        }
        return (total > MIN_SCORE);
    }

}