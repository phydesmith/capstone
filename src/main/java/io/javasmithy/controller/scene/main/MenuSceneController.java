package io.javasmithy.controller.scene.main;

import io.javasmithy.controller.scene.SceneController;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.*;

import javafx.scene.control.Button;

/** Controller used to determine behavior for Main Menu
 * @author Peter Hyde-Smith
 */
public class MenuSceneController implements Initializable, SceneController {

    private GameController gc;
    private Scene creationScene;
    private Scene gameScene;

    @FXML
    private Button newGameBtn;
    @FXML
    private Button continueBtn;
    @FXML
    private Button exit;

    public void setCreationScene(Scene scene) {
        this.creationScene = scene;
    }

    public void setGameScene(Scene scene) {
        this.gameScene = scene;
    }

    @FXML
    public void openCreationScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(creationScene);
    }

    @FXML
    public void openGameScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(gameScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setContinueBtnDisable(boolean state){
        continueBtn.setDisable(state);
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    @FXML
    public void exitGame(){
        Platform.exit();
    }

}