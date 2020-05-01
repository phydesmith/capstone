package io.javasmithy.model.entity;

import io.javasmithy.model.component.attack.AttackType;
import io.javasmithy.model.component.level.*;
import io.javasmithy.model.component.ability.Ability;
import io.javasmithy.model.component.ability.AbilityScores;
import io.javasmithy.model.component.alignment.Alignment;
import io.javasmithy.model.component.cclass.CClass;
import io.javasmithy.model.component.background.Background;
import io.javasmithy.model.component.hitpoints.*;
import io.javasmithy.model.component.race.Race;
import io.javasmithy.model.component.skill.Skill;
import io.javasmithy.model.item.*;
import io.javasmithy.model.item.armor.Armor;
import io.javasmithy.model.item.armor.ArmorFactory;
import io.javasmithy.model.item.armor.ArmorType;
import io.javasmithy.model.item.weapons.Weapon;
import io.javasmithy.model.item.weapons.WeaponFactory;
import io.javasmithy.model.item.weapons.WeaponType;
import io.javasmithy.util.Distance;
import io.javasmithy.util.GameLog;
import io.javasmithy.util.Generator;
import io.javasmithy.view.Sprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

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

    //  Skills
    private List<Skill> skillList;

    //  Inventory and Items
    private ObservableList<Item> inventory;
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
        this.inventory = FXCollections.observableArrayList();
        this.gold = 1000;

        //  Testing
        initTestWeapon();
        initTestArmor();
    }

    public void setAbilityScores(AbilityScores abilityScores){
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

        // For Testing Balance Only
        this.hp.increaseMaximumHitPoints(20);
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
    public void changeGold(int delta) { this.gold += delta;}

    public Race getRace(){
        return this.race;
    }
    public void setRace(Race race){
        this.race = race;
        this.speed = race.getSpeed();
        this.movePoints = this.speed;
    }

    @Override
    public void attack(Entity entity){
        int atkRoll = Generator.generate(20, 1);
        atkRoll += getAttackBonus();
        GameLog.addEntry(this.charName + " rolled a " + atkRoll + ".");
        if (atkRoll+getAttackBonus() >= entity.getArmorClass()){
            int dmg = this.getDamage();
            GameLog.addEntry("Hit " + entity.getName() + " with " + this.weapon.getName() + ". Target takes " + dmg + " damage!");
            entity.takeDamage(dmg);
        }  else {
            System.out.println("Miss!");
        }
    }
    public int getArmorClass(){
        int base = 10 + this.abilityScores.getModifier(Ability.DEXTERITY);
        if (this.armor == null) {return base;} else return base + this.armor.getAcVal();
    }

    @Override
    public int getAttackBonus(){
        if (this.weapon != null){
            if (this.weapon.getAttackType() == AttackType.MELEE){
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
            setIsDead(true);
        }
    }

    public boolean canAttackTarget(Entity entity){
        double dist = Distance.compute(getColumn(), getRow(), entity.getColumn(), entity.getRow());
        System.out.println("Log: distance to attack " + dist);
        return (!this.isDead() &&
                !entity.isDead() &&
                dist <= this.weapon.getAtkRange());
    }

    public void addItemToInventory(Item item){
        this.inventory.add(item);
    }
    public ObservableList<Item> getInventory() { return this.inventory;}
    public Item getItem(int index){
        return this.inventory.get(index);
    }

    public Armor getArmor(){
        return this.armor;
    }
    public void setArmor(Armor armor){
        this.armor = armor;
        this.armor.setEquipped(true);
        this.armor.setOwner(this);
    }
    public Weapon getWeapon(){ return this.weapon; }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
        this.weapon.setEquipped(true);
        this.weapon.setOwner(this);
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
        if (isDead()) {
            GameLog.addEntry(this.getName() + " died!");
            Image deathSprite = new Image (getClass().getResource( "/assets/img/death-sprite-50px.png").toExternalForm());
            this.sprite.setImage(deathSprite);
            this.movePoints = 0;
            this.standardAction = false;
        }
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
    public boolean canMove(){ return this.movePoints > 0;}
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

    public void setSkillList(List<Skill> skillList){
        this.skillList = skillList;
    }
    public int getSkillModifier(Skill skill){
        if (this.skillList.contains(skill)){
            System.out.println("Log: has proficiency, using ability modifier and proficiency bonus.");
            return this.abilityScores.getModifier(skill.getAbility()) + this.level.getProfBonus();
        } else {
            System.out.println("Log: no proficiency, using ability modifier.");
            return this.abilityScores.getModifier(skill.getAbility());
        }
    }
    public boolean skillCheck(Skill skill, int difficultyCheck){
        int roll = Generator.generate(20,1) + getSkillModifier(skill);
        if (roll >= difficultyCheck){
            GameLog.addEntry(this.charName + " rolled " + roll + " vs DC " + difficultyCheck + " on " + skill + " check, success!");
            return true;
        } else {
            GameLog.addEntry(this.charName + " rolled " + roll + " vs DC " + difficultyCheck + " on " + skill + " check, failure!");
            return false;
        }
    }
    public boolean hasSkill(Skill skill){
        return this.skillList.contains(skill);
    }


    public String toStringNoName(){
        String str = "";
        str += "\n Alignment: " + this.alignment
            +"\n Race: \t" + this.race
            + "\n Background: \t" + this.background
            + "\n Class: \t" + this.cClass
            + "\n\n HP: " + this.hp
            + "\n" + this.abilityScores
            + "\n\nSkills: \n" + getFormattedSkillList()
            + "\n Gold: " + this.gold
            + "\n\n\tDescription:\n" + this.description;
        return str;
    }
    private String getFormattedSkillList(){
        String skills = "";
        if (this.skillList.isEmpty()) return skills;
        for (int i = 0; i < this.skillList.size(); i++){
            skills += this.skillList.get(i).getName() + "\n";
        }
        return skills;
    }

    public String toString(){
        String str = "";
        str += "\n Name: \t" + this.charName 
            + toStringNoName();
        return str;
    }

    // TESTING
    public void initTestWeapon(){
        setWeapon(WeaponFactory.createWeapon(WeaponType.SPEAR));
    }

    public void initTestArmor(){
        setArmor(ArmorFactory.createArmor(ArmorType.PLATE));
    }
}