package io.javasmithy.controller.scene;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.*;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

public class CreationSceneController {

    private Scene menuScene;
    private Scene gameScene;

    @FXML
    Pane centerChangePane;

    public void setMenuScene(Scene scene) {
        this.menuScene = scene;
    }
    public void setGameScene(Scene scene){
        this.gameScene = scene;
    }

    @FXML
    public void openMenuScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);
    }
    @FXML
    public void openGameScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(gameScene);
    }
    @FXML
    public void showAbilityGeneration(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\AbilitiesLayout.fxml"));
        this.centerChangePane.getChildren().clear();
        this.centerChangePane.getChildren().add(loader.load());
        System.out.println(this.centerChangePane.getChildren());
    }
}