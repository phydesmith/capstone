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
import io.javasmithy.view.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class GameController{
    Room currentRoom;
    Entity playerCharacter;
    PointGrid pGrid;
    
    public GameController(){
        this.pGrid = new PointGrid(16, 16, 0, 0,  50);
        //this.playerCharacter = new CharacterEntity();
        //  FOR DEBUGGING
        this.playerCharacter = createDebugChar();
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
        while (!this.playerCharacter.isDead()) {
            System.out.println("Running");
            handlePlayerMovement();
            handleMonsterMovement();
            resetAllMovePoints();
        }
    }

    public void handlePlayerMovement(){
        System.out.println("DEBUG Handling Player Movement");
        while (this.playerCharacter.canMove()){
            //System.out.println("DEBUG Move Points " + ((CharacterEntity)playerCharacter).getMovePoints());
            System.out.print("");
        }
        System.out.println("Reset Player MovePoints");
    }
    public void handleMonsterMovement(){
        System.out.println("DEBUG Handling Monster Movement");
        for (Entity monster : this.currentRoom.getEntities()){
            System.out.println("DEBUG: looping through monsters");

            while(monster.canMove() && !monster.isDead()){
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
                    ((Monster)monster).holdMove();
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
        System.out.println("\nChecking Collision");
        if (this.pGrid.checkCollision(mover.getColumn()+1, mover.getRow()+1)) {
            System.out.println("DEBUG - Check collision map: 1");
            return true;
        } else if (this.pGrid.checkCollision(mover.getColumn()+1, mover.getRow()-1)){
            System.out.println("DEBUG - Check collision map: 2");
            return true;
        } else if (this.pGrid.checkCollision(mover.getColumn(), mover.getRow()+1)) {
            System.out.println("DEBUG - Check collision map: 3");
            return true;
        } else if (this.pGrid.checkCollision(mover.getColumn(), mover.getRow()-1)) {
            System.out.println("DEBUG - Check collision map: 4");
            return true;
        } else if (this.pGrid.checkCollision(mover.getColumn()-1, mover.getRow()+1)) {
            System.out.println("DEBUG - Check collision map: 5");
            return true;
        } else if (this.pGrid.checkCollision(mover.getColumn()-1, mover.getRow()-1)){
            System.out.println("DEBUG - Check collision map: 6");
            return true;
        } else if (this.pGrid.checkCollision(mover.getColumn()+1, mover.getRow())) {
            System.out.println("DEBUG - Check collision map: 7");
            return true;
        } else if (this.pGrid.checkCollision(mover.getColumn()-1, mover.getRow())) {
            System.out.println("DEBUG - Check collision map: 8");
            return true;
        } else {
            System.out.println("\nDEBUG - Check collision map: no collision");
            return false;
        }
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

