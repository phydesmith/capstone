package io.javasmithy.model.component.ability;

import java.util.Map;
import io.javasmithy.model.component.race.*;

public class AbilityScores{
    Map<Ability, Integer> scores;

    public AbilityScores(Map<Ability, Integer> scores, Race race){
        this.scores = scores;
        applyRaceMods(race);
    }

    public int getModifier(Ability ability){
        return (this.scores.get(ability)-10)/2;
    }

    public void applyRaceMods(Race race){

        if ( race == Race.DRAGONBORN){
            this.scores.put(Ability.CHARISMA, this.scores.get(Ability.CHARISMA)+1);
            this.scores.put(Ability.STRENGTH, this.scores.get(Ability.STRENGTH)+2);
        } else if (race == Race.DWARF) {
            this.scores.put(Ability.CONSTITUTION, this.scores.get(Ability.CONSTITUTION)+2);
        }else if (race == Race.ELF) {
            this.scores.put(Ability.DEXTERITY, this.scores.get(Ability.DEXTERITY)+2);
        }else if (race == Race.GNOME) {
            this.scores.put(Ability.INTELLIGENCE, this.scores.get(Ability.INTELLIGENCE)+2);
        }else if (race == Race.HALFELF) {
            //  This is not correct per PHB and functionality to choose will need to be implemented
            this.scores.put(Ability.CHARISMA, this.scores.get(Ability.CHARISMA)+2);
            this.scores.put(Ability.WISDOM, this.scores.get(Ability.WISDOM)+1);
        }else if (race == Race.HALFLING) {
            this.scores.put(Ability.DEXTERITY, this.scores.get(Ability.DEXTERITY)+2);
        }else if (race == Race.HALFORC) {
            this.scores.put(Ability.STRENGTH, this.scores.get(Ability.STRENGTH)+2);
            this.scores.put(Ability.CONSTITUTION, this.scores.get(Ability.CONSTITUTION)+1);
        }else if (race == Race.HUMAN) {
            for (Ability Ability : Ability.values()){
                this.scores.put(Ability, this.scores.get(Ability)+1);
            }
        }else if (race == Race.TIEFLING) {
            this.scores.put(Ability.CHARISMA, this.scores.get(Ability.CHARISMA)+2);
            this.scores.put(Ability.INTELLIGENCE, this.scores.get(Ability.INTELLIGENCE)+1);
        }else {}
    }

    public String toString(){
        String str = "\n Ability Scores" + "\n ---------------- ";
        for(Ability ability : Ability.values() ){
            str += "\n" + ability + " " + scores.get(ability) + " | " + getModifier(ability);
        }
        return str;
    }

}