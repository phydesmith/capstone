package io.javasmithy.view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class MenuView extends BorderPane{
    private ButtonStack buttonStack;
    private Label title;
    private GridPane grid; 

    public MenuView(){
        this.grid = new GridPane();
        this.title = new Label("RPG Game");
        this.buttonStack = new ButtonStack();
        init();
    }

    private void init(){
        positionGrid();
        setButtonStackPosition();
        setupTitle();
        setSize();
    }

    private void positionGrid(){
        this.grid.setAlignment(Pos.CENTER);
        this.setCenter(this.grid);
    }
    private void setupTitle(){
        this.title.setFont(new Font("Courier New", 40.0));
        this.grid.add(this.title, 0,0);
    }
    private void setButtonStackPosition(){
        this.grid.add(this.buttonStack, 0,1);
    }

    private void setSize(){
        this.setMinWidth(1080);
        this.setMinHeight(720);
        this.setMaxWidth(1080);
        this.setMaxHeight(720);
    }
}