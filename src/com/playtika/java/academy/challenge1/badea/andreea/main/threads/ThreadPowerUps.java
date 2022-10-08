package com.playtika.java.academy.challenge1.badea.andreea.main.threads;

import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.BonusShield;
import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.enums.ShieldType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadPowerUps implements Callable<List<BonusShield>> {

    private File powerUpsFile;

    public ThreadPowerUps(File powerUpsFile) {
        this.powerUpsFile = powerUpsFile;
    }

    @Override
    public List<BonusShield> call() throws Exception {
        FileReader fileReader = new FileReader(powerUpsFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<BonusShield> bonusShieldList = new ArrayList<>();
        String buffer = "";
        while ((buffer = bufferedReader.readLine()) != null) {

            boolean isAdvantage = Boolean.parseBoolean(bufferedReader.readLine().split(",")[0]);
            int score = Integer.parseInt(bufferedReader.readLine().split(",")[1]);
            String name = bufferedReader.readLine().split(",")[2];
            ShieldType type = ShieldType.valueOf(bufferedReader.readLine().split(",")[3]);

            BonusShield bonusShield = new BonusShield(score, name, type);
            bonusShield.setAdvantage(isAdvantage);
            bonusShieldList.add(bonusShield);
        }
        bufferedReader.close();

        return bonusShieldList;

    }
}
