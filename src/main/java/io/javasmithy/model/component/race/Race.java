package io.javasmithy.model.component.race;

public enum Race{
    DRAGONBORN("Dragonborn", "Half-Man, Half-Dragon, charismatic with an affinity towards sorcery.", 6),
    DWARF("Dwarf", "Gruff and stout. Militant and industrious tendancies",5),
    ELF("Elf", "Graceful and feylike forest dwellers.", 30),
    GNOME("Gnome", "Short and stocky. Tend towards inquisitiveness, curiosity and eclecticism.", 5),
    HALFLING("Halfling", "Short and slight. Simple agrarian lives centered around hearth and harvest. Some have an adventurous streak.", 5),
    HALFELF("Half-Elf", "The best features of humans and elves- but the ability to fit in with neither fully. Tend to be restless and long for adventure.", 6),
    HALFORC("Half-Orc", "Large and strong. Value strength and tend to be mercenaries or soliders.",6),
    HUMAN("Human", "Medium sized. Tend to be the most populous and widespread because of their thirst for expansion.", 6),
    TIEFLING("Tiefling", "Any being with infernal ancestry, tend to be outcasts because of their overtly demonic features.", 6);

    private String name;
    private String description;
    private int speed;

    Race(String name, String description, int Speed){
        this.name = name;
        this.description = description;
        this.speed = speed;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public int getSpeed(){return this.speed;}

    public String toString(){
        return this.name;
    }
}