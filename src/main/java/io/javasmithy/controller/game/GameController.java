package io.javasmithy.controller.game;

import io.javasmithy.model.entity.*;

//  Testing Purposes
import io.javasmithy.model.entity.monster.Monster;
import io.javasmithy.model.entity.monster.MonsterFactory;
import io.javasmithy.model.entity.monster.MonsterType;
import io.javasmithy.model.position.PointGrid;
import io.javasmithy.model.room.EncounterRoom;
import io.javasmithy.model.room.Room;
import io.javasmithy.view.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class GameController{
    Room currentRoom;
    Entity playerCharacter;
    PointGrid pGrid;
    
    public GameController(){
        this.pGrid = new PointGrid(12, 16, 0, 0,  50);
        this.playerCharacter = new CharacterEntity();
        this.playerCharacter.setSprite(new Sprite(pGrid));
        this.playerCharacter.getSprite().setImage(new Image (getClass().getClassLoader().getResource( "assets/img/m-warrior-sprite-50px.png").toExternalForm()) );
    }

    public PointGrid getPointGrid(){
        return this.pGrid;
    }

    public CharacterEntity getPlayerCharacter(){
        return (CharacterEntity)this.playerCharacter;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
        //System.out.println("DEBUG - GC PGRID" + this.pGrid);
        //((EncounterRoom)this.currentRoom).setMonsterSpriteGrid();
    }
    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    public void init(){

    }

    public void runDebug(){
        while (!this.currentRoom.getEntities().get(0).isDead() && !this.playerCharacter.isDead()){
            this.currentRoom.getEntities().get(0).attack(this.playerCharacter);
            this.playerCharacter.attack(this.currentRoom.getEntities().get(0));

            System.out.println("\nRound Result");
            System.out.println(((Monster)this.currentRoom.getEntities().get(0)).getHp());
            System.out.println(((CharacterEntity)this.playerCharacter).getHp());
            System.out.println("\n");
        }
    }

    public void run(){
        while (!this.currentRoom.getEntities().get(0).isDead() && !this.playerCharacter.isDead()) {
            System.out.println("Running");
            System.out.println("Handling Player Movement");
            handlePlayerMovement();
            handleMonsterMovement();
            resetAllMovePoints();
        }
    }

    public void handlePlayerMovement(){
        System.out.println("DEBUG Handling Player Movement");
        while (playerCharacter.canMove()){
            System.out.println("DEBUG Move Points " + ((CharacterEntity)playerCharacter).getMovePoints());
        }
        System.out.println("Reset Player MovePoints");
    }
    public void handleMonsterMovement(){
        System.out.println("DEBUG Handling Monster Movement");
        double targetX = this.playerCharacter.getSprite().getGridX();
        double targetY = this.playerCharacter.getSprite().getGridY();
        for (Entity monster : this.currentRoom.getEntities()){
            System.out.println("DEBUG: looping through monster");
            while(monster.canMove() && !monster.isDead()){
                if (detectCollision(monster, this.playerCharacter)){
                    System.out.println("DEBUG : Detect Collision: " + detectCollision(monster, this.playerCharacter));
                    ((Monster)monster).holdMove();
                } else {
                    System.out.println("DEBUG: Monster Moving");
                    if (monster.getSprite().getGridX() < targetX - 1) {
                        monster.getSprite().moveColumn(1);
                    } else if (monster.getSprite().getGridX()> targetX + 1) {
                        monster.getSprite().moveColumn(-1);
                    } else if (monster.getSprite().getGridY() < targetY - 1) {
                        monster.getSprite().moveRow(1);
                    } else if (monster.getSprite().getGridY() > targetY + 1) {
                        monster.getSprite().moveRow(-1);
                    }
                    monster.decMovePoints();
                }
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public boolean detectCollision(Entity mover, Entity target){
        boolean collision = false;
        if (mover.getSprite().getGridX() -1 == target.getSprite().getX()) collision = true;
        if (mover.getSprite().getGridX() +1 == target.getSprite().getX()) collision = true;
        if (mover.getSprite().getGridY() -1 == target.getSprite().getY()) collision = true;
        if (mover.getSprite().getGridY() +1 == target.getSprite().getY()) collision = true;
        return collision;
    }

    public void resetAllMovePoints(){
        playerCharacter.resetMovePoints();
        for (Entity monster : this.currentRoom.getEntities()) {
            monster.resetMovePoints();
        }
    }

    public void processMonstersAttacks(){
        for (Entity entity : this.currentRoom.getEntities()) {
            if(entity.canAttackTarget(playerCharacter)){
                entity.attack(this.playerCharacter);
            }
        }
    }

    public void processMonsterMoves(){

    }

    public String toString(){
        String str = "";
        //str += this.playerCharacter.toString();
        //str += this.currentRoom.toString();
        return str;
    }




}

