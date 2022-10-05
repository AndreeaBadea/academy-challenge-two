package com.playtika.java.academy.challenge3.badea.andreea.models;

import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.GameServer;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.Server;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class OnlineServer extends GameServer {

    private LocalDateTime startTime;

    protected OnlineServer(){

    }

    @Override
    public void start() {
        startTime = LocalDateTime.now();
        System.out.println("Local game server started at " + startTime);
    }

    @Override
    public void stop() {
        System.out.println("Local game server stopped after " + ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()) + " seconds.");
    }
}
