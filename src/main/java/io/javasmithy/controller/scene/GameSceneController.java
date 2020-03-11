package io.javasmithy.controller.scene;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.*;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

public class GameSceneController {

    private Scene menuScene;

    //  testing
    int gCtr = 0;
    @FXML
    Button gCtrPress;

    public void setMenuScene(Scene scene) {
        this.menuScene = scene;
    }
    @FXML
    public void gameCtrPress(ActionEvent actionEvent) {
        gCtr++;
        System.out.println(gCtr);
    }
    @FXML
    public void openMenuScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);
    }
}