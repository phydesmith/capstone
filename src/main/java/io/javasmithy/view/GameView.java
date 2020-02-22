package io.javasmithy.view;

import javafx.scene.layout.AnchorPane;

public class GameView extends AnchorPane{
    private ButtonStack buttonStack;

    public GameView(){
        buttonStack = new ButtonStack();
        init();
    }

    private void init(){
        this.getChildren().add(buttonStack);
        setSize();
    }

    private void setSize(){
        this.setMinWidth(1080);
        this.setMinHeight(720);
        this.setMaxWidth(1080);
        this.setMaxHeight(720);
    }

    private void setButtonStackPosition(){
        this.setTopAnchor(buttonStack, 200.0);
        this.setLeftAnchor(buttonStack, 500.0);
    }

}