package io.javasmithy.controller;

import javafx.stage.Stage;

import io.javasmithy.view.factory.SceneFactory;
import javafx.scene.Scene;

public class GameController extends Stage{
    Scene mainMenu;
    Scene characterCreation;
    
    public GameController(){
        SceneFactory sf = new SceneFactory();
        mainMenu = sf.getScene("menu");
        this.setScene(mainMenu);
    }
}