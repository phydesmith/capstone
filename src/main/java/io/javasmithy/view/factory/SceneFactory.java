package io.javasmithy.view.factory;

import javafx.scene.Scene;

public class SceneFactory{
    ViewFactory vb;

    public SceneFactory(){
        vb = new ViewFactory();
    }

    public Scene getScene(){
        return new Scene(vb.getView(), 1080, 720);
    }

}