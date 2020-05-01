package io.javasmithy.model.component.background;

import io.javasmithy.model.component.skill.Skill;

import java.util.List;

/** Enums of backgrounds - extra skills a character might game from background
 * @author Peter Hyde-Smith
 */
public enum Background {
    SOLDIER("Soldier",
            "A professional warrior, in service to a city guard, provincial regiment, or some other organized fighting force.",
            List.of(Skill.ATHLETICS,
                    Skill.INTIMIDATION)),
    ACOLYTE("Acolyte",
            "Usually raised in a monastary or temple from a young age, these people tend to be sheltered. However, they are well-learned albeit within the confines of their dogma.",
            List.of(Skill.INSIGHT,
                    Skill.RELIGION)),
    CHARLATAN("Charlatan",
            "A charismatic but untrustworthy character, will flatter and charm and offer all manner of empty promises for a quick coin.",
            List.of(Skill.DECEPTION,
                    Skill.SLEIGHT_OF_HAND)),
    HERMIT("Hermit",
            "A solitary character, usually self-imposed, though for a variety of reasons. They spend much of their time pursuing specialized knowledge.",
            List.of(Skill.MEDICINE,
                    Skill.RELIGION)),
    NOBLE("Noble",
            "Born into the elite of society, these characters tend to be wealthy and entitled. Many wield enough influence to find luxury in their travels, using only their titles.",
            List.of(Skill.HISTORY,
                    Skill.PERSUASION));

    private String name;
    private String description;
    private List<Skill> skills;

    private Background(String name, String description, List<Skill> skills){
        this.name = name;
        this.description = description;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<Skill> getSkillList(){ return this.skills;}

    public String toString(){
        return this.name;
    }
}

