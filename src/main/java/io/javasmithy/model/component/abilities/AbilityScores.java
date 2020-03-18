package io.javasmithy.model.component.abilities;

import java.util.Map;
import io.javasmithy.model.component.race.*;

public class AbilityScores{
    Map<Abilities, Integer> scores;

    public AbilityScores(Map<Abilities, Integer> scores, Race race){
        this.scores = scores;
        applyRaceMods(race);
    }

    public int getModifier(Abilities ability){
        return (this.scores.get(ability)-10)/2;
    }

    public void applyRaceMods(Race race){

        if ( race == Race.DRAGONBORN){
            this.scores.put(Abilities.CHARISMA, this.scores.get(Abilities.CHARISMA)+1);
            this.scores.put(Abilities.STRENGTH, this.scores.get(Abilities.STRENGTH)+2);
        } else if (race == Race.DWARF) {
            this.scores.put(Abilities.CONSTITUTION, this.scores.get(Abilities.CONSTITUTION)+2);
        }else if (race == Race.ELF) {
            this.scores.put(Abilities.DEXTERITY, this.scores.get(Abilities.DEXTERITY)+2);
        }else if (race == Race.GNOME) {
            this.scores.put(Abilities.INTELLIGENCE, this.scores.get(Abilities.INTELLIGENCE)+2);
        }else if (race == Race.HALFELF) {
            //  This is not correct per PHB and functionality to choose will need to be implemented
            this.scores.put(Abilities.CHARISMA, this.scores.get(Abilities.CHARISMA)+2);
            this.scores.put(Abilities.WISDOM, this.scores.get(Abilities.WISDOM)+1);
        }else if (race == Race.HALFLING) {
            this.scores.put(Abilities.DEXTERITY, this.scores.get(Abilities.DEXTERITY)+2);
        }else if (race == Race.HALFORC) {
            this.scores.put(Abilities.STRENGTH, this.scores.get(Abilities.STRENGTH)+2);
            this.scores.put(Abilities.CONSTITUTION, this.scores.get(Abilities.CONSTITUTION)+1);
        }else if (race == Race.HUMAN) {
            for (Abilities abilities : Abilities.values()){
                this.scores.put(abilities, this.scores.get(abilities)+1);
            }
        }else if (race == Race.TIEFLING) {
            this.scores.put(Abilities.CHARISMA, this.scores.get(Abilities.CHARISMA)+2);
            this.scores.put(Abilities.INTELLIGENCE, this.scores.get(Abilities.INTELLIGENCE)+1);
        }else {}
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