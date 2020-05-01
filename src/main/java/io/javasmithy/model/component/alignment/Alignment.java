package io.javasmithy.model.component.alignment;
/** Enums of alignments - flavor mostly though can be used in certain checks
 * @author Peter Hyde-Smith
 */
public enum Alignment{
    LG("Lawful Good", "Follows laws and what is morally right."),
    CG("Chaotic Good", "May bend the rules for the greater good rather than to the letter."),
    NG("Neutral Good", "Law and chaos don't matter, only that good is done."),
    LN("Lawful Neutral", "Law over everything - law creates balance. Good and evil is irrelevant."),
    TN("True Neutral", "Actively seeks to create balance between all axes."),
    CN("Chaotic Neutral", "Sows chaos, but doesn't matter if it is good or evil."),
    NE("Neutral Evil", "Doesn't actively challenge laws but doesn't follow them, self-serving and tends towards evil deeds."),
    CE("Chaotic Evil", "Sows chaos, self-serving and tries to destabilize. Deeds are mostly evil."),
    LE("Lawful Evil", "Upholds and enforces hierarchies of law and power, deeds are evil.");

    private String name;
    private String description;

    private Alignment(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }

    public String toString(){
        return this.name;
    }


    


}