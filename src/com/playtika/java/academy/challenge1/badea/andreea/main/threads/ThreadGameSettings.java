package com.playtika.java.academy.challenge1.badea.andreea.main.threads;

import com.playtika.java.academy.challenge1.badea.andreea.main.game.GameSettings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class ThreadGameSettings implements Callable<GameSettings> {

    private File gameSettingsFile;


    public ThreadGameSettings(File gameSettingsFile) {
        this.gameSettingsFile = gameSettingsFile;
    }

    @Override
    public GameSettings call() throws Exception {
        FileReader fileReader = new FileReader(gameSettingsFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String clientVersion = bufferedReader.readLine().split("=")[1];
        String autoSave = bufferedReader.readLine().split("")[1];
        int autoSaveTimeout = Integer.parseInt(bufferedReader.readLine().split("=")[1]);
        String serverURL = bufferedReader.readLine().split("=")[1];

        GameSettings gameSettings = new GameSettings(clientVersion, autoSaveTimeout, serverURL);
        if(autoSave.equals("true")){
            gameSettings.startAutoSave();
        }else{
            gameSettings.stopAutoSave();
        }
        bufferedReader.close();
        return gameSettings;
    }



}
