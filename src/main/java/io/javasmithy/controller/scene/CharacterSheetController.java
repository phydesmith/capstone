package io.javasmithy.controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;

public class CharacterSheetController implements Initializable, SceneController {

    private GameController gc;

    @FXML
    Label name;
    @FXML
    Label race;
    @FXML
    Label cClass;
    @FXML
    Label level;
    @FXML
    Label alignment;
    @FXML
    Label background;
    @FXML
    Label experience;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    public void populate(){
        name.setText(gc.getPlayerCharacter().getCharName());
        race.setText(this.gc.getPlayerCharacter().getRace().toString());
        cClass.setText(this.gc.getPlayerCharacter().getCClass().toString());
        level.setText(this.gc.getPlayerCharacter().getLevel().toString());
        alignment.setText(this.gc.getPlayerCharacter().getAlignment());
        background.setText(this.gc.getPlayerCharacter().getBackground().toString());
        experience.setText("" + this.gc.getPlayerCharacter().getCurrentXP());
    }


    
}