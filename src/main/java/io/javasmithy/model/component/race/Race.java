package io.javasmithy.model.component.race;

public enum Race{
    DRAGONBORN("Dragonborn", "Half-Man, Half-Dragon, charismatic with an affinity towards sorcery."),
    DWARF("Dwarf", "Gruff and stout. Militant and industrious tendancies"),
    ELF("Elf", "Graceful and feylike forest dwellers."),
    GNOME("Gnome", "Short and stocky. Tend towards inquisitiveness, curiosity and electivism."),
    HALFLING("Halfling", "Short and slight. Simple agrarian lives centered around hearth and harvest. Some have an adventurous streak."),
    HALFELF("Half-Elf", "The best features of humans and elves- but the ability to fit in with neither fully. Tend to be restless and long for adventure."),
    HALFORC("Half-Orc", "Large and strong. Value strength and tend to be mercenaries or soliders."),
    HUMAN("Human", "Medium sized. Tend to be the most populous and widespread because of their thirst for expansion."),
    TIEFLING("Tiefling", "Any being with infernal ancestry, tend to be outcasts because of their overtly demonic features.");

    private String name;
    private String description;

    Race(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String toString(){
        return this.name;
    }
}