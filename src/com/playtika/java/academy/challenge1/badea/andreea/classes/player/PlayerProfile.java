package com.playtika.java.academy.challenge1.badea.andreea.classes.player;

import com.playtika.java.academy.challenge1.badea.andreea.classes.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge1.badea.andreea.classes.player.interfaces.Restorable;
import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.BonusShield;
import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.interfaces.PowerUp;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerProfile implements Cloneable, Restorable {
    private String userName;
    private String email;
    private LocalDate creationDate;
    private int[] minutesPlayedPerSession;
    private List<PowerUp> powerUps = new ArrayList<>();

    private static int NB_OF_PROFILES;
    private final String REGEX = "^(.+)@(.+)$";

    public PlayerProfile(String userName, String email) throws PlayerProfileException {
        this.userName = userName;
        setEmail(email);
        this.creationDate = LocalDate.now();
        NB_OF_PROFILES++;
    }


    public void setEmail(String email) throws PlayerProfileException {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            this.email = email;
        } else throw new PlayerProfileException("Email is not valid.");
    }


    public void setMinutesPlayedPerSession(int[] minutesPlayedPerSession) {
        this.minutesPlayedPerSession = Arrays.copyOf(minutesPlayedPerSession, minutesPlayedPerSession.length);
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalPlayedTime() throws PlayerProfileException {
        if (minutesPlayedPerSession == null) {
            throw new PlayerProfileException("Array which stores minutes played per session not initialized");
        }
        int totalTime = 0;
        for (int minutesPlayed : minutesPlayedPerSession) {
            totalTime += minutesPlayed;
        }
        return totalTime;
    }

    public int getPlayerAgeInDays() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int playerAge = (int) creationDate.until(localDateTime, ChronoUnit.DAYS);
        return playerAge;
    }

    public int getMaxPlayedMinutes() {
        int[] copyOfMaxPlayedMinutes = Arrays.copyOf(minutesPlayedPerSession, minutesPlayedPerSession.length);
        Arrays.sort(copyOfMaxPlayedMinutes);
        return copyOfMaxPlayedMinutes[copyOfMaxPlayedMinutes.length - 1];
    }

    public int[] getMinutesPlayedPerSession() {
        return Arrays.copyOf(minutesPlayedPerSession, minutesPlayedPerSession.length);
    }

    public static int getNbOfProfiles() {
        return NB_OF_PROFILES;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void addNewPlaySession() {
        int[] copyPlayedTime = Arrays.copyOf(minutesPlayedPerSession, minutesPlayedPerSession.length + 1);
        minutesPlayedPerSession = Arrays.copyOf(copyPlayedTime, copyPlayedTime.length);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PlayerProfile{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", minutesPlayedPerSession=");
        if (minutesPlayedPerSession == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < minutesPlayedPerSession.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(minutesPlayedPerSession[i]);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        PlayerProfile clonedPlayerProfile = (PlayerProfile) super.clone();
        clonedPlayerProfile.creationDate = creationDate;
        clonedPlayerProfile.minutesPlayedPerSession = Arrays.copyOf(minutesPlayedPerSession, minutesPlayedPerSession.length);
        return clonedPlayerProfile;
    }

    public void updateLastPlayedTime(int time) {
        minutesPlayedPerSession[minutesPlayedPerSession.length - 1] += time;
    }

    public void addPowerUp(PowerUp... powerUpShields) {
        if(powerUps == null){
            powerUps = new ArrayList<>();
        }
        powerUps.addAll(List.of(powerUpShields));
    }


    public void removePowerUp(String name) {
        PowerUp powerUpToRemove = getPowerUp(name);
        if(powerUpToRemove != null) {
           powerUps.remove(powerUpToRemove);
        }
    }

    public PowerUp getPowerUp(String name) {
        for (PowerUp powerUp : powerUps) {
            if(powerUp instanceof BonusShield) {
                BonusShield bonusShield = (BonusShield) powerUp;
                if (bonusShield.getName().equals(name)) {
                    return powerUp;
                }
            }
        }
        throw new UnsupportedOperationException("This PowerUp can not be found.");
    }


    public PowerUp getPowerUp(Class<?> type) {
        for (PowerUp powerUp : powerUps) {
            if (powerUp.getClass() == type) {
                return powerUp;
            }
        }
        throw new UnsupportedOperationException("This PowerUp could not be found.");
    }


    @Override
    public void saveProfile(String fileName) throws IOException {
        File logFile = new File(fileName);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(logFile, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(this);
        printWriter.close();
    }

    @Override
    public void loadProfile(String fileName) throws IOException {
        File logFile = new File(fileName);
        FileReader fileReader = new FileReader(logFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = "";
        while ((textLine = bufferedReader.readLine()) != null) {
            System.out.println(textLine);
        }
        bufferedReader.close();
    }

}
