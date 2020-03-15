package io.javasmithy.controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.model.component.abilities.AbilityScoreFactory;
import io.javasmithy.model.component.abilities.RawScoreFactory;

import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;

import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.Group;

public class AbilityGenerationController implements Initializable, SceneController {

    private GameController gc;

    @FXML
    GridPane abilGridPane;

    @FXML
    ListView<Integer> listView;

    @FXML
    ArrayList<TextField> inputFields;

    @FXML
    Label warningLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(FXCollections.observableArrayList(15, 14, 13, 12, 10, 8));
    }

    @FXML
    private void submitScores(ActionEvent e) {
        if (compareInputAndScoreBank()) {
            System.out.println(getScoreInputs());
            System.out.println(this.gc.getPlayerCharacter());
            System.out.println("DEBUG 1");
            this.gc.getPlayerCharacter().setAbilityScores(AbilityScoreFactory.createAbilityScores(getScoreInputs()));
            this.warningLabel.setText("Scores Saved!");
            System.out.println(this.gc.getPlayerCharacter());
        } else {
            System.out.println("Incorrect Scores: " + getScoreInputs());
            System.out.println("Score Array: " + listView.getItems().toString());
            this.warningLabel.setText("Wrong Score Inputs!");
        }
    }

    @FXML
    private boolean compareInputAndScoreBank() {
        int[] arr1 = listView.getItems().stream().mapToInt(i -> i).toArray(); // from SO
        int[] arr2 = getScoreInputs().stream().mapToInt(i->i).toArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    @FXML
    private List<Integer> getScoreInputs() {
        List<Integer> scoreList = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            scoreList.add(Integer.parseInt(inputFields.get(i).getText()));
        }
        return scoreList;
    }

    @FXML
    private void generateNewScores() {
        this.listView.setItems(FXCollections.observableArrayList(RawScoreFactory.generateValidScoreList()));
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

}