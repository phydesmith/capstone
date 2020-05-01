package io.javasmithy.controller.game;

import io.javasmithy.model.component.ability.AbilityScoreFactory;
import io.javasmithy.model.component.background.Background;
import io.javasmithy.model.component.cclass.CClass;
import io.javasmithy.model.component.race.Race;
import io.javasmithy.model.entity.*;

import io.javasmithy.model.entity.monster.Monster;
import io.javasmithy.model.item.armor.Armor;
import io.javasmithy.model.item.armor.ArmorFactory;
import io.javasmithy.model.item.armor.ArmorType;
import io.javasmithy.model.item.weapons.Weapon;
import io.javasmithy.model.item.weapons.WeaponFactory;
import io.javasmithy.model.item.weapons.WeaponType;
import io.javasmithy.model.position.PointGrid;
import io.javasmithy.model.room.EncounterRoom;
import io.javasmithy.model.room.Room;
import io.javasmithy.model.room.RoomType;
import io.javasmithy.util.GameLog;
import io.javasmithy.view.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/** Non-Gui controller that controls game logic
 * @author Peter Hyde-Smith
 *
 */
public class GameController{
    /**
     * Current room configurations loaded into controller. Contains things like size and monster count.
     */
    private Room currentRoom;

    /**
     * Player character object
     */
    private Entity playerCharacter;

    /**
     * Entity that is target of player, set by GUI and used to attack targets.
     */
    private Entity playerTarget;

    /**
     * Current Grid used by Controller - used to check collisions, loaded from room grid.
     */
    private PointGrid pGrid;

    
    public GameController(){
        this.pGrid = new PointGrid(16, 16, 0, 0,  50);
        this.playerCharacter = createDebugChar();
    }

    /**
     * @return CharacterEntity that represents player
     */
    public CharacterEntity getPlayerCharacter(){
        return (CharacterEntity)this.playerCharacter;
    }

    /** Sets current room for game controller to load correct elements and set the correct PGrid to.
     * @param room room config to load
     */
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

    /** Main game loop, consists of move and attack phase, used for rooms containing combat
     *  is run on a different thread to prevent GUI from locking.
     */
    public void run(){
        if (((EncounterRoom)this.currentRoom).getRoomType()== RoomType.ROOM_0) return;
        while (!this.playerCharacter.isDead()) {


            GameLog.addEntry(this.playerCharacter.getName() + " Move Phase.");
            System.out.println("Log: Player Move Phase");
            handlePlayerMovement();
            GameLog.addEntry(this.playerCharacter.getName() + " attack Phase.");
            System.out.println("Log: Player attack Phase");
            handlePlayerAttacks();

            GameLog.addEntry("Monster Move Phase.");
            System.out.println("Log: Monster Move Phase");
            handleMonsterMovement();
            GameLog.addEntry("Monster attack Phase.");
            System.out.println("Log: Monster attack Phase");
            handleMonsterAttacks();

            resetAllMovePoints();
        }
        System.out.println("Log: character is dead -> " + this.playerCharacter.isDead());
        GameLog.addEntry("Character Died - Game Over!");
    }

    /** Handles player movements - loop runs until player's move points have run out.
     *
     */
    public void handlePlayerMovement(){
        System.out.println("Log: Handling Player Movement");
        while (this.playerCharacter.canMove()){
            System.out.print("");
        }
        System.out.println("Log: Player move finished.");
    }

    /**Handles player attacks, loop runs until player's actions have run out
     * also checks that player target an attk range are valid.
     *
     */
    public void handlePlayerAttacks(){
        if (!playerCharacter.canUseAction()) return;
        System.out.println("Log: Handling Player Attacks");
        while (this.playerCharacter.canUseAction()){
            System.out.print("");
        }
        if (playerTarget != null && this.playerCharacter.canAttackTarget(playerTarget)) {this.playerCharacter.attack(playerTarget);}
        System.out.println("Log: Player attack finished.");
        playerCharacter.resetAction();
    }
    /** Sets player target to entity parameter to allow attacks.
     * @param target Entity to set target to.
     */
    public void setPlayerTarget(Entity target){
        this.playerTarget = target;
        System.out.println("Log: Player target set to " + this.playerTarget);
    }
    /** Loop through room monsters and process their movement.
     *
     */
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
    /** Before each move, monster checks all surrounding squares for collision and will not move if it detects one.
     * @param mover the entity that needs to move
     * @return true or false
     */
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
    /** Loops through room monsters and checks for and process valid attacks
     *
     */
    public void handleMonsterAttacks(){
        for (Entity entity : this.currentRoom.getEntities()) {
            if(entity.canAttackTarget(playerCharacter)){
                entity.attack(this.playerCharacter);
            }
        }
    }
    /** When round is completed reset all move points. Checks for entities death-state.
     *
     */
    public void resetAllMovePoints(){
        if (playerCharacter.isDead()) return;
        playerCharacter.resetMovePoints();
        for (Entity monster : this.currentRoom.getEntities()) {
            monster.resetMovePoints();
        }
    }


    //  Debugging
    public String toString(){
        String str = "";
        return str;
    }

    /** Automatically generates a debugging character for testing on GUI without having to recreate a character through character creation.
     * @return a new character entity set up for testing
     */
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

        Weapon weapon = WeaponFactory.createWeapon(WeaponType.SPEAR);
        Armor armor = ArmorFactory.createArmor(ArmorType.LEATHER);
        c.setWeapon(weapon);
        c.setArmor(armor);
        c.addItemToInventory(weapon);
        c.addItemToInventory(armor);
        return c;
    }

}

