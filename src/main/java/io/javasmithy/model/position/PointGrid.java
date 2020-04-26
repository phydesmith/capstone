package io.javasmithy.model.position;

import io.javasmithy.view.Sprite;
import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointGrid{

    private Map<Point2D, Sprite> collisionMap;
    private Point2D[][] grid;

    private int xOffset;
    private int yOffset;

    private int cellSize;

    private int rows;
    private int columns;

    public PointGrid(){}

    public PointGrid(int rows, int columns, int yOffset, int xOffset, int cellSize){
        this.grid = new Point2D[rows][columns];
        this.cellSize = cellSize;
        this.yOffset = yOffset;
        this.xOffset = xOffset;

        System.out.println("DEBUG rows grid length: " + this.grid.length);
        System.out.println("DEBUG columns grid[] : " + this.grid[0].length);

        this.rows = rows;
        this.columns = columns;

        initGrid();
        initCollisionMap();
        System.out.println(toString());
    }

    public void initGrid(){
        for (int row = 0; row < this.rows; row++){
            for (int column = 0; column < this.columns; column++) {
                this.grid[row][column] = new Point2D(xOffset+(cellSize*column), yOffset+(cellSize*row));
            }
        }
    }
    public void initCollisionMap(){
        this.collisionMap = new HashMap<Point2D, Sprite>();
        for (int i = 0; i < this.grid.length; i++){
            for (int j = 0; j < this.grid[i].length; j++){
                this.collisionMap.put(this.grid[i][j], null);
            }
        }
    }

    /**
     * @param rows the rows to set
     */
    public void setHeight(int rows) {
        this.rows = rows;
    }
    /**
     * @param columns the width to set
     */
    public void setWidth(int columns) {
        this.columns = columns;
    }
    /**
     * @param xOffset the xOffset to set
     */
    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }
    /**
     * @param yOffset the yOffset to set
     */
    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }
    public int getWidth(){
        return this.grid[0].length;
    }
    public int getHeight(){
        return this.grid.length;
        //return this.rows;
    }
    public int getCellSize(){
        return this.cellSize;
    }

    public Point2D getPoint2D(int row, int column){
        System.out.println("\n getting pt2d");
        System.out.println("DEBUG this.grid[row]" + Arrays.deepToString(this.grid[row]));
        System.out.println("DEBUG this.grid[row][column]" + this.grid[row][column]);
        return this.grid[row][column];
    }

    public void setCollisionTrue(int row, int column, Sprite sprite){
        this.collisionMap.put(getPoint2D(row, column), sprite);
    }
    public Sprite getSpriteAtPosition(int row, int column){
        try {
            return this.collisionMap.get(getPoint2D(row, column));
        } catch (IndexOutOfBoundsException e){
            System.out.println("Exception caught in io.javasmithy.view.Sprite: Sprite on edge of grid");
            return null;
        }
    }
    public void clearCollision(int row, int column){
        this.collisionMap.put(getPoint2D(row, column), null);
    }
    public boolean checkCollision(int row, int column){
        try {
            return this.collisionMap.get(getPoint2D(row, column)) != null;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Exception caught in io.javasmithy.view.Sprite: Sprite on edge of grid");
            return false;
        }
    }


    public String toString(){
        String str = "";

        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++) {
                str += "[" + this.grid[row][column].getX() + "," + this.grid[row][column].getY() + "] ";
            }
            str+="\n";
        }

        return str;
    }


}