package com.playtika.java.academy.challenge1.badea.andreea.main.statistics;

import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.BonusShield;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Statistic {

    public static void saveStatisticToFile(String name, BonusShield[] bonusShields) throws IOException {
        File file = new File(name);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(bonusShields.length);
            for (BonusShield shield : bonusShields) {
                bufferedWriter.write(String.valueOf(shield.isAdvantage()));
                bufferedWriter.write(shield.getName());
                bufferedWriter.write(shield.getScore());
                bufferedWriter.write(shield.getType().name());
            }
        }
    }
}
