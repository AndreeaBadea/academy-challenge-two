package com.playtika.java.academy.challenge3.badea.andreea.models;

import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.Connection;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.ServerCommand;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements Connection {

    protected List<AbstractPlayer> playersOnServer = new ArrayList<>();

    public void add(AbstractPlayer abstractPlayer) {
        playersOnServer.add(abstractPlayer);
    }

    public void remove(AbstractPlayer abstractPlayer) {
        playersOnServer.remove(abstractPlayer);
    }

    public abstract String getName();

    public abstract void setServerCommand(ServerCommand serverCommand);


}
