package com.playtika.java.academy.challenge3.badea.andreea.services;

import com.playtika.java.academy.challenge3.badea.andreea.exceptions.NotConnectedToInternetException;
import com.playtika.java.academy.challenge3.badea.andreea.models.LocalServer;
import com.playtika.java.academy.challenge3.badea.andreea.models.OnlineServer;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.Server;

import java.io.IOException;

public class ConnectionChecker implements Server {

    private Server gameServer;

    private static final String URL_TO_PING = "ping www.geeksforgeeks.org";


    public ConnectionChecker(Server gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public void start() {
        try {
            if(checkInternetConnection()){
                if(gameServer instanceof LocalServer){
                    LocalServer localServer = (LocalServer) gameServer;
                    localServer.start();
                }else if(gameServer instanceof OnlineServer){
                    OnlineServer onlineServer = (OnlineServer) gameServer;
                    onlineServer.start();
                }
            }
        } catch (IOException | NotConnectedToInternetException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {

    }

    public static boolean checkInternetConnection() throws IOException, NotConnectedToInternetException, InterruptedException {
    Process process = Runtime.getRuntime().exec(URL_TO_PING);
    int code = process.waitFor();
    if(code == 0){
        System.out.println("Connected to internet.");
        return true;
    }else{
        throw new NotConnectedToInternetException("You need connection to internet to join the server.");
    }
    }
}
