package io.javasmithy;

import io.javasmithy.controller.viewcontroller.BaseViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.fxml.FXMLLoader;



public class App extends Application{

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\BaseView.fxml"));
        //FXMLLoader loader = new FXMLLoader(BaseViewController.class.getClassLoader().getResource("fxml\\BaseView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args )
    {
        launch();
    }
}
