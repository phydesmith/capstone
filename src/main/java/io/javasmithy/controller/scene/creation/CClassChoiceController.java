package io.javasmithy.controller.scene.creation;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;

import java.util.Arrays;

import java.util.List;

import io.javasmithy.controller.scene.SceneController;
import io.javasmithy.model.component.cclass.CClass;

import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/** Controller for a sub-pane that allows character to choose character class.
 * @author Peter Hyde-Smith
 */
public class CClassChoiceController implements Initializable, SceneController {

    private GameController gc;
    private List<CClass> cClassEnums;
    private CClass currentSelection;

    @FXML
    ListView<CClass> listView;
    @FXML
    TextArea cClassDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initEnums();
        listView.setItems(FXCollections.observableArrayList(cClassEnums));
        setListViewSelectionListener();
    }
    private void initEnums(){
        this.cClassEnums = Arrays.asList(CClass.values());
    }
    /**
     * sets listener for Character Class Choice listview
     */
    private void setListViewSelectionListener(){
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CClass>() {
            @Override
            public void changed(ObservableValue<? extends CClass> observable, CClass oldValue, CClass newValue) {
                cClassDescription.setText(newValue.getDescription());
                currentSelection=newValue;
            }
        });
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    @FXML
    public void handleCClassSelection(ActionEvent actionEvent) throws Exception{
        this.currentSelection= (CClass)listView.getSelectionModel().getSelectedItem();
        this.cClassDescription.setText(this.currentSelection.getDescription());
    }
    /**
     * Submits Character class and sets in character object.
     */
    @FXML
    public void submitCClass(){
        this.gc.getPlayerCharacter().setCClass(this.currentSelection);
        System.out.println("Log: class selection " + this.currentSelection + " submitted.");
    }

}