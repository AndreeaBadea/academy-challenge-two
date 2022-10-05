package com.playtika.java.academy.challenge1.badea.andreea.classes.game;

public class GameSettings {
    private String clientVersion;
    private boolean autoSave;
    private int autoSaveTimeoutInSeconds;
    private String serverURL;


    public GameSettings(String clientVersion, int autoSaveTimeoutInSeconds, String serverURL) {
        this.clientVersion = clientVersion;
        this.autoSaveTimeoutInSeconds = autoSaveTimeoutInSeconds;
        this.serverURL = serverURL;
    }

    public int getAutoSaveTimeoutInSeconds() {
        return autoSaveTimeoutInSeconds;
    }

    public void stopAutoSave(){
        this.autoSave = false;
    }

    public void startAutoSave(){
        this.autoSave = true;
    }

    @Override
    public String toString() {
        return "GameSettings{" +
                "clientVersion='" + clientVersion + '\'' +
                ", autoSave=" + autoSave +
                ", autoSaveTimeoutInSeconds=" + autoSaveTimeoutInSeconds +
                ", serverURL='" + serverURL + '\'' +
                '}';
    }
}
