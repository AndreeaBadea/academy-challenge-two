package com.playtika.java.academy.challenge1.badea.andreea.main.statistics;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class OnlinePlayers {

    public int  getNumberOfOnlinePlayers(){
        CompletableFuture<Integer> taskAsync = CompletableFuture.supplyAsync(() -> {
            try{
                Random random = new Random();
                TimeUnit.SECONDS.sleep(4);
                return random.nextInt(10000);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        });
        int numberOfPlayers;
        try {
            numberOfPlayers = taskAsync.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return numberOfPlayers;
    }
}
