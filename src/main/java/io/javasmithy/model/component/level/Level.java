package io.javasmithy.model.component.level;

public enum Level{
    LEVEL_1_XP(1, 0),
    LEVEL_2_XP(2, 300),
    LEVEL_3_XP(3, 900),
    LEVEL_4_XP(4, 2700),
    LEVEL_5_XP(5, 6500),
    LEVEL_6_XP(6, 14000),
    LEVEL_7_XP(7, 23000),
    LEVEL_8_XP(8, 34000),
    LEVEL_9_XP(9, 48000),
    LEVEL_10_XP(10, 64000),
    LEVEL_11_XP(11, 85000),
    LEVEL_12_XP(12, 100000),
    LEVEL_13_XP(13, 120000),
    LEVEL_14_XP(14, 140000),
    LEVEL_15_XP(15, 165000),
    LEVEL_16_XP(16, 195000),
    LEVEL_17_XP(17, 225000),
    LEVEL_18_XP(18, 265000),
    LEVEL_19_XP(19, 305000),
    LEVEL_20_XP(20, 355000);

    private int reqExp;
    private int level;
    private Level(int lvl, int reqExp){
        this.level = lvl;
        this.reqExp = reqExp;
    }

    public int getProfBonus(){
        int profBonus = 0;
        profBonus = (int) Math.ceil(this.level/4.0) +1;
        return profBonus;
    }

    public int getReqExp(){
        return this.reqExp;
    }

    public int getLevel(){
        return this.level;
    }
}