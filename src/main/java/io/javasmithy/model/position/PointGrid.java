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

        this.rows = rows;
        this.columns = columns;

        initGrid();
        initCollisionMap();
        System.out.println(toString());
    }

    public Point2D[][] getGrid(){
        return this.grid;
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

    public int getWidth(){
        return this.grid[0].length;
    }
    public int getHeight(){
        return this.grid.length;
    }
    public int getCellSize(){
        return this.cellSize;
    }

    public Point2D getPoint2D(int row, int column){
        //System.out.println("getting pt2d at row " + row + " column " + column + " P2D " + this.grid[row][column]);
        return this.grid[row][column];
    }

    public void setCollisionTrue(int row, int column, Sprite sprite){
        System.out.println("Log: Setting Collision");
        this.collisionMap.put(getPoint2D(row, column), sprite);
    }
    public void clearCollision(int row, int column){
        System.out.println("Log: Clearing collision - in point grid - at row " + row + " column " + column);
        this.collisionMap.put(getPoint2D(row, column), null);
    }
    public boolean checkCollision(int row, int column){
        System.out.println("Log: Checking Collision");
        try {
            return getSpriteAtPosition(row, column) != null;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Log: Exception caught in point grid -> Sprite on edge of grid");
            return false;
        }
    }


    public Sprite getSpriteAtPosition(int row, int column){
        try {
            return this.collisionMap.get(getPoint2D(row, column));
        } catch (IndexOutOfBoundsException e){
            System.out.println("Log: Exception caught in point grid -> Sprite on edge of grid");
            return null;
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