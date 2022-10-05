package com.playtika.java.academy.challenge1.badea.andreea.classes.player.interfaces;

import java.io.IOException;

public interface Restorable {

    void saveProfile(String fileName) throws IOException;
    void loadProfile(String fileName) throws IOException;
}
