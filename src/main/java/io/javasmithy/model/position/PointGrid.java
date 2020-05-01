package io.javasmithy.model.position;

import io.javasmithy.view.Sprite;
import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class is used to convert column-row positions to xy coordinates
 * @author Peter Hyde-Smith
 */
public class PointGrid{

    /**
     * Map containing pairings between a point within this grid and a sprite. Used in collision checks.
     */
    private Map<Point2D, Sprite> collisionMap;
    /**
     * 2d array that is analogous to a grid
     */
    private Point2D[][] grid;

    /**
     * if grid is not flush to GUI element, this will add a certain amount of x-axis pixels
     */
    private int xOffset;
    /**
     * if grid is not flush to GUI element, this will add a certain amount of y-axis pixels
     */
    private int yOffset;

    /**
     * the pixel height and width of cells
     */
    private int cellSize;

    /**
     * number of rows the grid has
     */
    private int rows;
    /**
     * number of columns the grid has
     */
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
    }

    public Point2D[][] getGrid(){
        return this.grid;
    }

    /**
     * intializes the points that translates the column/row to x/y points via storing Point2D classes
     */
    public void initGrid(){
        for (int row = 0; row < this.rows; row++){
            for (int column = 0; column < this.columns; column++) {
                this.grid[row][column] = new Point2D(xOffset+(cellSize*column), yOffset+(cellSize*row));
            }
        }
    }
    public void initCollisionMap(){
        /**
         * a map of each point in the grid sets all entries to null
         */
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
        return this.grid[row][column];
    }

    /** Sets collision map entry associated with a sprite to allow for collision checks
     * @param row row position
     * @param column column position
     * @param sprite sprite associated with collision
     */
    public void setCollisionTrue(int row, int column, Sprite sprite){
        System.out.println("Log: Setting Collision");
        this.collisionMap.put(getPoint2D(row, column), sprite);
    }
    /** clears collision map entry associated with a sprite to allow for collision checks
     * @param row row position
     * @param column column position
     *
     */
    public void clearCollision(int row, int column){
        System.out.println("Log: Clearing collision - in point grid - at row " + row + " column " + column);
        this.collisionMap.put(getPoint2D(row, column), null);
    }
    /** checks collision map entry to see if there is a sprite associated with it
     * @param row row position
     * @param column column position
     * @return true or false
     */
    public boolean checkCollision(int row, int column){
        System.out.println("Log: Checking Collision");
        try {
            return getSpriteAtPosition(row, column) != null;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Log: Exception caught in point grid -> Sprite on edge of grid");
            return false;
        }
    }


    /** Returns sprite at row/column position
     * @param row row position
     * @param column column position
     * @return Sprite at position
     */
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