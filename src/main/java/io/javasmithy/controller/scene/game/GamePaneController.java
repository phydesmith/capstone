package io.javasmithy.controller.scene.game;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.controller.scene.PaneController;
import io.javasmithy.model.entity.CharacterEntity;
import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.room.EncounterRoom;
import io.javasmithy.model.room.RoomType;
import io.javasmithy.view.Sprite;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GamePaneController implements Initializable, PaneController {

    private GameController gc;

    @FXML
    AnchorPane gamePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setGameController(GameController gc) {
        this.gc = gc;
        if (((EncounterRoom)this.gc.getCurrentRoom()).getRoomType()== RoomType.ROOM_0) return;
        initPlayerSprite();
        initMonsterSprites();
        setUserTargetClicks();
    }

    public void initPlayerSprite(){
        this.gc.getPlayerCharacter().getSprite().setSpriteParent(this.gamePane);
        setUserKeyInput(this.gc.getPlayerCharacter());
    }

    public void initMonsterSprites(){
        List<Entity> monsters = this.gc.getCurrentRoom().getEntities();
        for (int i = 0; i < monsters.size(); i++){
            monsters.get(i).getSprite().setSpriteParent(this.gamePane);
        }
    }

    public void setUserKeyInput(CharacterEntity playerCharacter){
        Sprite charSprite = playerCharacter.getSprite();
        this.gamePane.setOnKeyPressed(e -> {
            if (playerCharacter.canMove()) {
                if (e.getCode() == KeyCode.UP) {
                    System.out.println("Log: Player moved up.");
                    charSprite.moveRow(-1);
                } else if (e.getCode() == KeyCode.DOWN) {
                    System.out.println("Log: Player moved down.");
                    charSprite.moveRow(1);
                } else if (e.getCode() == KeyCode.LEFT) {
                    System.out.println("Log: Player moved left.");
                    charSprite.moveColumn(-1);
                } else {//(e.getCode() == KeyCode.RIGHT){
                    System.out.println("Log: Player moved right.");
                    charSprite.moveColumn(1);
                }
                playerCharacter.decMovePoints();
            }
        });
    }

    public void setUserTargetClicks(){
        this.gamePane.setOnMouseClicked( e-> {
            try {
                Sprite target = (Sprite) e.getTarget();
                gc.setPlayerTarget(target.getEntity());
            } catch (ClassCastException cce){
                System.out.println("Log: Player clicked on AnchorPane and not sprite");
            }
        });
    }

}
