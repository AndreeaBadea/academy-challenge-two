package com.playtika.java.academy.challenge1.badea.andreea.main.powerups.interfaces;

import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.BonusShield;

import java.util.List;

public interface Processable {

    BonusShield[] process(List<BonusShield> bonusShieldCollection);
}
