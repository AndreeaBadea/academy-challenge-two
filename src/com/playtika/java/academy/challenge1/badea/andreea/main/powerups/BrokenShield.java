package com.playtika.java.academy.challenge1.badea.andreea.main.powerups;

import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.enums.ShieldType;

public class BrokenShield extends  BonusShield{
    private int additionalDamage;

    public BrokenShield(int score, String name, ShieldType type, int additionalDamage) {
        super(score, name, type);
        this.additionalDamage = additionalDamage;
    }

    public void disable(){
        additionalDamage = 0;
    }

    @Override
    public void takesAHit(int damage){
        score -= damage - additionalDamage;
    }

    @Override
    public String toString() {
        return "BrokenShield{ " +
                "isAdvantage=" + isAdvantage +
                ", score=" + getScore() +
                ", name='" + getName() + '\'' +
                ", type= " + getType() + '\'' +
                ", aditionalDamage=" + additionalDamage +
                '}';
    }

}
