package io.javasmithy;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
public class App extends Application{

    @Override
    public void start(Stage stage){
        Scene scene = new Scene(
            new AnchorPane(
                new Button("BUTTON")
            ),
            500,
            500
        );
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args )
    {
        launch();
    }
}
