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


public class App extends Application{
    /**
     * The main entry point for the program.
     * @param stage JavaFX stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        GameController gc = new GameController();
        GameThread gameThread = new GameThread(gc);

        FXMLLoader menuSceneLoader, creationSceneLoader, gameSceneLoader;
        Parent menuSceneLayout, creationSceneLayout, gameSceneLayout;
        Scene menuScene, creationScene, gameScene;

        menuSceneLoader = new FXMLLoader(getClass().getResource("/fxml/MenuSceneLayout.fxml"));
        creationSceneLoader = new FXMLLoader(getClass().getResource("/fxml/CreationSceneLayout.fxml"));
        gameSceneLoader = new FXMLLoader(getClass().getResource("/fxml/GameSceneLayout.fxml"));

        menuSceneLayout = menuSceneLoader.load();
        creationSceneLayout = creationSceneLoader.load();
        gameSceneLayout = gameSceneLoader.load();

        menuScene = new Scene(menuSceneLayout, 1440, 900);
        creationScene = new Scene(creationSceneLayout, 1440, 900);
        gameScene = new Scene(gameSceneLayout, 1440, 900);

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
        /**
         * Main method calls the launch() method to start the JavaFX app
         * @param args Args for program
         *
         */
        launch();
    }
}