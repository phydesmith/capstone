package io.javasmithy.view;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.position.PointGrid;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Sprite extends ImageView {
    private static final Random random = new Random();

    private Entity entity;

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

    public void setEntity(Entity e){
        this.entity = e;
    }
    public Entity getEntity(){
        return this.entity;
    }

    public void setSpriteParent(Pane pane){
        pane.getChildren().add(this);
    }

    public void moveRow(int delta){
        clearCollision();
        System.out.println("\nMoving Sprite - row wise - up down");
        if (row + delta < 0 || row + delta > this.grid.getHeight()-1) {
            return;
        } else {row+=delta;}
        setPos();
    }
    public void moveColumn(int delta){
        clearCollision();
        System.out.println("\nMoving Sprite - columnwise - left right");
        if (column + delta < 0 || column + delta > this.grid.getWidth()-1) {
            return;
        } else {column+=delta;}
        setPos();
    }
    private void clearCollision(){
        this.grid.clearCollision(row, column);
    }
    public void setPos(){
        System.out.println("\nSetting Sprite Position" + this);
        this.setX(grid.getPoint2D(row, column).getX());
        this.setY(grid.getPoint2D(row, column).getY());

        this.grid.setCollisionTrue(row, column, this);
        //System.out.println("Setting collision at row " + row + " column " + column + " sprite " + this.grid.getSpriteAtPosition(row, column) + " check sprite " + this );
        //System.out.println("DEBUG Collision confirmation: " + this.grid.checkCollision(row, column));
    }
    public Point2D getPos(){
        return this.grid.getPoint2D(row, column);
    }

    public void setGrid(PointGrid grid){
        this.grid = grid;
    }

    public void setColumn(int column){
        this.column = column;
    }
    public int getColumn(){
        return this.column;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getRow(){
        return this.row;
    }

    public PointGrid getGrid(){
        return this.grid;
    }


    public void moveRandomly(){
        int axis = random.nextInt(2);
        int direction = random.nextInt(2);
        int delta = 1;
        if (direction == 0) delta = -delta;
        if (axis ==0 ) {moveRow(delta);} else {moveColumn(delta);}
    }
}