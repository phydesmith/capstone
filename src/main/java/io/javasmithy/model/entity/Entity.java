package io.javasmithy.model.entity;

import io.javasmithy.view.Sprite;

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
    public void move(int deltaX, int deltaY);
    public int getX();
    public int getY();

    public void setSprite(Sprite sprite);
    public Sprite getSprite();

}