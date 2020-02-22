package io.javasmithy.view.factory;

import io.javasmithy.view.GameView;
import io.javasmithy.view.MenuView;

import javafx.scene.layout.Pane;

public class ViewFactory{

    public Pane getView(String viewType){
        if (viewType.equalsIgnoreCase("menu")) {
            return new MenuView();
        } else if (viewType.equalsIgnoreCase("game")){
            return new GameView();
        } else { return null;} // Should Probably Throw Exception
    }

}