package io.javasmithy;

import io.javasmithy.controller.GameController;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    @Override
    public void start(Stage stage){
        stage = new GameController();
        stage.show();
    }

    public static void main( String[] args )
    {
        launch();
    }
}
