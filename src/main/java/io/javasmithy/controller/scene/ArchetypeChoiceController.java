package io.javasmithy.controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;

import java.util.Arrays;

import java.util.List;

import io.javasmithy.model.component.archetype.Archetype;

import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ArchetypeChoiceController implements Initializable, SceneController {

    private GameController gc;
    private List<Archetype> archEnums;
    private Archetype currentSelection;

    @FXML
    ListView<Archetype> listView;
    @FXML
    TextArea archetypeDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initEnums();
        listView.setItems(FXCollections.observableArrayList(archEnums));
        setListViewSelectionListener();
    }
    private void initEnums(){
        this.archEnums = Arrays.asList(Archetype.values());
    }
    private void setListViewSelectionListener(){
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Archetype>() {
            @Override
            public void changed(ObservableValue<? extends Archetype> observable, Archetype oldValue, Archetype newValue) {
                archetypeDescription.setText(newValue.getDescription());
                currentSelection=newValue;
            }
        });
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    @FXML
    public void handleArchetypeSelection(ActionEvent actionEvent) throws Exception{
        this.currentSelection= (Archetype)listView.getSelectionModel().getSelectedItem();
        this.archetypeDescription.setText(this.currentSelection.getDescription());
    }
    @FXML
    public void submitArchetype(){
        this.gc.getPlayerCharacter().setArchetype(this.currentSelection);
    }

}