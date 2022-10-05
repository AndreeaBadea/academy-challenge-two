package com.playtika.java.academy.challenge1.badea.andreea.classes.statistics;

import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.BonusShield;
import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.enums.ShieldType;
import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.interfaces.Processable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class MostUsedShieldTypeCategory implements Processable {

    @Override
    public BonusShield[] process(List<BonusShield> bonusShields) {

        CompletableFuture<BonusShield[]> taskAsync = CompletableFuture.supplyAsync(() -> {

            ShieldType type = bonusShields.parallelStream()
                    .collect(Collectors.groupingBy(BonusShield::getType, Collectors.counting()))
                    .entrySet()
                    .parallelStream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            return bonusShields.parallelStream().filter(x -> x.getType().equals(type)).toArray(BonusShield[]::new);
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


