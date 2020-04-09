package io.javasmithy.controller.game;

import io.javasmithy.model.component.hitpoints.HitPoints;
import io.javasmithy.model.entity.*;

//  Testing Purposes
import io.javasmithy.model.entity.monster.Monster;
import io.javasmithy.model.entity.monster.MonsterFactory;
import io.javasmithy.model.entity.monster.MonsterType;
import io.javasmithy.model.position.PointGrid;
import io.javasmithy.view.Sprite;

import java.util.ArrayList;
import java.util.List;

public class GameController{
    Entity playerCharacter;
    Sprite charSprite;
    List<Entity> monsters;
    PointGrid pGrid;
    
    public GameController(){
        PointGrid pGrid = new PointGrid(12, 16, 0, 0,  50);
        this.playerCharacter = new CharacterEntity();
        this.charSprite = new Sprite(pGrid);
        this.monsters = new ArrayList<Entity>();
    }

    public Sprite getSprite(){
        return charSprite;
    }
    public PointGrid getPointGrid(){
        return this.pGrid;
    }

    public void init(){
        monsters.add(MonsterFactory.createMonster(MonsterType.ZOMBIE));
        System.out.println(monsters.get(0));
    }

    public void run(){
        while (!monsters.get(0).isDead() && !this.playerCharacter.isDead()){
            this.monsters.get(0).attack(this.playerCharacter);
            this.playerCharacter.attack(this.monsters.get(0));

            System.out.println("\nRound Result");
            System.out.println(((Monster)monsters.get(0)).getHp());
            System.out.println(((CharacterEntity)this.playerCharacter).getHp());
            System.out.println("\n");

        }
    }

    public CharacterEntity getPlayerCharacter(){
        return (CharacterEntity)this.playerCharacter;
    }

    public void setMonsters(List<Entity> monsters){
        this.monsters = monsters;
    }
    
    public void processMonstersAttacks(){
        for (Entity entity : monsters) {
            if(entity.canAttackTarget(playerCharacter)){
                entity.attack(this.playerCharacter);
            }
        }
    }



}