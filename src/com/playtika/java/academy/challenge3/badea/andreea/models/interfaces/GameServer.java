package com.playtika.java.academy.challenge3.badea.andreea.models.interfaces;

import com.playtika.java.academy.challenge3.badea.andreea.models.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;

public abstract class GameServer implements Server{

    List<AbstractPlayer> playersOnServer = new ArrayList<>();

    public List<AbstractPlayer> getPlayersOnServer() {
        return playersOnServer;
    }

    public abstract void start();

    public abstract void stop();
}
