package io.javasmithy.model.entity.monster;


public class MonsterConfig {
    private MonsterType monsterType;
    private String name;
    private int speed;
    private int hitDie;
    private int hitDieQty;
    private int hpBonus;
    private int armorClass;
    private int dmgDie;
    private int dmgDieQty;
    private int dmgBonus;
    private int xpValue;
    private int atkBonus;
    private int atkRange;

    public Monster generate(){
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
        monster.setXpValue(this.xpValue);
        monster.setAtkBonus(this.atkBonus);
        monster.setAtkRange(this.atkRange);
        monster.initHP();
        return monster;
    }

}
