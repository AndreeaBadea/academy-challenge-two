package com.playtika.java.academy.challenge3.badea.andreea.models;

import com.playtika.java.academy.challenge3.badea.andreea.models.enums.ServerType;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.Server;

public class GameServerCreator {

    Server onlineServer = null;
    Server localServer = null;

    public Server createNewServer(ServerType serverType){
        switch(serverType){
            case ONLINE: return new OnlineServer();
            case LOCAL: return new LocalServer();
            default: return null;
        }
    }

    public Server createOrGetExistent(ServerType serverType){
            switch (serverType){
                case ONLINE: {
                    if(onlineServer == null){
                        return new OnlineServer();
                    }
                    return onlineServer;
                }
                case LOCAL: {
                    if(localServer == null){
                      return new LocalServer();
                    }
                    return localServer;
                }
                default: return null;
            }
        }
    }



