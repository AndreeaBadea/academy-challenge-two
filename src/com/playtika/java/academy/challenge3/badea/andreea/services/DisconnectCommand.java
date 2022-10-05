package com.playtika.java.academy.challenge3.badea.andreea.services;

import com.playtika.java.academy.challenge3.badea.andreea.models.AbstractPlayer;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.GameServer;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.ServerCommand;

public class DisconnectCommand implements ServerCommand {

    GameServer gameServer;

    public DisconnectCommand(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public void execute(Object o) {
        AbstractPlayer player = (AbstractPlayer) o;
        gameServer.getPlayersOnServer().remove(player);
    }


}
