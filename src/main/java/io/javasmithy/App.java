package io.javasmithy;

import io.javasmithy.controller.scene.MenuSceneController;
import io.javasmithy.controller.scene.CreationSceneController;
import io.javasmithy.controller.scene.GameSceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.fxml.FXMLLoader;



public class App extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader menuSceneLoader, creationSceneLoader, gameSceneLoader;
        Parent menuSceneLayout, creationSceneLayout, gameSceneLayout;
        Scene menuScene, creationScene, gameScene;

        menuSceneLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\MenuSceneLayout.fxml"));
        creationSceneLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\CreationSceneLayout.fxml"));
        gameSceneLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\GameSceneLayout.fxml"));

        menuSceneLayout = menuSceneLoader.load();
        creationSceneLayout = creationSceneLoader.load();
        gameSceneLayout = gameSceneLoader.load();

        menuScene = new Scene(menuSceneLayout, 1080, 720);
        creationScene = new Scene(creationSceneLayout, 1080, 720);
        gameScene = new Scene(gameSceneLayout, 1080, 720);

        MenuSceneController menu = (MenuSceneController) menuSceneLoader.getController();
        menu.setCreationScene(creationScene);
        menu.setGameScene(gameScene);

        CreationSceneController creation = (CreationSceneController) creationSceneLoader.getController();
        creation.setMenuScene(menuScene);
        creation.setGameScene(gameScene);

        GameSceneController gScene = (GameSceneController) gameSceneLoader.getController();
        gScene.setMenuScene(menuScene);

        stage.setScene(menuScene);
        stage.show();
    }

    public static void main( String[] args )
    {
        launch();
    }
}



/*

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\MainMenuScene.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\BaseView.fxml"));
        //FXMLLoader loader = new FXMLLoader(BaseViewController.class.getClassLoader().getResource("fxml\\BaseView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
*/
