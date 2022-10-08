package com.playtika.java.academy.challenge1.badea.andreea.main.threads;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class ThreadBackupFile implements Runnable {
    private boolean isShutDown = false;
    private File backupFile;
    private int delay;


    public ThreadBackupFile(String backupFileName, int delay) throws IOException {
        File backupFile = new File(backupFileName);
        if(!backupFile.exists()){
            backupFile.createNewFile();
        }
        this.backupFile = backupFile;
        this.delay = delay;
    }

    public void setShutDown() {
        this.isShutDown = true;
    }


    @Override
    public void run() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(backupFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            while (!isShutDown) {
                printWriter.println("Autosaved at " + new Timestamp(System.currentTimeMillis()));
                TimeUnit.SECONDS.sleep(delay);
            }
            printWriter.println("Final autosaved: " + new Timestamp(System.currentTimeMillis()));
            printWriter.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}
