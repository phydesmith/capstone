package io.javasmithy.model.component.hitpoints;

import io.javasmithy.util.Generator;

public class HitPoints{
    int maxHitPoints;
    int currentHitPoints;
    int hitDie;

    public HitPoints(int hitDie, int hitDieQty, int hitPointBonus){
        this.hitDie = hitDie;
        this.maxHitPoints = Generator.generate(this.hitDie, hitDieQty) + hitPointBonus;
        this.currentHitPoints = maxHitPoints;
    }

    public HitPoints(int conMod, int hitDie){
        this.maxHitPoints = hitDie + conMod;
        this.currentHitPoints = maxHitPoints;
        this.hitDie = hitDie;
    }

    public int getCurrentHP(){
        return this.currentHitPoints;
    }
    public int getMaximumHP(){
        return this.maxHitPoints;
    }
    public int getHitDie(){
        return this.hitDie;
    }

    public void setCurrentHP(int value){
        if (value < 0) {this.currentHitPoints = 0;}
        else if( value > this.maxHitPoints) {this.currentHitPoints = this.maxHitPoints;}
        else {this.currentHitPoints = value;}
    }
    public void setMaxHP(int value){
        if (value < 0) {
            this.maxHitPoints = 0;
            this.currentHitPoints = 0;
        } else if( this.maxHitPoints < this.currentHitPoints) {
            this.maxHitPoints = value;
            this.currentHitPoints = this.maxHitPoints;
        } else {this.maxHitPoints = value;}
    }
    public void setHitDie(int value){
        this.hitDie = value;
    }

    public void increaseMaxHPForLevelUp(){
        this.maxHitPoints += Generator.generate(hitDie, 1);
    }

    public void increaseMaximumHitPoints(int delta){
        this.maxHitPoints += delta;
        this.currentHitPoints = this.maxHitPoints;
    }
    public void decreaseMaximumHitPoints(int delta){
        this.maxHitPoints -= delta;
        this.currentHitPoints = this.maxHitPoints;
    }

    public void increaseCurrentHitPoints(int delta){
        this.currentHitPoints = (this.currentHitPoints+delta)<=this.maxHitPoints ? this.currentHitPoints+delta : this.maxHitPoints;
    }
    public void decreaseCurrentHitPoints(int delta){
        this.currentHitPoints = (this.currentHitPoints-delta)>0 ? this.currentHitPoints-delta : 0;
    }

    public String toString(){
        return currentHitPoints + "/" + maxHitPoints;
    }


}