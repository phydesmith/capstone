package io.javasmithy.controller.scene;

import io.javasmithy.view.Sprite;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.fxml.*;

import javafx.scene.control.Button;
import io.javasmithy.controller.game.GameController;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable, SceneController {

    private GameController gc;
    private Sprite charSprite;
    private Scene menuScene;
    private Scene gameScene;
    private String[] panePaths = {"fxml/Room1.fxml", "fxml/Room2.fxml", "fxml/Room3.fxml"};
    private GamePaneController gPaneController;

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
        gamePane.getChildren().add(new Region()); // this group
    }


    public void setMenuScene(Scene scene) {
        this.menuScene = scene;
    }
    public void setGameScene(Scene scene){
        this.gameScene = scene;
    }


    //   TESTING
    @FXML
    public void gCtrInc() {
        //gc.run(); //  TESTING
        if (gCtr < 2){
            gCtr++;
        } else {
            gCtr = 0;
        }
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
        setSprite();
    }
    public void setSprite(){
        this.charSprite = this.gc.getSprite();
    }

    @FXML
    public void setGamePane(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(panePaths[this.gCtr]));
        this.gamePane.getChildren().set(0, loader.load());
        gPaneController = loader.getController();
        gPaneController.setGameController(this.gc);
        gPaneController.setGameScene(this.gameScene);
        gCtrInc();
    }

    @FXML
    public void moveBtn(ActionEvent actionEvent){
        System.out.println("DEBUG: " +   ((AnchorPane)this.gamePane.getChildren().get(0)).getChildren());
        System.out.println(gPaneController.getSprite());
        gPaneController.getSprite().moveColumn(1);
    }

}