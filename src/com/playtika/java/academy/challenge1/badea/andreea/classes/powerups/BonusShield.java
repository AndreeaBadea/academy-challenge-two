package com.playtika.java.academy.challenge1.badea.andreea.classes.powerups;

import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.enums.ShieldType;

import java.util.function.IntSupplier;

public class BonusShield extends Shield implements Comparable{

    protected boolean isAdvantage;
    protected int score;
    private String name;
    private ShieldType type;

    public BonusShield(int score, String name, ShieldType type) {
        this.score = score;
        this.name = name;
        this.type = type;
    }

    public boolean isAdvantage() {
        if(type.getSpecialAbility().equals("Invisible")){
            return true;
        }
        return false;
    }

    public void setAdvantage(boolean advantage) {
        isAdvantage = advantage;
    }

    public void useBonusPoints(IntSupplier supplier){
        score += supplier.getAsInt();
    }

    @Override
    public void takesAHit(int damage) {
        score =  score - damage;
    }

    @Override
    public void hits(int points) {
        score = score + points;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public ShieldType getType() {
        return type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BonusShield{");
        sb.append("isAdvantage=").append(isAdvantage);
        sb.append(", score=").append(score);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append('}').append('\'');
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        BonusShield bonusShield = (BonusShield) o;
        if(this.score != bonusShield.score) {
            return Integer.compare(this.score, bonusShield.score);
        } else {
            return  this.name.compareTo(bonusShield.name); }
    }


}
