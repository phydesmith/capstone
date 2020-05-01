package io.javasmithy.model.entity.monster;

import io.javasmithy.model.component.hitpoints.HitPoints;
import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.position.PointGrid;
import io.javasmithy.util.Distance;
import io.javasmithy.util.GameLog;
import io.javasmithy.util.Generator;
import io.javasmithy.view.Sprite;
import javafx.scene.image.Image;

/**
 * @author Peter Hyde-Smith
 */
public class Monster implements Entity {

    private MonsterType monsterType;
    private String name;
    private int speed;
    private int movePoints;
    private int hitDie;
    private int hitDieQty;
    private int hpBonus;
    private HitPoints hitPoints;
    private int armorClass;
    private int dmgDie;
    private int dmgDieQty;
    private int dmgBonus;
    private boolean isDead = false;
    private boolean standardAction = true;
    private int xPos, yPos;
    private int xpValue;
    private int atkBonus;
    private int atkRange;
    private Sprite sprite;


    public void initHP(){
        this.hitPoints = new HitPoints(hitDie, hitDieQty, hpBonus);
    }


    /**
     * Simulates an attack roll vs entity armor class and also calls takeDamage()
     * outputs entries to game log.
     *@param entity the entity to attack
     */
    @Override
    public void attack(Entity entity) {
        int atkRoll = Generator.generate(20, 1);
        atkRoll += getAttackBonus();
        GameLog.addEntry(this.name + " rolled a " + atkRoll + ".");
        if (atkRoll+getAttackBonus() >= entity.getArmorClass()){
            int dmg = this.getDamage();
            GameLog.addEntry("Hit! " + entity.getName() + " takes " + dmg + " damage!");
            entity.takeDamage(dmg);
        } else {
            System.out.println("Miss!");
        }
    }

    @Override
    public String getName(){
        return this.name;
    }

    /**
     * Decreases hitpoints by damage
     * @param damage the amount of damage to take
     */
    @Override
    public void takeDamage(int damage) {
        this.hitPoints.decreaseCurrentHitPoints(damage);
        if (this.hitPoints.getCurrentHP() <= 0){
            setIsDead(true);
            this.movePoints = 0;
            this.standardAction = false;
        }
    }

    /**
     * Checks for entity to be alive, in range and that this monster is not dead.
     * @param entity the target
     */
    @Override
    public boolean canAttackTarget(Entity entity) {
        double dist = Distance.compute(getColumn(), getRow(), entity.getColumn(), entity.getRow());
        System.out.println("Log: distance " + dist);
        return (!this.isDead() &&
                !entity.isDead() &&
                dist <= this.atkRange);
    }

    @Override
    public int getArmorClass() {
        return this.armorClass;
    }

    @Override
    public int getAttackBonus() {
        return this.atkBonus;
    }

    @Override
    public int getDamage() {
        return Generator.generate(dmgDie, dmgDieQty) + this.dmgBonus;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        this.xPos += deltaX;
        this.yPos += deltaY;
    }

    public int getColumn() {
        return this.sprite.getColumn();
    }
    public void setColumn(int column){this.sprite.setColumn(column);}
    public int getRow(){
        return this.sprite.getRow();
    }
    public void setRow(int row){this.sprite.setRow(row);}

    @Override
    /**
     * Checks if monster is considered 'dead'.
     * @return true or false
     */
    public boolean isDead(){
        return isDead;
    }
    /**
     * Used by take damage to set monster death. Adds a GameLog entry, changes sprite and sets move and action points to zero.
     * @param status boolean
     */
    public void setIsDead(boolean status){
        this.isDead = status;
        if (isDead()) {
            GameLog.addEntry(this.getName() + " died!");
            Image deathSprite = new Image (getClass().getResource( "/assets/img/death-sprite-50px.png").toExternalForm());
            this.sprite.setImage(deathSprite);
            this.movePoints = 0;
            this.standardAction = false;
        }
    }

    public int getXpValue(){
        return this.xpValue;
    }

    public MonsterType getMonsterType(){
        return this.monsterType;
    }
    public void setMonsterType(MonsterType type){this.monsterType = type;}

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        this.movePoints = this.speed;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    public void setHitDieQty(int hitDieQty) {
        this.hitDieQty = hitDieQty;
    }

    public void setHpBonus(int hpBonus) {
        this.hpBonus = hpBonus;
    }

    public void setHitPoints(HitPoints hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public void setDmgDie(int dmgDie) {
        this.dmgDie = dmgDie;
    }

    public void setDmgDieQty(int dmgDieQty) {
        this.dmgDieQty = dmgDieQty;
    }

    public void setDmgBonus(int dmgBonus) {
        this.dmgBonus = dmgBonus;
    }

    public void setPos(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setXpValue(int xpValue) {
        this.xpValue = xpValue;
    }

    public void setAtkBonus(int atkBonus) {
        this.atkBonus = atkBonus;
    }

    public void setAtkRange(int atkRange) {
        this.atkRange = atkRange;
    }

    public HitPoints getHp() {
        return this.hitPoints;
    }

    public Sprite getSprite(){
        return this.sprite;
    }
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
        this.sprite.setEntity(this);
    }
    public void setSpriteGrid(PointGrid grid){
        this.sprite.setGrid(grid);
    }

    /**
     * Checks if move points are greater than zero.
     */
    @Override
    public boolean canMove(){
        return this.movePoints > 0;
    }
    /**
     * Decrease movement points by one.
     */
    @Override
    public void decMovePoints(){
        this.movePoints--;
    }
    /**
     * Sets move points to zero.
     */
    public void holdMove(){
        this.movePoints = 0;
    }
    /**
     * Set movement points to speed (based on grid movement).
     */
    public void resetMovePoints(){
        this.movePoints = this.speed;
    }
    public int getMovePoints() {return this.movePoints;}

    /**
     * Checks if monster can use a 'standard action'.
     */
    public boolean canUseAction(){
        return this.standardAction;
    };
    /**
     * Resets action flag to true.
     */
    public void resetAction(){
        this.standardAction = true;
    };
    /**
     * Sets action flag to false preventing further action til reset.
     */
    public void useAction(){
        this.standardAction = false;
    };

    public Monster copy(){
        Monster monster = new Monster();
        monster.setMonsterType(this.monsterType);
        monster.setName(this.name);
        monster.setSpeed(this.speed);
        monster.setHitDie(this.hitDie);
        monster.setHitDieQty(this.hitDieQty);
        monster.setHpBonus(this.hpBonus);
        monster.setArmorClass(this.armorClass);
        monster.setDmgDie(this.dmgDie);
        monster.setDmgDieQty(this.dmgDieQty);
        monster.setDmgBonus(this.dmgBonus);
        monster.setIsDead(false);
        monster.setPos(this.xPos, this.yPos);
        monster.setXpValue(this.xpValue);
        monster.setAtkBonus(this.atkBonus);
        monster.setAtkRange(this.atkRange);
        monster.initHP();
        return monster;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String debugToString(){
        return "Monster{" +
                "monsterType=" + monsterType +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                ", hitDie=" + hitDie +
                ", hitDieQty=" + hitDieQty +
                ", hpBonus=" + hpBonus +
                ", hitPoints=" + hitPoints +
                ", armorClass=" + armorClass +
                ", dmgDie=" + dmgDie +
                ", dmgDieQty=" + dmgDieQty +
                ", dmgBonus=" + dmgBonus +
                ", isDead=" + isDead +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", xpValue=" + xpValue +
                ", atkBonus=" + atkBonus +
                ", atkRange=" + atkRange +
                '}';
    }
}

