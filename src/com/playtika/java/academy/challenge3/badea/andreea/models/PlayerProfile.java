package com.playtika.java.academy.challenge3.badea.andreea.models;

import com.playtika.java.academy.challenge1.badea.andreea.main.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.ServerCommand;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerProfile extends AbstractPlayer implements Cloneable, Comparable<PlayerProfile> {
    private String userName;
    private String email;
    private LocalDate creationDate;

    private final String REGEX = "^(.+)@(.+)$";

    private ServerCommand serverCommand;

    public PlayerProfile(String userName, String email) throws PlayerProfileException {
        this.userName = userName;
        setEmail(email);
        this.creationDate = LocalDate.now();
    }


    public void setEmail(String email) throws PlayerProfileException {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            this.email = email;
        } else throw new PlayerProfileException("Email is not valid.");
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PlayerProfile{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        PlayerProfile clonedPlayerProfile = (PlayerProfile) super.clone();
        clonedPlayerProfile.creationDate = creationDate;
        return clonedPlayerProfile;
    }


    @Override
    public void add(AbstractPlayer abstractPlayer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(AbstractPlayer abstractPlayer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return userName;
    }

    @Override
    public void setServerCommand(ServerCommand serverCommand) {
        this.serverCommand = serverCommand;
    }

    @Override
    public void connect() {
        serverCommand.execute(this);
        System.out.println("Player " + userName + " connected.");
    }

    @Override
    public void disconnect() {
        serverCommand.execute(this);
        System.out.println("Player: " + userName + " disconnected.");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerProfile that = (PlayerProfile) o;
        return userName.equals(that.userName) && creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, creationDate);
    }


    @Override
    public int compareTo(@NotNull PlayerProfile o) {
        return o.creationDate.compareTo(creationDate);
    }



}
