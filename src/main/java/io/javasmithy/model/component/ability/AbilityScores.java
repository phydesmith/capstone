package io.javasmithy.model.component.ability;

import java.util.Map;
import io.javasmithy.model.component.race.*;

/** Ability score class that holds a map of Ability and Integer.
 * @author Peter Hyde-Smith
 */
public class AbilityScores{
    /**
     * Score map - can use an Ability enum to return associated score.
     */
    Map<Ability, Integer> scores;

    public AbilityScores(Map<Ability, Integer> scores, Race race){
        this.scores = scores;
        applyRaceMods(race);
    }

    /**
     * Gets the modifier that is associated with the score value. This equation comes from the DnD rulebook.
     * @param ability ability to get from score map to perform calculation on
     * @return modifier for associated skill
     */
    public int getModifier(Ability ability){
        return (this.scores.get(ability)-10)/2;
    }

    /**
     * This method is called during character creation to modify the ability scores based on 'racial' adjustments
     * note: Race in Dungeons and Dragons is Elf, Half-Orc, Human, Dragonborn etc.
     * @param race the race to get use to determine modifier
     */
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
            str += "\n" + ability.getShortName() + "\t " + scores.get(ability) + "\t| " + getModifier(ability);
        }
        return str;
    }

}