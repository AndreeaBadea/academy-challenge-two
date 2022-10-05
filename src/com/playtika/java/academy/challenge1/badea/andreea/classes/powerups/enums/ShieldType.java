package com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.enums;

public enum ShieldType {
    Strong("Heals"),
    Weak("Shots"),
    Invincible("Invisible");

    private String specialAbility;


    ShieldType(String specialAbility) {
        this.specialAbility = specialAbility;

    }

    public String getSpecialAbility(){
        return this.specialAbility;
    }




}
