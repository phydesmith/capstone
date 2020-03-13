package io.javasmithy.controller.scene;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Arrays;

import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AbilityGenerationController implements Initializable {

    @FXML
    GridPane abilGridPane;

    @FXML
    ListView<Integer> listView;

    @FXML
    VBox inputBox;
    @FXML
    Label warningLabel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        listView.setItems(FXCollections.observableArrayList(15,14,13,12,10,8) );
        //System.out.println(abilGridPane.getChildren());
    }

    @FXML
    void submitScores(ActionEvent e){
        if (compareInputAndScoreBank()){
            System.out.println(Arrays.toString(getScoreInputs()));
            this.warningLabel.setText("Scores Saved!");
        } else {
            System.out.println(Arrays.toString(getScoreInputs()));
            this.warningLabel.setText("WRONG SCORES");
        }
        //listView.getItems().add(ctr);
    }
    boolean compareInputAndScoreBank(){
        //int[] arr1 = listView.getItems().toArray();
        int[] arr1 = listView.getItems().stream().mapToInt(i -> i).toArray(); // from SO
        int[] arr2 = getScoreInputs();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }
    int[] getScoreInputs(){
        int[] arr = new int[6];
        for (int i = 0; i < 6; i++){
            arr[i] = Integer.parseInt(
                ( (TextField) inputBox.getChildren().get(i)).getText()
            );
        }
        return arr;
    }

}