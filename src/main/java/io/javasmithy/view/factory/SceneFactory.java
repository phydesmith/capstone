package io.javasmithy.view.factory;

import javafx.scene.Scene;

public class SceneFactory{
    private ViewFactory vb;

    public SceneFactory(){
        vb = new ViewFactory();
    }

    public Scene getScene(String gvType){
        return new Scene(vb.getView(gvType), 1080, 720);
    }

}