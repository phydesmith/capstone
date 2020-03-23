package io.javasmithy.model.component.level;

public enum Level{
    LEVEL_1(1, 0),
    LEVEL_2(2, 300),
    LEVEL_3(3, 900),
    LEVEL_4(4, 2700),
    LEVEL_5(5, 6500),
    LEVEL_6(6, 14000),
    LEVEL_7(7, 23000),
    LEVEL_8(8, 34000),
    LEVEL_9(9, 48000),
    LEVEL_10(10, 64000),
    LEVEL_11(11, 85000),
    LEVEL_12(12, 100000),
    LEVEL_13(13, 120000),
    LEVEL_14(14, 140000),
    LEVEL_15(15, 165000),
    LEVEL_16(16, 195000),
    LEVEL_17(17, 225000),
    LEVEL_18(18, 265000),
    LEVEL_19(19, 305000),
    LEVEL_20(20, 355000);

    private int reqExp;
    private int levelValue;
    private Level(int lvl, int reqExp){
        this.levelValue = lvl;
        this.reqExp = reqExp;
    }

    public int getProfBonus(){
        int profBonus = 0;
        profBonus = (int) Math.ceil(this.levelValue/4.0) +1;
        return profBonus;
    }

    public int getReqExp(){
        return this.reqExp;
    }

    public int getLevelValue(){
        return this.levelValue;
    }

    public static Level getNewLevel(int value){
        Level newLevel = LEVEL_1;
        for (Level level : Level.values()) {
            if (level.getLevelValue()==value) {newLevel = level;}
        }
        return newLevel;
    }

    public String toString(){
        return this.levelValue+"";
    }
}