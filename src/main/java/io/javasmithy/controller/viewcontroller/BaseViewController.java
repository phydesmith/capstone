package io.javasmithy.controller.viewcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.Button;

public class BaseViewController implements Initializable {

    @FXML 
    public BorderPane changePane;
    @FXML
    public AnchorPane basePane;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Pane newPane;
        Button tempButton = (Button)event.getSource();
        switch(tempButton.getId()){
            case "newGameBtn":
                loader.setLocation(
                    getClass().getClassLoader().getResource("fxml\\CreationView.fxml")
                );
                newPane = loader.load();
                basePane.getChildren().add(newPane);
                break;
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }
    
}