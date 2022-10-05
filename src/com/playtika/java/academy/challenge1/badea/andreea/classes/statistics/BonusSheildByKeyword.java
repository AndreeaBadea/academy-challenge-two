package com.playtika.java.academy.challenge1.badea.andreea.classes.statistics;

import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.BonusShield;
import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.interfaces.Processable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BonusSheildByKeyword implements Processable {

    private final String KEYWORD = "atomic";

    @Override
    public BonusShield[] process(List<BonusShield> bonusShields) {
        CompletableFuture<BonusShield[]> taskAsync = CompletableFuture.supplyAsync(() -> {

            double average = bonusShields.parallelStream()
                    .mapToInt(BonusShield::getScore)
                    .average()
                    .getAsDouble();

            return bonusShields.parallelStream()
                    .filter(x -> x.getName().toLowerCase().contains(KEYWORD.toLowerCase()))
                    .filter(x -> x.getScore() > average).toArray(BonusShield[]::new);
        });

        BonusShield[] result;
        try {
            result = taskAsync.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
