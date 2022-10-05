package com.playtika.java.academy.challenge3.badea.andreea.services;

import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.ServiceLocatorInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ServiceLocator implements ServiceLocatorInterface {

    Map<Class, Class> contractMap = new HashMap<>();

    @Override
    public void register(Class contract, Class implementation){
        contractMap.put(contract, implementation);
    }

    @Override
    public <T> T resolve(Class contract){
        Class implementation = contractMap.get(contract);
        try {
            T object = (T) implementation.getDeclaredConstructor().newInstance();
            return object;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
