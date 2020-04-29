package io.javasmithy.model.entity;

import io.javasmithy.model.component.level.*;
import io.javasmithy.model.component.ability.Ability;
import io.javasmithy.model.component.ability.AbilityScores;
import io.javasmithy.model.component.alignment.Alignment;
import io.javasmithy.model.component.cclass.CClass;
import io.javasmithy.model.component.background.Background;
import io.javasmithy.model.component.hitpoints.*;
import io.javasmithy.model.component.race.Race;
import io.javasmithy.model.item.Armor;
import io.javasmithy.model.item.Item;
import io.javasmithy.model.item.Weapon;
import io.javasmithy.model.item.WeaponType;
import io.javasmithy.util.Distance;
import io.javasmithy.util.Generator;
import io.javasmithy.view.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class CharacterEntity implements Entity{
    
    //  Biographical Information
    private String charName = "DEFAULT";
    private Alignment alignment;
    private int age;
    private String description;

    //  Character Base Mechanics and Stats
    private Race race;
    private AbilityScores abilityScores;
    private CClass cClass;
    private Background background;
    private int speed;
    private int movePoints;

    //  Character Counters
    private HitPoints hp;
    private Level level;
    private int currentXP;
    private int gold;

    //  Character Static Combat Mechanics
    boolean standardAction = true;
    
    //  Character Calculated Interaction and Combat Mechanics
    //  saving throws
    //  attack rolls bonus
    //  initiative bonus
    //  skill modifiers

    //  Inventory and Items
    private List<Item> items;
    private Armor armor;
    private Weapon weapon;

    //  Positional
    private int xPos;
    private int yPos;

    // status
    private boolean isDead=false;

    //  GUI-related
    private Sprite sprite;

    public CharacterEntity(){
        init();
    }

    private void init(){
        initLevel();
        this.items = new ArrayList<Item>();

        //  Testing
        initTestWeapon();
    }

    public void setAbilityScores(AbilityScores abilityScores){
        System.out.println("DEBUG " + abilityScores);
        this.abilityScores = abilityScores;
    }
    public AbilityScores getAbilityScores(){
        return this.abilityScores;
    }
    
    public Alignment getAlignment() {
        return this.alignment;
    }
    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public CClass getCClass() {
        return cClass;
    }
    public void setCClass(CClass cClass) {
        this.cClass = cClass;
        initHP();
    }
    public void initHP(){
        this.hp = new HitPoints(this.abilityScores.getModifier(Ability.CONSTITUTION), this.cClass.getHitDie());
    }

    public Background getBackground(){
        return this.background;
    }
    public void setBackground(Background background){
        this.background = background;
    }

    public String getName() {
        return charName;
    }
    public void setName(String charName) {
        this.charName = charName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel(){
        return this.level;
    }
    private void initLevel(){
        //  Sets level to Level 1
        this.level = Level.getNewLevel(1);
        this.setCurrentXP(this.level.getReqExp());
    }
    private void setLevel(int i ){
        this.level = Level.getNewLevel(i);
    }
    private void levelUp(){
        this.level = Level.getNewLevel(this.level.getLevelValue()+1);
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public HitPoints getHp() {
        return hp;
    }
    public void setHp(HitPoints hp) {
        this.hp = hp;
    }

    public int getCurrentXP() {
        return currentXP;
    }
    public void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }
    public void addXP(int delta){
        this.currentXP += delta;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public Race getRace(){
        return this.race;
    }
    public void setRace(Race race){
        this.race = race;
        this.speed = race.getSpeed();
        this.movePoints = this.speed;
        System.out.println("DEBUG SETTING MOVE POINTS: " + this.movePoints);
    }

    @Override
    public void attack(Entity entity){
        int atkRoll = Generator.generate(20, 1);
        atkRoll += getAttackBonus();
        System.out.println(this.charName + " rolled a " + atkRoll + ".");
        if (atkRoll+getAttackBonus() >= entity.getArmorClass()){
            int dmg = this.getDamage();
            System.out.println("Hit! " + entity.getName() + " takes " + dmg + " damage!");
            entity.takeDamage(dmg);
        }  else {
            System.out.println("Miss!");
        }

        /*
        int atkRoll = Generator.generate(20, 1);
        if (atkRoll+getAttackBonus() >= entity.getArmorClass()){
            entity.takeDamage(this.getDamage());
        }
        */
    }
    public int getArmorClass(){
        return 10 + this.abilityScores.getModifier(Ability.DEXTERITY);
        // get armor AC modifiers
    }

    @Override
    public int getAttackBonus(){
        if (this.weapon != null){
            if (this.weapon.getType() == WeaponType.MELEE){
                return getMeleeAttackBonus();
            } else {
                return getRangedAttackBonus();
            }
        } else {
            return getMeleeAttackBonus();
        }
    }
    private int getMeleeAttackBonus(){
        return this.level.getProfBonus() + this.abilityScores.getModifier(Ability.STRENGTH);
    }
    private int getRangedAttackBonus(){
        return this.level.getProfBonus() + this.abilityScores.getModifier(Ability.DEXTERITY);
    }

    @Override
    public int getDamage(){
        if ( this.weapon != null){
            return Generator.generate(this.weapon.getDmgDie(), this.weapon.getDmgDieQty());
        } else {
            return Generator.generate(2, 1);
        }

    }

    public void takeDamage(int dmg){
        this.hp.decreaseCurrentHitPoints(dmg);
        if (this.hp.getCurrentHP()<= 0){
            this.isDead = true;
        }
    }

    public boolean canAttackTarget(Entity entity){
        return Distance.compute(getColumn(), getRow(), entity.getColumn(), getRow()) <= this.weapon.getAtkRange();
    }

    public void addItem(Item item){
        this.items.add(item);
    }
    public Item getItem(int index){
        return this.items.get(index);
    }

    public Armor getArmor(){
        return this.armor;
    }
    public void setArmor(Armor armor){
        this.armor = armor;
    }
    public Weapon getWeapon(){
        return this.weapon;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        this.xPos += deltaX;
        this.yPos += deltaY;
    }

    public void setX(int xPos){
        this.xPos = xPos;
    }
    public void setY(){
        this.yPos = yPos;
    }

    public int getColumn() {
        return this.sprite.getColumn();
    }
    public void setColumn(int column){this.sprite.setColumn(column);}
    public int getRow(){
        return this.sprite.getRow();
    }
    public void setRow(int row){this.sprite.setRow(row);}


    public boolean isDead(){
        return this.isDead;
    }
    public void setIsDead(Boolean status){
        this.isDead = status;
    }

    public Sprite getSprite(){ return this.sprite;}
    @Override
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
        this.sprite.setEntity(this);
    }
    public void setSpriteImage(Image image){
        this.sprite.setImage(image);
    }

    @Override
    public boolean canMove(){
        //System.out.println("Player can move: " + (this.movePoints > 0));
        return this.movePoints > 0;
    }
    @Override
    public void decMovePoints(){
        this.movePoints--;
    }
    public void resetMovePoints(){
        this.movePoints = this.speed;
    }
    public int getMovePoints(){
        return this.movePoints;
    }
    public void holdMove(){this.movePoints = 0;}


    public boolean canUseAction(){
        return this.standardAction;
    };
    public void resetAction(){
        this.standardAction = true;
    };
    public void useAction(){
        this.standardAction = false;
    };

    public String toStringNoName(){
        String str = "";
        str += "\n Alignment: " + this.alignment
            +"\n Race: \t" + this.race
            + "\n Background: \t" + this.background
            + "\n Class: \t" + this.cClass
            + "\n\n HP: " + this.hp
            + "\n" + this.abilityScores
            + "\n\n\tDescription:\n" + this.description;
        return str;
    }

    public String toString(){
        String str = "";
        str += "\n Name: \t" + this.charName 
            + toStringNoName();
        return str;
    }

    // TESTING
    public void initTestWeapon(){
        this.weapon = new Weapon();
        this.weapon.setType(WeaponType.MELEE);
        this.weapon.setAtkRange(1);
        this.weapon.setDmgDie(6);
        this.weapon.setDmgDieQty(1);
        this.weapon.setEquipped(true);
        this.weapon.setOwner(this);
        this.weapon.setValue(100);
        this.weapon.setName("Long Sword");
    }
}