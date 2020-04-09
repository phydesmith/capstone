package io.javasmithy.model.position;

import javafx.geometry.Point2D;

import java.util.Arrays;

public class PointGrid{

    Point2D[][] grid;

    int xOffset;
    int yOffset;

    int cellSize;

    int width;
    int height;

    public PointGrid(int height, int width, int yOffset, int xOffset, int cellSize){
        this.grid = new Point2D[height][width];
        this.cellSize = cellSize;
        this.yOffset = yOffset;
        this.xOffset = xOffset;
        this.width = width;
        this.height = height;

        initGrid();
        System.out.println(toString());
    }

    public void initGrid(){
        for (int row = 0; row < this.height; row++){
            for (int column = 0; column < this.width; column++) {
                this.grid[row][column] = new Point2D(xOffset+(cellSize*column), yOffset+(cellSize*row));
            }
        }
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
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
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public int getCellSize(){
        return this.cellSize;
    }

    public Point2D getPoint2D(int row, int column){
        return this.grid[row][column];
    }


    public String toString(){
        String str = "";

        for (int row = 0; row < height; row++){
            for (int column = 0; column < width; column++) {
                str += "[" + this.grid[row][column].getX() + "," + this.grid[row][column].getY() + "] ";
            }
            str+="\n";
        }

        return str;
    }


}