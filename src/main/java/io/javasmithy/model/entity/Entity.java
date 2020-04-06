package io.javasmithy.model.entity;

public interface Entity{
    public String getName();

    public void attack(Entity entity);
    public void takeDamage(int damage);
    public boolean canAttackTarget(Entity entity);
    public int getArmorClass();
    public int getAttackBonus();
    public int getDamage();

    public boolean isDead();

    public void move(int deltaX, int deltaY);
    public int getX();
    public int getY();
}