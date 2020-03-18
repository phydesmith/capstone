package io.javasmithy.controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.model.component.abilities.AbilityScoreFactory;
import io.javasmithy.model.component.abilities.RawScoreFactory;

import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;

import io.javasmithy.model.component.race.*;

import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class RaceChoiceController implements Initializable, SceneController {

    private GameController gc;
    private List<Race> raceEnums;
    private Race currentSelection;

    @FXML
    ListView<Race> listView;
    @FXML
    TextArea raceDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initRaceEnums();
        listView.setItems(FXCollections.observableArrayList(raceEnums));
        setListViewSelectionListener();
    }
    private void initRaceEnums(){
        this.raceEnums = Arrays.asList(Race.values());
    }
    private void setListViewSelectionListener(){
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Race>() {
            @Override
            public void changed(ObservableValue<? extends Race> observable, Race oldValue, Race newValue) {
                raceDescription.setText(newValue.getDescription());
                currentSelection=newValue;
            }
        });
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    @FXML
    public void handleRaceSelection(ActionEvent actionEvent) throws Exception{
        this.currentSelection= (Race)listView.getSelectionModel().getSelectedItem();
        this.raceDescription.setText(this.currentSelection.getDescription());
    }
    @FXML
    public void submitRace(){
        this.gc.getPlayerCharacter().setRace(this.currentSelection);
    }

}