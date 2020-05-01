package io.javasmithy.view;

import io.javasmithy.model.entity.Entity;
import io.javasmithy.model.position.PointGrid;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

/** A class to represent the entities on the playing field.
 * @author Peter Hyde-Smith
 */
public class Sprite extends ImageView {

    /**Entity associated with the sprite.
     *
     */
    private Entity entity;

    /** Row on the board. Used with point grid.
     *
     */
    private int row;
    /**Column on the board. Used with point grid.
     *
     */
    private int column;

    /**The point grid the sprite should reference when determining valid moves. Chiefly game board bounds.
     *
     */
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

    /**sets sprite entity
     * @param e the entity to associate this sprite with
     */
    public void setEntity(Entity e){
        this.entity = e;
    }
    /**gets sprite associated with entity
     * @return entity - entity associated with sprite
     */
    public Entity getEntity(){
        return this.entity;
    }

    /**used to set the GUI node this sprite will be displayed and manipulated on.
     * @param pane Gui node
     */
    public void setSpriteParent(Pane pane){
        pane.getChildren().add(this);
    }

    /**moves row-wise by delta - checks for bounds against grid
     * @param delta the amount to move, can be positive or negative -> usually 1
     */
    public void moveRow(int delta){
        clearCollision();
        if (row + delta < 0 || row + delta > this.grid.getHeight()-1) {
            return;
        } else {row+=delta;}
        setPos();
    }
    /**moves colum-wise by delta - checks for bounds against gri
     * @param delta the amount to move, can be positive or negative -> usually 1
     */
    public void moveColumn(int delta){
        clearCollision();
        if (column + delta < 0 || column + delta > this.grid.getWidth()-1) {
            return;
        } else {column+=delta;}
        setPos();
    }
    /**clears collision of current position with grid.
     *
     */
    private void clearCollision(){
        this.grid.clearCollision(row, column);
    }
    /**sets x-y GUI position based on grid, and the point at the sprites row and column values
     *also sets collision in grid
     */
    public void setPos(){
        System.out.println("Log: Setting Sprite Position");
        this.setX(grid.getPoint2D(row, column).getX());
        this.setY(grid.getPoint2D(row, column).getY());

        this.grid.setCollisionTrue(row, column, this);
    }

    public Point2D getPos(){
        return this.grid.getPoint2D(row, column);
    }


    public PointGrid getGrid(){
        return this.grid;
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



}