package io.javasmithy.controller.scene.game;

import io.javasmithy.controller.scene.SceneController;
import io.javasmithy.model.room.RoomFactory;
import io.javasmithy.model.room.RoomType;
import io.javasmithy.util.GameLog;
import io.javasmithy.util.GameThread;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ListView;
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
    private GameThread gameThread;
    private Scene menuScene;
    private String[] panePaths = {"/fxml/Room1.fxml", "/fxml/Room2.fxml", "/fxml/Room3.fxml"};

    // testing
    int gCtr = 0;
    @FXML
    Button gCtrPress;
    @FXML
    Button nextRoomButton;
    @FXML
    AnchorPane gamePane;
    @FXML
    ListView gameLogView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamePane.getChildren().add(new Region()); // this group
        gameLogView.setItems(GameLog.getLogList());
        enableGameLogAutoScroll();
    }


    public void setMenuScene(Scene scene) {
        this.menuScene = scene;
    }

    //   TESTING
    @FXML
    public void gCtrInc() {
        //gc.run();
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
    }
    public void setGameThread(GameThread gameThread){
        this.gameThread = gameThread;
    }

    @FXML
    public void setGamePane(ActionEvent actionEvent) throws IOException {
        setEncounterRoom();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(panePaths[this.gCtr]));
        this.gamePane.getChildren().set(0, loader.load());

        ((GamePaneController)loader.getController()).setGameController(this.gc);

        this.gameThread.start();

        gCtrInc();
    }

    @FXML
    public void holdPlayerMove(){
        this.gc.getPlayerCharacter().holdMove();
    }
    @FXML
    public void attack(){
        System.out.println("LOG: Player attacking target");
        useAction();
    }
    @FXML
    public void holdAttack(){
        System.out.println("LOG: Player holding attack");
        useAction();
    }
    public void useAction(){ this.gc.getPlayerCharacter().useAction(); }


    public void setEncounterRoom(){
        if (gCtr == 0){
            gc.setCurrentRoom(RoomFactory.createRoom(RoomType.ROOM_1));
        } else if (gCtr ==1){
            gc.setCurrentRoom(RoomFactory.createRoom(RoomType.ROOM_2));
        } else {
            gc.setCurrentRoom(RoomFactory.createRoom(RoomType.ROOM_1));
        }
    }

    public void enableGameLogAutoScroll(){
        this.gameLogView.getItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {
                gameLogView.scrollTo(gameLogView.getItems().size());
            }
        });

    }

}