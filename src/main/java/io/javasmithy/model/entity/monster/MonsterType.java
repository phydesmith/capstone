package io.javasmithy.model.entity.monster;

public enum MonsterType {
    ZOMBIE("zombie"),
    GOBLIN( "goblin");

    private String id;

    private MonsterType(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
