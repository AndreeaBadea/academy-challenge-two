package com.playtika.java.academy;

import com.playtika.java.academy.challenge1.badea.andreea.main.exceptions.NoGameSettingsException;
import com.playtika.java.academy.challenge1.badea.andreea.main.exceptions.NoPlayerDataException;
import com.playtika.java.academy.challenge1.badea.andreea.main.exceptions.NoPlayerProfileException;
import com.playtika.java.academy.challenge1.badea.andreea.main.game.GameSettings;
import com.playtika.java.academy.challenge1.badea.andreea.main.player.PlayerProfile;
import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.BonusShield;
import com.playtika.java.academy.challenge1.badea.andreea.main.powerups.BonusShieldDataSet;
import com.playtika.java.academy.challenge1.badea.andreea.main.spaceinvaders.GameSession;
import com.playtika.java.academy.challenge1.badea.andreea.main.spaceinvaders.RandomInvadersSupplier;
import com.playtika.java.academy.challenge1.badea.andreea.main.spaceinvaders.SpaceInvader;
import com.playtika.java.academy.challenge1.badea.andreea.main.statistics.*;
import com.playtika.java.academy.challenge1.badea.andreea.main.threads.ThreadBackupFile;
import com.playtika.java.academy.challenge1.badea.andreea.main.threads.ThreadGameSettings;
import com.playtika.java.academy.challenge1.badea.andreea.main.threads.ThreadPlayerProfile;
import com.playtika.java.academy.challenge1.badea.andreea.main.threads.ThreadPowerUps;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class MainChallengeTwo {

    private static final String BACKUP_FILE = "backup.txt";

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        System.out.println("*************************** CHALLENGE 2 ***************************");
        System.out.println("*******************************************************************");

        if (args.length == 0){
            System.out.println("You should upload the next files: game_settings, player_profile, power_ups");
            return;
        }

        File gameSettingsFile = new File(args[0]);
        if(!gameSettingsFile.exists()){
            throw new NoGameSettingsException("gameSettingsFile not found!");
        }

        File playerProfileFile = new File(args[1]);
        if(!playerProfileFile.exists()){
            throw new NoPlayerProfileException("playerProfileFile not found!");
        }

        File powerUpsFile = new File(args[2]);
        if(!powerUpsFile.exists()){
            throw new NoPlayerDataException("powerUpsFile not found!");
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<GameSettings> gameSettingsFuture = executorService.submit(new ThreadGameSettings(gameSettingsFile));
        Future<PlayerProfile> playerProfileFuture = executorService.submit(new ThreadPlayerProfile(playerProfileFile));
        Future<List<BonusShield>> bonusShieldFuture = executorService.submit(new ThreadPowerUps(powerUpsFile));


        ThreadPoolExecutor threadManager = (ThreadPoolExecutor) executorService;

        while(threadManager.getCompletedTaskCount() != 3){
            System.out.println("Loading..");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println("START GAME");

        OnlinePlayers onlinePlayers = new OnlinePlayers();
        System.out.println("Online players: " + onlinePlayers.getNumberOfOnlinePlayers());


        GameSettings gameSettings = gameSettingsFuture.get();
        PlayerProfile playerProfile = playerProfileFuture.get();
        List<BonusShield> bonusShieldList = bonusShieldFuture.get();

        System.out.println("");
        System.out.println(gameSettings);
        System.out.println(playerProfile);
        System.out.println(bonusShieldList.size());

        System.out.println("");
        System.out.println("First 50 bonus shields:");
        Collections.sort(bonusShieldList);
        bonusShieldList.parallelStream().limit(50).forEach(System.out::println);

        ThreadBackupFile threadBackupFile = new ThreadBackupFile(BACKUP_FILE, gameSettings.getAutoSaveTimeoutInSeconds());
        Thread backupFileThread = new Thread(threadBackupFile);
        backupFileThread.start();
        TimeUnit.SECONDS.sleep(6);

        LinkedBlockingDeque<SpaceInvader> linkedBlockingDeque = new LinkedBlockingDeque<>(200);
        GameSession gameSession = new GameSession(1, linkedBlockingDeque);
        RandomInvadersSupplier randomInvadersSupplier = new RandomInvadersSupplier(5, linkedBlockingDeque);
        Thread randomInvadersThread = new Thread(randomInvadersSupplier);

        randomInvadersThread.start();
        TimeUnit.SECONDS.sleep(3);
        gameSession.start();

        TimeUnit.SECONDS.sleep(20);

        gameSession.isGameFinished(true);
        randomInvadersSupplier.stopGenerating();

        gameSession.join();
        randomInvadersThread.join();
        System.out.println("----------------FINISHED GAME----------------");


        BonusShieldDataSet bonusShieldDataSet = new BonusShieldDataSet(bonusShieldList);
        BonusShield[] mostUsedShieldTypeCategory = bonusShieldDataSet.process(new MostUsedShieldTypeCategory());
        BonusShield[] topTenBonusShields = bonusShieldDataSet.process(new TopTenBonusShields());
        BonusShield[] bonusSheildsByKeyword = bonusShieldDataSet.process(new BonusSheildByKeyword());
        BonusShield[] bonusShieldsWithMaxScore = bonusShieldDataSet.process(new BonusShieldsWithMaxScore());

        try {
            Statistic.saveStatisticToFile("mostUsedShieldTypeCategory.txt", mostUsedShieldTypeCategory);
            Statistic.saveStatisticToFile("topTenBonusShields.txt", topTenBonusShields);
            Statistic.saveStatisticToFile("containsKeywordBonusShields.txt", bonusSheildsByKeyword);
            Statistic.saveStatisticToFile("maxScoreInCategory.txt", bonusShieldsWithMaxScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        threadBackupFile.setShutDown();
        backupFileThread.join();
        executorService.shutdown();

    }
}
