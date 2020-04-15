package io.javasmithy.controller.scene.game;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.model.entity.Entity;
import io.javasmithy.view.Sprite;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GamePaneController implements Initializable {

    private GameController gc;

    @FXML
    AnchorPane gamePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setGameController(GameController gc) {
        this.gc = gc;
        initPlayerSprite();
        //initMonsterSprites();
    }

    public void initPlayerSprite(){
        this.gc.getPlayerCharacter().getSprite().setSpriteParent(this.gamePane);
        // this could be gc.getPcSprite.setSpriteParent()
        setUserKeyInput(this.gc.getPlayerCharacter().getSprite());
    }

    public void initMonsterSprites(){
        List<Entity> monsters = this.gc.getMonsters();
        for (int i = 0; i < monsters.size(); i++){
            monsters.get(i).getSprite().setSpriteParent(this.gamePane);
        }
    }

    public void setUserKeyInput(Sprite charSprite){
        this.gamePane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                System.out.println("UP");
                charSprite.moveRow(-1);
            } else if (e.getCode() == KeyCode.DOWN) {
                System.out.println("down");
                charSprite.moveRow(1);
            } else if (e.getCode() == KeyCode.LEFT) {
                System.out.println("left");
                charSprite.moveColumn(-1);
            } else {//(e.getCode() == KeyCode.RIGHT){
                System.out.println("right");
                charSprite.moveColumn(1);
            }
        });
    }

}
