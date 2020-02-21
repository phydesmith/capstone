package io.javasmithy.view.factory;

import io.javasmithy.view.GameView;

public class ViewFactory{

    public GameView getView(){
        return new GameView();
    }

}