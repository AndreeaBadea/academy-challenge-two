package com.playtika.java.academy.challenge3.badea.andreea.models;

import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.ServerCommand;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Objects;


public class TeamProfile extends AbstractPlayer implements Comparable<TeamProfile> {

    private String teamName;
    private LocalDate creationDate;
    private ServerCommand serverCommand;


    public TeamProfile(String teamName) {
        this.teamName = teamName;
        this.creationDate = LocalDate.now();
    }

    @Override
    public void connect() {
        serverCommand.execute(this);
        for(AbstractPlayer abstractPlayer : playersOnServer){
            abstractPlayer.setServerCommand(serverCommand);
            abstractPlayer.connect();
        }
    }

    @Override
    public void disconnect() {
        serverCommand.execute(this);
        for(AbstractPlayer abstractPlayer : playersOnServer){
            abstractPlayer.setServerCommand(serverCommand);
            abstractPlayer.disconnect();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamProfile that = (TeamProfile) o;
        return Objects.equals(teamName, that.teamName) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, creationDate);
    }

    @Override
    public String getName() {
        return teamName;
    }

    @Override
    public void setServerCommand(ServerCommand serverCommand) {
        this.serverCommand = serverCommand;
    }

    @Override
    public int compareTo(@NotNull TeamProfile o) {
        return o.creationDate.compareTo(creationDate);
    }

    @Override
    public String toString() {
        return "TeamProfile{" +
                "teamName='" + teamName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
