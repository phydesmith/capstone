package io.javasmithy.controller.scene;

import io.javasmithy.controller.game.GameController;
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
import java.util.ResourceBundle;

public class GamePaneController implements Initializable {

    private GameController gc;
    private Scene gameScene;
    private Sprite charSprite;

    @FXML
    AnchorPane gamePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("DEBUG - Game Controller");
    }

    public void setGameScene(Scene scene){
        this.gameScene = scene;
        setUserKeyInput();
    }
    public void setUserKeyInput(){
        System.out.println(this.gamePane);

        this.gamePane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                System.out.println("UP");
                this.charSprite.moveRow(-1);
            } else if (e.getCode() == KeyCode.DOWN) {
                System.out.println("UP");
                this.charSprite.moveRow(1);
            } else if (e.getCode() == KeyCode.LEFT) {
                System.out.println("UP");
                this.charSprite.moveColumn(-1);
            } else if (e.getCode() == KeyCode.RIGHT){
                System.out.println("UP");
                this.charSprite.moveColumn(1);
            } else {};
        });

        System.out.println(this.gamePane.getOnKeyPressed());
    }

    public void handleOnKeyPressed(KeyEvent e){
        if (e.getCode() == KeyCode.UP) {
            System.out.println("UP");
            this.charSprite.moveRow(-1);
        } else if (e.getCode() == KeyCode.DOWN) {
            System.out.println("UP");
            this.charSprite.moveRow(1);
        } else if (e.getCode() == KeyCode.LEFT) {
            System.out.println("UP");
            this.charSprite.moveColumn(-1);
        } else if (e.getCode() == KeyCode.RIGHT){
            System.out.println("UP");
            this.charSprite.moveColumn(1);
        } else {};
    }

    public void setGameController(GameController gc) {
        this.gc = gc;
        setSprite();
    }
    public void setSprite(){
        this.charSprite = this.gc.getSprite();
        this.gamePane.getChildren().add(this.charSprite);
    }

    public Sprite getSprite(){
        return this.charSprite;
    }
}
