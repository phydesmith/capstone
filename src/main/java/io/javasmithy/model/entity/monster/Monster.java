package io.javasmithy.model.entity.monster;

import io.javasmithy.model.component.hitpoints.HitPoints;
import io.javasmithy.model.entity.Entity;
import io.javasmithy.util.Distance;
import io.javasmithy.util.Generator;


public class Monster implements Entity {
    //  private String path = "\\data\\monsters.json
    //  private static ObjectMapper mapper = JsonFactory.create();
    //  private static MonsterList mList = mapper.fromJson(path, MonsterList.class);
    //  private static final Map<Type, Monster> monsterMap = new

    private MonsterType monsterType;
    private String name;
    private int speed;
    private int hitDie;
    private int hitDieQty;
    private int hpBonus;
    private HitPoints hitPoints;
    private int armorClass;
    private int dmgDie;
    private int dmgDieQty;
    private int dmgBonus;
    private boolean isDead = false;
    private int xPos, yPos;
    private int xpValue;
    private int atkBonus;
    private int atkRange;

    public void initHP(){
        this.hitPoints = new HitPoints(hitDie, hitDieQty, hpBonus);
    }


    @Override
    public void attack(Entity entity) {
        int atkRoll = Generator.generate(20, 1);
        atkRoll += getAttackBonus();
        System.out.println(this.name + " rolled a " + atkRoll + ".");
        if (atkRoll+getAttackBonus() >= entity.getArmorClass()){
            int dmg = this.getDamage();
            System.out.println("Hit! " + entity.getName() + " takes " + dmg + " damage!");
            entity.takeDamage(dmg);
        } else {
            System.out.println("Miss!");
        }
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void takeDamage(int damage) {
        this.hitPoints.decreaseCurrentHitPoints(damage);
        if (this.hitPoints.getCurrentHP() <= 0){
            isDead = true;
        }
    }

    @Override
    public boolean canAttackTarget(Entity entity) {
        return Distance.compute(getX(), getY(), entity.getX(), getY()) <= this.atkRange;
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
    public int getX() {
        return xPos;
    }
    public void setX(int xPos){
        this.xPos = xPos;
    }
    public int getY(){
        return yPos;
    }
    public void setY(){
        this.yPos = yPos;
    }

    @Override
    public boolean isDead(){
        return isDead;
    }
    public void setIsDead(boolean status){
        this.isDead = status;
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

