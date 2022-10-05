package com.playtika.java.academy.challenge3.badea.andreea.models.interfaces;

public interface ServiceLocatorInterface {

    void register(Class contract, Class implementation);

    <T> T resolve(Class contract);
}
