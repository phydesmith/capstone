package io.javasmithy.model.entity.monster;
/**Types of monsters used by GSON to load entries from json in monster factory and monster config
 * @author Peter Hyde-Smith
 */
public enum MonsterType {
    ZOMBIE("zombie", "/assets/img/zombie1-sprite-50px.png"),
    GOBLIN( "goblin", "/assets/img/f-warrior-sprite-50px.png");

    private String id;
    private String imgPath;

    private MonsterType(String id, String imgPath){
        this.id = id;
        this.imgPath = imgPath;
    }

    public String getId(){
        return this.id;
    }
    public String getImgPath() { return this.imgPath; }
}
