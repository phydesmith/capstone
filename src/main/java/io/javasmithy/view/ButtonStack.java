package io.javasmithy.view;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class ButtonStack extends VBox{

    public ButtonStack(){
        init();
    }

    private void init(){
        setUpButtons();
        setSpacing();
    }

    private void setUpButtons(){
        String[] text = { "New", "Load", "Save", "Exit"};
        for (int i = 0; i < text.length; i++){
            this.getChildren().add(new Button(text[i]));
            ((Button)this.getChildren().get(i)).setMinHeight(40);
            ((Button)this.getChildren().get(i)).setMinWidth(200);
            ((Button)this.getChildren().get(i)).setMaxHeight(40);
            ((Button)this.getChildren().get(i)).setMaxWidth(200);
        }
    }

    private void setSpacing(){
        this.setSpacing(20);
    }
}