package com.playtika.java.academy.challenge1.badea.andreea.main.spaceinvaders;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class RandomInvadersSupplier implements Runnable{

    private volatile boolean hasFinishedGeneratingData;
    private int initialSeed;
    LinkedBlockingDeque<SpaceInvader> linkedBlockingDeque;

    public RandomInvadersSupplier(int initialSeed, LinkedBlockingDeque<SpaceInvader> linkedBlockingDeque) {
        super();
        this.initialSeed = initialSeed;
        this.linkedBlockingDeque = linkedBlockingDeque;
    }

    public void stopGenerating() {
        this.hasFinishedGeneratingData = true;
    }

    @Override
    public void run() {
        while (!hasFinishedGeneratingData) {
            if (linkedBlockingDeque.size() < 200) {
                Random random = new Random();
                int x = random.nextInt(50);
                int y = random.nextInt(50);
                boolean damage = random.nextBoolean();
                this.linkedBlockingDeque.add(new SpaceInvader(x, y, damage));
            }
        }
    }
}
