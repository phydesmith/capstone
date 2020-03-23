package io.javasmithy.model.component.alignment;

public enum Alignment{
    LG("Lawful Good", ""),
    CG("Chaotic Good", ""),
    NG("Neutral Good", ""),
    LN("Lawful Neutral", ""),
    TN("True Neutral", ""),
    CN("Chaotic Neutral", ""),
    NE("Neutral Evil", ""),
    CE("Chaotic Evil", ""),
    LE("Lawful Evil", "");

    private String name;
    private String description;

    private Alignment(String name, String description){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public String toString(){
        return this.name;
    }


    


}