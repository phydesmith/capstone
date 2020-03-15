package io.javasmithy.model.component.abilities;

import io.javasmithy.util.Generator;

import java.util.List;
import java.util.ArrayList;

public class RawScoreFactory{
    private static final Generator GENERATOR = new Generator();
    private static final int MIN_SCORE = 69; // 69 is total of standard array

    public static List<Integer> generateValidScoreList(){
        List<Integer> scoreList = generateRawScoreList();
        while (!totalGreaterThanMin(scoreList)){
            scoreList = generateRawScoreList();
        }
        return scoreList;
    }
    private static List<Integer> generateRawScoreList(){
        List<Integer> scoreList = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++){
            scoreList.add(GENERATOR.generateAbilityScore());
        }
        return scoreList;
    }
    private static boolean totalGreaterThanMin(List<Integer> scoreList){
        int total = 0;
        for (int i =0; i < scoreList.size(); i++){
            total+=scoreList.get(i);
        }
        return (total > MIN_SCORE);
    }

}