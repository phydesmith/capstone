package io.javasmithy.controller.game;

import io.javasmithy.model.component.ability.AbilityScoreFactory;
import io.javasmithy.model.component.background.Background;
import io.javasmithy.model.component.cclass.CClass;
import io.javasmithy.model.component.race.Race;
import io.javasmithy.model.entity.*;

//  Testing Purposes
import io.javasmithy.model.entity.monster.Monster;
import io.javasmithy.model.entity.monster.MonsterFactory;
import io.javasmithy.model.entity.monster.MonsterType;
import io.javasmithy.model.position.PointGrid;
import io.javasmithy.model.room.EncounterRoom;
import io.javasmithy.model.room.Room;
import io.javasmithy.util.Direction;
import io.javasmithy.view.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class GameController{
    Room currentRoom;
    Entity playerCharacter;
    Entity playerTarget;
    PointGrid pGrid;

    
    public GameController(){
        this.pGrid = new PointGrid(16, 16, 0, 0,  50);
        //this.playerCharacter = new CharacterEntity();
        //  FOR DEBUGGING
        this.playerCharacter = createDebugChar();
        //this.playerCharacter.setSprite(new Sprite(pGrid));
        //this.playerCharacter.getSprite().setImage(new Image (getClass().getClassLoader().getResource( "assets/img/m-warrior-sprite-50px.png").toExternalForm()) );
    }

    public CharacterEntity getPlayerCharacter(){
        return (CharacterEntity)this.playerCharacter;
    }

    public void setCurrentRoom(Room room){
        this.playerCharacter.setSprite(new Sprite(room.getGrid()));
        this.playerCharacter.getSprite().setImage(new Image (getClass().getResource( "/assets/img/m-warrior-sprite-50px.png").toExternalForm()) );
        this.currentRoom = room;
        playerCharacter.getSprite().setGrid(room.getGrid());
        this.pGrid = room.getGrid();
        ((EncounterRoom)this.currentRoom).randomizeMonsterStarts();
    }
    public Room getCurrentRoom(){
        return this.currentRoom;
    }


    public void run(){
        while (!this.playerCharacter.isDead()) {
            System.out.println("Starting Room Combat");

            handlePlayerMovement();
            handlePlayerAttacks();

            System.out.println("Monster");
            handleMonsterMovement();
            handleMonsterAttacks();

            resetAllMovePoints();
        }
    }

    public void handlePlayerMovement(){
        System.out.println("Log: Handling Player Movement");
        while (this.playerCharacter.canMove()){
            System.out.print("");
        }
        System.out.println("Log: Player move finished.");
    }
    public void handlePlayerAttacks(){
        System.out.println("Log: Handling Player Attacks");
        playerCharacter.resetAction();
        while (this.playerCharacter.canUseAction()){
            System.out.print("");
        }
        if (playerTarget != null && this.playerCharacter.canAttackTarget(playerTarget)) {this.playerCharacter.attack(playerTarget);}
        System.out.println("Log: Player Attack finished.");
    }
    public void setPlayerTarget(Entity target){
        this.playerTarget = target;
        System.out.println("Log: Player target set to " + this.playerTarget);
    }
    public void handleMonsterMovement(){
        System.out.println("Log: Handling Monster Movement");
        for (Entity monster : this.currentRoom.getEntities()){

            while(monster.canMove() && !monster.isDead()){

                System.out.println("Log: " + monster.getName() +" moving");
                if (!checkCollisionMap(monster)) {
                    if (monster.getColumn() > playerCharacter.getColumn()){
                        monster.getSprite().moveColumn(-1);
                    } else if (monster.getColumn() < playerCharacter.getColumn()){
                        monster.getSprite().moveColumn(1);
                    } else if (monster.getRow() > playerCharacter.getRow()){
                        monster.getSprite().moveRow(-1);
                    } else if (monster.getRow() < playerCharacter.getRow()) {
                        monster.getSprite().moveRow(1);
                    }
                    monster.decMovePoints();
                } else {
                    System.out.println("Log: " + monster.getName() + " Holding Move");
                    ((Monster)monster).holdMove();
                    break;
                }

                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }
    public boolean checkCollisionMap(Entity mover){
        System.out.println("Log: checking " + mover.getName() + " collisions.");
        if (this.pGrid.checkCollision(mover.getRow(), mover.getColumn()+1)) {
            return true;
        } else if (this.pGrid.checkCollision(mover.getRow()+1, mover.getColumn()-1)){
            return true;
        } else if (this.pGrid.checkCollision(mover.getRow(), mover.getColumn()+1)) {
            return true;
        } else if (this.pGrid.checkCollision(mover.getRow(), mover.getColumn()-1)) {
            return true;
        } else if (this.pGrid.checkCollision(mover.getRow()-1, mover.getColumn()+1)) {
            return true;
        } else if (this.pGrid.checkCollision(mover.getRow()-1, mover.getColumn()-1)){
            return true;
        } else if (this.pGrid.checkCollision(mover.getRow()+1, mover.getColumn())) {
            return true;
        } else if (this.pGrid.checkCollision(mover.getRow()-1, mover.getColumn())) {
            return true;
        } else {
            return false;
        }
    }

    public void handleMonsterAttacks(){
        for (Entity entity : this.currentRoom.getEntities()) {
            if(entity.canAttackTarget(playerCharacter)){
                entity.attack(this.playerCharacter);
            }
        }
    }

    public void resetAllMovePoints(){
        playerCharacter.resetMovePoints();
        for (Entity monster : this.currentRoom.getEntities()) {
            monster.resetMovePoints();
        }
    }


    //  Debugging
    public String toString(){
        String str = "";
        //str += this.playerCharacter.toString();
        //str += this.currentRoom.toString();
        return str;
    }
    public CharacterEntity createDebugChar(){
        CharacterEntity c = new CharacterEntity();
        c.setName("TEST");
        c.setDescription("TEST DESC");
        List<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++){
            a.add(10);
        }
        c.setRace(Race.DRAGONBORN);
        c.setAbilityScores(AbilityScoreFactory.createAbilityScores(a, c.getRace()));
        c.setCClass(CClass.FIGHTER);
        c.setBackground(Background.ACOLYTE);
        return c;
    }



}

