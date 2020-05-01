package io.javasmithy.controller.scene.game;

import io.javasmithy.controller.scene.PaneController;
import io.javasmithy.controller.scene.SceneController;
import io.javasmithy.model.component.skill.Skill;
import io.javasmithy.model.entity.CharacterEntity;
import io.javasmithy.model.room.RoomFactory;
import io.javasmithy.model.room.RoomType;
import io.javasmithy.util.GameLog;
import io.javasmithy.util.GameThread;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
import java.util.List;
import java.util.ResourceBundle;

/** Controller for Scene that holds the 'Game Panes'
 * @author Peter Hyde-Smith
 */
public class GameSceneController implements Initializable, SceneController {

    private GameController gc;
    /**
     * Thread to start gc.run()
     */
    private GameThread gameThread;
    private Scene menuScene;
    /**
     * Array of resource paths.
     */
    private String[] panePaths = {"/fxml/Room0.fxml", "/fxml/Room1.fxml"};
    /**
     * Keeps track of room type.
     */
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
    @FXML
    Button endTurnBtn;
    @FXML
    ListView<CharacterEntity> characterSheet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamePane.getChildren().add(new Region()); // this group
        gameLogView.setItems(GameLog.getLogList());
        enableGameLogAutoScroll();
        setCombatButtonsVisible(false);
    }


    public void setMenuScene(Scene scene) {
        this.menuScene = scene;
    }

    /**
     * Used to get pane path - rolls to zero based on panePaths length.
     */
    @FXML
    public void incRoomCounter() {
        if (roomCounter < panePaths.length-1){
            roomCounter++;
        } else {
            roomCounter = 0;
        }
    }

    @FXML
    public void openMenuScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);

        enableContinueButton(primaryStage);
    }
    /**
     * Method to set continue button active in main menu (it is disabled by default).
     * @param stage
     */
    private void enableContinueButton(Stage stage){
        ObservableList<Node> children = stage.getScene().getRoot().getChildrenUnmodifiable();
        ObservableList<Node> targetChildren = null;
        for (Node node : children){
            if (node.getId() != null && node.getId().equals("menuButtons")){
                targetChildren = ((VBox) node).getChildrenUnmodifiable();
            }
        }
        for (Node child: targetChildren) {
            if (child.getId() != null && child.getId().equals("continueBtn")){
                child.setDisable(false);
            }
        }
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
        setCharacterSheet();
    }
    public void setGameThread(GameThread gameThread){
        this.gameThread = gameThread;
    }
    private void setCharacterSheet(){
        ObservableList<CharacterEntity> character = FXCollections.observableArrayList();
        character.add(this.gc.getPlayerCharacter());
        this.characterSheet.setItems(character);
        setCharSheetChangeListener();
    }

    /**
     * Sets a click listener that updates character sheet viewer
     */
    private void setCharSheetChangeListener(){

        this.characterSheet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                characterSheet.refresh();
            }

        });

    }

    /**
     * Sets active pane - used by nextRoom button
     * starts the GC thread and calls increment counter so next room in panePaths can be loaded.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Used by hold move button to skip character move phase.
     */
    @FXML
    public void holdPlayerMove(){
        this.gc.getPlayerCharacter().holdMove();
    }
    /**
     * Used by attack button to initiate character attack.
     */
    @FXML
    public void attack(){
        System.out.println("LOG: Player attacking target");
        useAction();
    }
    /**
     * Used by hold move attack to skip character attack phase.
     */
    @FXML
    public void holdAttack(){
        System.out.println("LOG: Player holding attack");
        useAction();
    }
    /**
     * Consumes the character action. Triggering attack phase to finish.
     */
    public void useAction(){ this.gc.getPlayerCharacter().useAction(); }
    /**
     * Used by end turn  button to skip all character phases.
     */
    @FXML
    public void endTurn(){
        GameLog.addEntry(this.gc.getPlayerCharacter().getName() + " ended turn.");
        holdPlayerMove();
        holdAttack();
    }


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
        endTurnBtn.setVisible(state);
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