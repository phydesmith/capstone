package io.javasmithy.controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;
import javafx.fxml.Initializable;

public class CharacterInformationController implements Initializable, SceneController {

    GameController gc;

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }




}