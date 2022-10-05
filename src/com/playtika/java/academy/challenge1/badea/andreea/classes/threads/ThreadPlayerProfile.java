package com.playtika.java.academy.challenge1.badea.andreea.classes.threads;

import com.playtika.java.academy.challenge1.badea.andreea.classes.player.PlayerProfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public class ThreadPlayerProfile implements Callable<PlayerProfile> {

    File playerProfileFile;

    public ThreadPlayerProfile(File playerProfileFile) {
        this.playerProfileFile = playerProfileFile;
    }

    @Override
    public PlayerProfile call() throws Exception {
        FileReader fileReader = new FileReader(playerProfileFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String username = bufferedReader.readLine();
        String email = bufferedReader.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.from(LocalDate.parse(bufferedReader.readLine(), formatter).atStartOfDay());
        int[] minutesPlayedPerSession = bufferedReader.readLine().chars().toArray();

        PlayerProfile playerProfile = new PlayerProfile(username, email);
        playerProfile.setCreationDate(localDate);
        playerProfile.setMinutesPlayedPerSession(minutesPlayedPerSession);
        bufferedReader.close();

        return playerProfile;

    }
}
