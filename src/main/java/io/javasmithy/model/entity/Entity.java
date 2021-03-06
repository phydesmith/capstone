package io.javasmithy.model.entity;

import io.javasmithy.view.Sprite;

/** Entity interface to ensure all sub types have same methods.
 * @author Peter Hyde-Smith
 */
public interface Entity{
    public String getName();

    public void attack(Entity entity);
    public void takeDamage(int damage);
    public boolean canAttackTarget(Entity entity);
    public int getArmorClass();
    public int getAttackBonus();
    public int getDamage();

    public boolean isDead();

    public boolean canMove();
    public void decMovePoints();
    public void resetMovePoints();
    public int getMovePoints();
    public void move(int deltaX, int deltaY);
    public int getColumn();
    public void setColumn(int column);
    public int getRow();
    public void setRow(int row);

    public void setSprite(Sprite sprite);
    public Sprite getSprite();

    public boolean canUseAction();
    public void resetAction();
    public void useAction();

}