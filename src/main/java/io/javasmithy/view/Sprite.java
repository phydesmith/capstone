package io.javasmithy.view;

import io.javasmithy.model.position.PointGrid;

import javafx.geometry.Point2D;
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
        clearCollision();
        if (row + delta < 0 || row + delta > this.grid.getHeight()-1) {
            return;
        } else {row+=delta;}
        setPos();
    }
    public void moveColumn(int delta){
        clearCollision();
        if (column + delta < 0 || column + delta > this.grid.getWidth()-2) {
            return;
        } else {column+=delta;}
        setPos();
    }

    private void clearCollision(){
        System.out.println("\nClearing Collisions" + this);
        System.out.println("DEBUG-Sprite-SetPos() map before collision clear: " + this.grid.getSpriteAtPosition(row, column));
        this.grid.clearCollision(column, row);
        System.out.println("DEBUG-Sprite-SetPos() map after collision clear: " + this.grid.getSpriteAtPosition(row, column));
    }
    private void setPos(){
        System.out.println("\nSetting Sprite Position" + this);
        System.out.println("Sprite Column(X): " + this.getColumn());
        System.out.println("Sprite Row(Y): " + this.row);
        this.setX(grid.getPoint2D(row, column).getX());
        this.setY(grid.getPoint2D(row, column).getY());

        System.out.println("DEBUG-Sprite-SetPos() map before set new collision: " + this.grid.getSpriteAtPosition(row, column));
        this.grid.setCollisionTrue(column, row, this);
        System.out.println("DEBUG-Sprite-SetPos() map after set new collision: " + this.grid.getSpriteAtPosition(row, column));
    }
    public Point2D getPos(){
        return this.grid.getPoint2D(row, column);
    }

    public void setGrid(PointGrid grid){
        this.grid = grid;
    }


    public int getColumn(){
        return this.column;
    }
    public int getRow(){
        return this.row;
    }


    public void moveRandomly(){
        int axis = random.nextInt(2);
        int direction = random.nextInt(2);
        int delta = 1;
        if (direction == 0) delta = -delta;
        if (axis ==0 ) {moveRow(delta);} else {moveColumn(delta);}
    }
}