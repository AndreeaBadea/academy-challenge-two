package com.playtika.java.academy.challenge1.badea.andreea.main.spaceinvaders;

public class SpaceInvader {

    private int X;
    private int Y;
    private boolean hasIncreasedDamage;

    public SpaceInvader(int x, int y, boolean hasIncreasedDamage) {
        X = x;
        Y = y;
        this.hasIncreasedDamage = hasIncreasedDamage;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public String toString() {
        return "SpaceInvader{" +
                "X=" + X +
                ", Y=" + Y +
                ", hasIncreasedDamage=" + hasIncreasedDamage +
                '}';
    }
}
