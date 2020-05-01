package io.javasmithy.controller.scene.creation;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;

import java.util.Arrays;

import java.util.List;

import io.javasmithy.controller.scene.SceneController;
import io.javasmithy.model.component.background.Background;

import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/** Controller for a sub-pane that allows character to choose a background.
 * @author Peter Hyde-Smith
 */
public class BackgroundChoiceController implements Initializable, SceneController {

    private GameController gc;
    private List<Background> bgEnums;
    private Background currentSelection;

    @FXML
    ListView<Background> listView;
    @FXML
    TextArea backgroundDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initEnums();
        listView.setItems(FXCollections.observableArrayList(bgEnums));
        setListViewSelectionListener();
    }
    private void initEnums(){
        this.bgEnums = Arrays.asList(Background.values());
    }
    /**
     * Sets listener for Background Choice listview.
     */
    private void setListViewSelectionListener(){
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Background>() {
            @Override
            public void changed(ObservableValue<? extends Background> observable, Background oldValue, Background newValue) {
                backgroundDescription.setText(newValue.getDescription());
                currentSelection=newValue;
            }
        });
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    @FXML
    public void handleBackgroundSelection(ActionEvent actionEvent) throws Exception{
        this.currentSelection= (Background)listView.getSelectionModel().getSelectedItem();
        this.backgroundDescription.setText(this.currentSelection.getDescription());
    }
    @FXML
    /**
     * Submits background and sets in character object.
     */
    public void submitBackground(){
        this.gc.getPlayerCharacter().setBackground(this.currentSelection);
    }

}