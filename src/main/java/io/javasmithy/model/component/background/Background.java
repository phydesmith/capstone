package io.javasmithy.model.component.background;

public enum Background {
    SOLDIER("Soldier", "A professional warrior, in service to a city guard, provincial regiment or some other organized fighting force."),
    ACOLYTE("Acolyte", "Usually raised in a monastary or temple from a young age, these people tend to be sheltered. However, they are well-learned albeit within the confines of their dogma."),
    CHARLATAN("Charlatan", "A charismatic but untrustworthy character, will flatter and charm and offer all manner of empty promises for a quick coin."),
    HERMIT("Hermit", "A solitary character, usually self-imposed, though for a variety of reasons. They spend much of their time pursuing specialized knowledge."),
    NOBLE("Noble", "Born into the elite of society, these characters tend to be wealthy and entitled. Many wield enough influence to find luxury in their travels, using only their titles.");

    private String name;
    private String description;

    private Background(String name, String description){
        this.name = name;
        this.description = description;
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

