package io.javasmithy;

import io.javasmithy.controller.scene.main.MenuSceneController;
import io.javasmithy.controller.game.GameController;
import io.javasmithy.controller.scene.creation.CreationSceneController;
import io.javasmithy.controller.scene.game.GameSceneController;

import io.javasmithy.util.GameThread;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.fxml.FXMLLoader;

//  testing and debugging imports


public class App extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        GameController gc = new GameController();
        gc.init();
        GameThread gameThread = new GameThread(gc);
        //gameThread.start();

        FXMLLoader menuSceneLoader, creationSceneLoader, gameSceneLoader;
        Parent menuSceneLayout, creationSceneLayout, gameSceneLayout;
        Scene menuScene, creationScene, gameScene;

        menuSceneLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\MenuSceneLayout.fxml"));
        menuSceneLoader.setLocation(getClass().getClassLoader().getResource("fxml\\MenuSceneLayout.fxml"));
        creationSceneLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\CreationSceneLayout.fxml"));
        creationSceneLoader.setLocation(getClass().getClassLoader().getResource("fxml\\CreationSceneLayout.fxml"));
        gameSceneLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml\\GameSceneLayout.fxml"));
        gameSceneLoader.setLocation(getClass().getClassLoader().getResource("fxml\\GameSceneLayout.fxml"));

        menuSceneLayout = menuSceneLoader.load();
        creationSceneLayout = creationSceneLoader.load();
        gameSceneLayout = gameSceneLoader.load();

        menuScene = new Scene(menuSceneLayout, 1080, 720);
        creationScene = new Scene(creationSceneLayout, 1080, 720);
        gameScene = new Scene(gameSceneLayout, 1080, 720);

        MenuSceneController menu = (MenuSceneController) menuSceneLoader.getController();
        menu.setGameController(gc);
        menu.setCreationScene(creationScene);
        menu.setGameScene(gameScene);

        CreationSceneController creation = (CreationSceneController) creationSceneLoader.getController();
        creation.setGameController(gc);
        creation.setMenuScene(menuScene);
        creation.setGameScene(gameScene);

        GameSceneController gScene = (GameSceneController) gameSceneLoader.getController();
        gScene.setGameController(gc);
        gScene.setGameThread(gameThread);
        gScene.setMenuScene(menuScene);


        stage.setScene(menuScene);
        stage.show();
    }

    public static void main( String[] args ) {

        launch();
    }
}