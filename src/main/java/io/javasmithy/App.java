package io.javasmithy;

import io.javasmithy.view.factory.SceneFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class App extends Application{

    @Override
    public void start(Stage stage){
        SceneFactory sf = new SceneFactory();
        Scene scene = sf.getScene();
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args )
    {
        launch();
    }
}
