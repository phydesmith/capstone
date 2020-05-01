package io.javasmithy.controller.scene.game;

import io.javasmithy.controller.scene.PaneController;
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
    private String[] panePaths = {"/fxml/Room0.fxml", "/fxml/Room1.fxml", "/fxml/Room2.fxml", "/fxml/Room3.fxml"};
    private int roomCounter = 0;

    @FXML
    Button nextRoomButton;
    @FXML
    AnchorPane gamePane;
    @FXML
    ListView gameLogView;
    @FXML
    Button holdMove;
    @FXML
    Button attack;
    @FXML
    Button holdAttack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamePane.getChildren().add(new Region()); // this group
        gameLogView.setItems(GameLog.getLogList());
        enableGameLogAutoScroll();
    }


    public void setMenuScene(Scene scene) {
        this.menuScene = scene;
    }


    @FXML
    public void incRoomCounter() {
        if (roomCounter < 2){
            roomCounter++;
        } else {
            roomCounter = 0;
        }
    }

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource(panePaths[this.roomCounter]));
        this.gamePane.getChildren().set(0, loader.load());
        ((PaneController)loader.getController()).setGameController(this.gc);


        this.gameThread = new GameThread(this.gc);
        this.gameThread.start();

        incRoomCounter();
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
        System.out.println("Log: setting room to type " + this.roomCounter);
        if (roomCounter == 0){
            setCombatButtonsVisible(false);
            gc.setCurrentRoom(RoomFactory.createRoom(RoomType.ROOM_0));
        } else {
            setCombatButtonsVisible(true);
            gc.setCurrentRoom(RoomFactory.createRoom(RoomType.ROOM_1));
        }
    }
    private void setCombatButtonsVisible(Boolean state){
        holdMove.setVisible(state);
        holdAttack.setVisible(state);
        attack.setVisible(state);
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