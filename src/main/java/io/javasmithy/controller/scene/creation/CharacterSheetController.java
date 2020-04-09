package io.javasmithy.controller.scene.creation;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.controller.scene.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
    @FXML
    TextArea characterSheet;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    public void populate(){
        name.setText(gc.getPlayerCharacter().getName());
        characterSheet.setText(gc.getPlayerCharacter().toStringNoName());

        /*
        race.setText(this.gc.getPlayerCharacter().getRace().toString());
        cClass.setText(this.gc.getPlayerCharacter().getCClass().toString());
        level.setText(this.gc.getPlayerCharacter().getLevel().toString());
        alignment.setText(this.gc.getPlayerCharacter().getAlignment().toString());
        background.setText(this.gc.getPlayerCharacter().getBackground().toString());
        experience.setText("" + this.gc.getPlayerCharacter().getCurrentXP());
        */
    }


    
}