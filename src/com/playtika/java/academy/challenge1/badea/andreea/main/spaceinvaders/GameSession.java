package com.playtika.java.academy.challenge1.badea.andreea.main.spaceinvaders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class GameSession extends Thread {
    private int difficultyLevel;
    private int score;
    private LinkedBlockingDeque<SpaceInvader> linkedBlockingDeque;
    private List<SpaceInvader> spaceInvaderList = new ArrayList<>(20);

    public volatile boolean gameIsFinished;

    public GameSession(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public GameSession(int difficultyLevel, LinkedBlockingDeque<SpaceInvader> linkedBlockingDeque) {
        this.difficultyLevel = difficultyLevel;
        this.linkedBlockingDeque = linkedBlockingDeque;
    }


    public void addSpaceInvader(SpaceInvader spaceInvader) {
        spaceInvaderList.add(spaceInvader);
    }

    public LinkedBlockingDeque<SpaceInvader> getLinkedBlockingDeque() {
        return this.linkedBlockingDeque;
    }

    public void increaseDifficulty() {
        difficultyLevel++;
    }

    public void reduceDifficulty() {
        if (this.difficultyLevel - 1 >= 0) {
            this.difficultyLevel--;
        }
    }


    public void print() {
        for (SpaceInvader spaceInvader : spaceInvaderList) {
            System.out.println(spaceInvader);
        }
    }

    public void isGameFinished(boolean gameIsFinished) {
        this.gameIsFinished = gameIsFinished;
    }

    public boolean isSpaceInvaderInPosition(SpaceInvader spaceInvader) {
        for (SpaceInvader iterator : this.spaceInvaderList) {
            if (iterator.getX() == spaceInvader.getX()
                    && iterator.getY() == spaceInvader.getY()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void run() {
        System.out.println("");
        System.out.println("-------------------START GAME----------------------");
        for (int i = 0; i < 20; i++) {
            try {
                SpaceInvader spaceInvader = linkedBlockingDeque.takeFirst();
                spaceInvaderList.add(spaceInvader);
                System.out.println(spaceInvader);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while (!gameIsFinished) {
            System.out.println("---------------KILLED SPACE INVADORS-------------");
            for (int i = 1; i <= 5; i++) {
                SpaceInvader spaceInvader = spaceInvaderList.remove(spaceInvaderList.size() - i);
                System.out.println(spaceInvader);
            }

            try {
                System.out.println("--------------NEW SPACE INVADORS------------");
                for (int i = 0; i < 5; i++) {
                    SpaceInvader spaceInvader = linkedBlockingDeque.takeFirst();

                    if (isSpaceInvaderInPosition(spaceInvader)) {
                        spaceInvader = linkedBlockingDeque.takeFirst();
                        while (isSpaceInvaderInPosition(spaceInvader)) {
                            spaceInvader = linkedBlockingDeque.takeFirst();
                        }

                        spaceInvaderList.add(spaceInvader);
                        System.out.println(spaceInvader);

                    } else {
                        System.out.println(spaceInvader);
                        spaceInvaderList.add(spaceInvader);
                    }
                }
                TimeUnit.SECONDS.sleep(5);
                System.out.println();
                System.out.println("*************SPACE INVADORS ON THE SCREEN************");

                for (SpaceInvader spaceInvader : this.spaceInvaderList) {
                    System.out.println(spaceInvader);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}