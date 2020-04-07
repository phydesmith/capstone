package io.javasmithy.controller.scene;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.*;

import javafx.scene.control.Button;
import io.javasmithy.controller.game.GameController;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable, SceneController {

    private GameController gc;
    private Scene menuScene;

    // testing
    int gCtr = 0;
    @FXML
    Button gCtrPress;
    @FXML
    Button nextRoomButton;
    @FXML
    AnchorPane gamePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamePane.getChildren().add(new Group());
    }



    public void setMenuScene(Scene scene) {
        this.menuScene = scene;
    }

    //   TESTING
    @FXML
    public void gameCtrPress(ActionEvent actionEvent) {
        gc.run(); //  TESTING
        gCtr++;
        System.out.println(gCtr);
    }
    //  TESTING

    @FXML
    public void openMenuScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    @FXML
    public void nextGamePane(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GamePane.fxml"));
        this.gamePane.getChildren().set(0, loader.load());
    }
}