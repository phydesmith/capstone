package io.javasmithy.view;

import io.javasmithy.model.position.PointGrid;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Sprite extends ImageView {
    private static final Random random = new Random();

    private int row;
    private int column;

    private PointGrid grid;

    public Sprite(){
        super();
        this.row = 0;
        this.column = 0;

        this.setFitHeight(50.0);
        this.setFitWidth(50.0);

    setFocusTraversable(true);

}

    public Sprite(PointGrid grid){
        super();
        this.grid = grid;
        this.row = 0;
        this.column = 0;

        this.setFitHeight(50.0);
        this.setFitWidth(50.0);

        setFocusTraversable(true);
    }

    public void setSpriteParent(Pane pane){
        pane.getChildren().add(this);
    }

    public void moveRow(int delta){
        if (row + delta < 0 || row + delta > this.grid.getHeight()-1) {
            return;
        } else {row+=delta;}
        setPos();
    }
    public void moveColumn(int delta){
        if (column + delta < 0 || column + delta > this.grid.getWidth()-1) {
            return;
        } else {column+=delta;}
        setPos();
    }
    private void setPos(){
        this.setX(grid.getPoint2D(row, column).getX());
        this.setY(grid.getPoint2D(row, column).getY());
    }

    public void setGrid(PointGrid grid){
        this.grid = grid;
    }

    public double getGridX(){
        return this.grid.getPoint2D(row, column).getX();
    }
    public double getGridY(){
        return this.grid.getPoint2D(row, column).getY();
    }


    public void moveRandomly(){
        int axis = random.nextInt(2);
        int direction = random.nextInt(2);
        int delta = 1;
        if (direction == 0) delta = -delta;
        if (axis ==0 ) {moveRow(delta);} else {moveColumn(delta);}
    }
}