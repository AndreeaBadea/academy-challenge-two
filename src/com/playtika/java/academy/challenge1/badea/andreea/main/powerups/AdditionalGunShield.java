package com.playtika.java.academy.challenge1.badea.andreea.main.powerups;

import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.enums.ShieldType;

public class AdditionalGunShield extends BonusShield{

    private String ability;
    private int scoreMultiplier;


    public AdditionalGunShield(int score, String name, ShieldType type, String ability, int scoreMultiplier) {
        super(score, name, type);
        this.ability = ability;
        this.scoreMultiplier = scoreMultiplier;
    }


    @Override
    public void hits(int damage){
        score += damage * scoreMultiplier;
    }

    @Override
    public String toString() {
        return "AdditionalGunShield{" +
                "isAdvantage=" + isAdvantage +
                    ", score=" + getScore() +
                    ", name='" + getName() + '\'' +
                    ", type= " + getType() + '\'' +
                "ability='" + ability + '\'' +
                ", scoreMultiplier=" + scoreMultiplier +
                '}';
    }
}
