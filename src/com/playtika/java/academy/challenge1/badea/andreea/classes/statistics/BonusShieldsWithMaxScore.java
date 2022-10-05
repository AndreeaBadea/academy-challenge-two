package com.playtika.java.academy.challenge1.badea.andreea.classes.statistics;

import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.BonusShield;
import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.enums.ShieldType;
import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.interfaces.Processable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class BonusShieldsWithMaxScore implements Processable {


        @Override
        public BonusShield[] process (List < BonusShield > bonusShieldCollection) {

            CompletableFuture<BonusShield[]> taskAsync = CompletableFuture.supplyAsync(() -> {

            int weakMaxScore = getMaxScorePerShieldType(bonusShieldCollection, ShieldType.Weak.name());
            List<BonusShield> weakShieldList = getBonusShieldsPerTypeByScore(bonusShieldCollection, ShieldType.Weak, weakMaxScore);

            int strongMaxScore = getMaxScorePerShieldType(bonusShieldCollection, ShieldType.Weak.name());
            List<BonusShield> strongShieldList = getBonusShieldsPerTypeByScore(bonusShieldCollection, ShieldType.Strong, strongMaxScore);

            int invincibleMaxScore = getMaxScorePerShieldType(bonusShieldCollection, ShieldType.Weak.name());
            List<BonusShield> invincibleShieldList = getBonusShieldsPerTypeByScore(bonusShieldCollection, ShieldType.Invincible, invincibleMaxScore);

            List<BonusShield> bonusShieldList = new ArrayList<>(weakShieldList);
            bonusShieldList.addAll(strongShieldList);
            bonusShieldList.addAll(invincibleShieldList);

            BonusShield[] bonusShieldsResult = new BonusShield[bonusShieldList.size()];
            for (int i = 0; i < bonusShieldList.size(); i++) {
                bonusShieldsResult[i] = bonusShieldList.get(i);
            }
            return bonusShieldsResult;
        });

            BonusShield[] result;
            try {
                result = taskAsync.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            return result;
    }

        public int getMaxScorePerShieldType(List<BonusShield> bonusShieldList, String shieldType){
             return bonusShieldList.parallelStream()
                    .filter(x -> shieldType.equals(x.getType().name()))
                    .mapToInt(BonusShield::getScore)
                    .max().orElseThrow(NullPointerException::new);
        }

        public List<BonusShield> getBonusShieldsPerTypeByScore(List<BonusShield> bonusShieldList, ShieldType shieldType, int maxScore){
            return bonusShieldList.parallelStream()
                    .filter(x -> x.getType().equals(shieldType))
                    .filter(x -> x.getScore() == maxScore)
                    .collect(Collectors.toList());
        }

    }
