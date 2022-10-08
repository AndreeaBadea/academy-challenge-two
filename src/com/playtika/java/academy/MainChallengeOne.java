package com.playtika.java.academy;

import com.playtika.java.academy.challenge1.badea.andreea.main.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge1.badea.andreea.main.player.PlayerProfile;
import com.playtika.java.academy.challenge1.badea.andreea.main.player.interfaces.GeneratePoints;
import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.AdditionalGunShield;
import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.BrokenShield;
import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.enums.ShieldType;

import java.io.IOException;
import java.util.Random;

public class MainChallengeOne {
    public static void main(String[] args) throws PlayerProfileException, CloneNotSupportedException {

        PlayerProfile profileOne = new PlayerProfile("user01", "user01@gmail.com");
        System.out.println("Number of profiles before creating profile two:  " + PlayerProfile.getNbOfProfiles());
        PlayerProfile profileTwo = new PlayerProfile("user02", "user02@gmail.com");
        System.out.println("Number of profiles after creating profile two: " + PlayerProfile.getNbOfProfiles());
        System.out.println("");

        System.out.println("First profile description: " + profileOne);
        System.out.println("Second profile description: " + profileTwo);
        System.out.println("");

        profileOne.setMinutesPlayedPerSession(new int[] {10, 20});
        profileOne.addNewPlaySession();
        profileOne.updateLastPlayedTime(4);
        System.out.println(profileOne);

        profileTwo.setMinutesPlayedPerSession(new int[] {2, 5, 1});
        profileTwo.addNewPlaySession();
        profileTwo.updateLastPlayedTime(30);
        profileTwo.addNewPlaySession();
        profileTwo.updateLastPlayedTime(50);
        System.out.println(profileTwo);
        System.out.println("");

        System.out.println("Number of profiles: " + PlayerProfile.getNbOfProfiles());

        PlayerProfile clonedProfile = (PlayerProfile) profileOne.clone();

        System.out.println("Cloned profile: " + clonedProfile);
        System.out.println("Number of profiles after creating a clone: " + PlayerProfile.getNbOfProfiles());
        System.out.println("");

        clonedProfile.setEmail("clonedUser@gmail.com");
        clonedProfile.addNewPlaySession();
        clonedProfile.updateLastPlayedTime(11);
        System.out.println("Cloned profile after update: " + clonedProfile);
        System.out.println("Unchanged profile one (deep copy was made): " + profileOne);
        System.out.println("");

        System.out.println("Maximum played time for cloned profile: " + clonedProfile.getMaxPlayedMinutes());
        System.out.println("Player age for cloned profile: " + clonedProfile.getPlayerAgeInDays());
        System.out.println("Total played time: " + clonedProfile.getTotalPlayedTime());
        System.out.println("");


        AdditionalGunShield additionalGunShield  = new AdditionalGunShield(2500, "addGunShield1", ShieldType.Invincible, "KILL", 10);
        System.out.println(additionalGunShield);

        additionalGunShield.hits(20);
        System.out.println("After hits: " + additionalGunShield);

        System.out.println("");
        BrokenShield brokenShield = new BrokenShield(2500, "brokenShield", ShieldType.Strong, 5);
        System.out.println(brokenShield);

        brokenShield.takesAHit(2);
        System.out.println("After takesAHit: " + brokenShield);
        brokenShield.useBonusPoints(()-> new Random().nextInt(20));
        System.out.println("After using bonus points: " + brokenShield);

        brokenShield.disable();
        brokenShield.takesAHit(2);
        System.out.println("After takesAHit: " + brokenShield);

        System.out.println("");
        System.out.println("Initial score: " + additionalGunShield.getScore());
        System.out.println("Generating points.. ");
        GeneratePoints generator = points -> points * points;
        additionalGunShield.hits( generator.generate(3));
        System.out.println("Score after hits: " + additionalGunShield.getScore());

        GeneratePoints generatePoints = new GeneratePoints() {
            @Override
            public int generate(int points) {
                return 0;
            }
        };
        additionalGunShield.hits(generatePoints.generate(20));

        System.out.println("");
        PlayerProfile profileThree = new PlayerProfile("test3", "test@gmail.com");
        int[] minutes = new int[]{11, 7, 1, 9};
        profileThree.setMinutesPlayedPerSession(minutes);
        System.out.println("Profile three: " + profileThree);

        profileThree.addPowerUp(additionalGunShield, brokenShield);
        System.out.println(profileThree.getPowerUps().size());
        System.out.println(profileThree.getPowerUp("brokenShield"));
        profileThree.removePowerUp(additionalGunShield.getName());
        System.out.println(profileThree.getPowerUps().size());
        System.out.println(profileThree.getPowerUp(BrokenShield.class));

        try {
            profileThree.saveProfile("logs.txt");
            System.out.println("");
            System.out.println("Reading from file..");
            profileThree.loadProfile("logs.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
