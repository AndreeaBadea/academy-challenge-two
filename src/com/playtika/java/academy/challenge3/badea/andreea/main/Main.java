package com.playtika.java.academy.challenge3.badea.andreea.main;

import com.playtika.java.academy.challenge1.badea.andreea.classes.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge3.badea.andreea.models.*;
import com.playtika.java.academy.challenge3.badea.andreea.models.enums.ServerType;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.GameServer;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.Repository;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.Server;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.ServerCommand;
import com.playtika.java.academy.challenge3.badea.andreea.repository.PlayerRepo;
import com.playtika.java.academy.challenge3.badea.andreea.services.ConnectCommand;
import com.playtika.java.academy.challenge3.badea.andreea.services.ConnectionChecker;
import com.playtika.java.academy.challenge3.badea.andreea.services.DisconnectCommand;
import com.playtika.java.academy.challenge3.badea.andreea.services.Reader;

import java.io.IOException;
import java.util.List;

public class Main {

    public final static String CONFIG_FILE = "config.txt";

    public static void main(String[] args) throws IOException, PlayerProfileException {

        Reader reader = new Reader();
        ServerType serverType = reader.readFromFile(CONFIG_FILE);
        GameServerCreator gameServerCreator = new GameServerCreator();
        Server firstGameServer = gameServerCreator.createOrGetExistent(serverType);

        firstGameServer.start();
        firstGameServer.stop();

        AbstractPlayer player1 = new PlayerProfile("player1", "player1@gmail.com");
        AbstractPlayer player2 = new PlayerProfile("player2", "player2@gmail.com");
        AbstractPlayer player3 = new PlayerProfile("player3", "player3@gmail.com");
        AbstractPlayer player4 = new PlayerProfile("player4", "player4@gmail.com");

        AbstractPlayer team1 = new TeamProfile("team1");
        AbstractPlayer team2 = new TeamProfile("team2");

        team1.add(player1);
        team1.add(player2);

        team2.add(team1);
        team2.add(player3);
        team2.add(player4);
        team2.remove(player3);

        Server secondGameServer = gameServerCreator.createOrGetExistent(serverType);
        secondGameServer.start();
        secondGameServer = new ConnectionChecker(secondGameServer);
        secondGameServer.start();

        Repository<AbstractPlayer> repository = new PlayerRepo();

        repository.add(player1);
        repository.add(player2);
        List<AbstractPlayer> list1 = repository.getLatest(2);
        System.out.println(list1);

        repository.add(team2);
        List<AbstractPlayer> list2 = repository.getAll("te");
        System.out.println(list2);

        Server serverInUse = gameServerCreator.createOrGetExistent(serverType);
        ServerCommand connectCommand = new ConnectCommand((GameServer) serverInUse);
        ServerCommand disconnectCommand = new DisconnectCommand((GameServer) serverInUse);

        team1.setServerCommand(connectCommand);
        team1.connect();
        System.out.println("---");

        team1.setServerCommand(disconnectCommand);
        team1.disconnect();
        System.out.println("---");


        team2.setServerCommand(connectCommand);
        team2.connect();
        System.out.println("---");

        team2.setServerCommand(connectCommand);
        team2.disconnect();
        System.out.println("---");

    }







}

