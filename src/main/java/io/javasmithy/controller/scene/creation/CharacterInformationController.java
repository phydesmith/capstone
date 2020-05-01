package io.javasmithy.controller.scene.creation;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Arrays;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.controller.scene.SceneController;
import io.javasmithy.model.component.alignment.Alignment;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/** Controller for a sub-pane that allows character to choose alignment and set a description.
 * @author Peter Hyde-Smith
 */
public class CharacterInformationController implements Initializable, SceneController {

    private GameController gc;
    private List<Alignment> alignmentEnums;
    private Alignment currentSelection;

    @FXML
    ListView<Alignment> listView;
    @FXML
    TextField name;
    @FXML
    TextArea descriptionInput;
    @FXML
    TextArea alignmentDescription;

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initEnums();
        listView.setItems(FXCollections.observableArrayList(alignmentEnums));
        setListViewSelectionListener();
    }
    private void initEnums(){
        this.alignmentEnums = Arrays.asList(Alignment.values());
    }
    /**
     * sets listener for Background Choice listview
     */
    private void setListViewSelectionListener(){
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Alignment>() {
            @Override
            public void changed(ObservableValue<? extends Alignment> observable, Alignment oldValue, Alignment newValue) {
                alignmentDescription.setText(newValue.getDescription());
                currentSelection=newValue;
            }
        });
    }

    @FXML
    public void handleAlignmentSelection(ActionEvent actionEvent) throws Exception{
        this.currentSelection= (Alignment)listView.getSelectionModel().getSelectedItem();
        this.alignmentDescription.setText(this.currentSelection.getDescription());
    }

    /**
     * Submits name and bio and sets in character object.
     */
    @FXML
    public void submitBio(){
        submitName();
        submitDescription();
    }
    @FXML
    public void submitDescription(){
        System.out.println(this.descriptionInput.getText());
        this.gc.getPlayerCharacter().setDescription(this.descriptionInput.getText());
    }
    @FXML
    public void submitName(){
        this.gc.getPlayerCharacter().setName(this.name.getText());
    }

    /**
     * Submits alignment and sets in character object.
     */
    @FXML
    public void submitAlignment(){
        this.gc.getPlayerCharacter().setAlignment(this.currentSelection);
        System.out.println("Log: alignment selection " + this.currentSelection + " submitted.");
    }

}