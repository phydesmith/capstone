package io.javasmithy.model.component.alignment;

public enum Alignment{
    LG("Lawful Good", "1"),
    CG("Chaotic Good", "2"),
    NG("Neutral Good", "3"),
    LN("Lawful Neutral", "4"),
    TN("True Neutral", "5"),
    CN("Chaotic Neutral", "6"),
    NE("Neutral Evil", "7"),
    CE("Chaotic Evil", "8"),
    LE("Lawful Evil", "9");

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