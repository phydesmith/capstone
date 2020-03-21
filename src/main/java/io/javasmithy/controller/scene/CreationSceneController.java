package io.javasmithy.controller.scene;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.fxml.*;

import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;
import javafx.event.ActionEvent;

public class CreationSceneController implements Initializable, SceneController {

    private GameController gc;
    private Scene menuScene;
    private Scene gameScene;

    @FXML
    Pane centerChangePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        centerChangePane.getChildren().add(new Group());
        System.out.println(centerChangePane.getChildren());
    }

    public void setMenuScene(Scene scene) {
        this.menuScene = scene;
    }

    public void setGameScene(Scene scene) {
        this.gameScene = scene;
    }

    @FXML
    public void openMenuScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);
    }

    @FXML
    public void openGameScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(gameScene);
    }

    @FXML
    public void showAbilityGeneration(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/AbilityGeneration.fxml"));
        /*
         * this.centerChangePane.getChildren().clear();
         * this.centerChangePane.getChildren().add(loader.load());
         */
        this.centerChangePane.getChildren().set(0, loader.load());
        ((AbilityGenerationController)loader.getController()).setGameController(this.gc);
        System.out.println(this.centerChangePane.getChildren());
    }

    @FXML
    public void showRaceChoice(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/RaceChoice.fxml"));
        this.centerChangePane.getChildren().set(0, loader.load());
        ((RaceChoiceController)loader.getController()).setGameController(this.gc);
    }

    @FXML
    public void showBackgroundChoice(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/BackgroundChoice.fxml"));
        this.centerChangePane.getChildren().set(0, loader.load());
        ((BackgroundChoiceController)loader.getController()).setGameController(this.gc);
    }

    @FXML
    public void showArchetypeChoice(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ArchetypeChoice.fxml"));
        this.centerChangePane.getChildren().set(0, loader.load());
        ((ArchetypeChoiceController)loader.getController()).setGameController(this.gc);
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }


}