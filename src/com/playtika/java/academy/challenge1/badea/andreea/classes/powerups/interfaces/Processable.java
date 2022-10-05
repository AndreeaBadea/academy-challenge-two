package com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.interfaces;

import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.BonusShield;

import java.util.List;

public interface Processable {

    BonusShield[] process(List<BonusShield> bonusShieldCollection);
}
