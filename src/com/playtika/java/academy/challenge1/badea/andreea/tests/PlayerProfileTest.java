package com.playtika.java.academy.challenge1.badea.andreea.tests;

import com.playtika.java.academy.challenge1.badea.andreea.classes.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge1.badea.andreea.classes.player.PlayerProfile;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;

public class PlayerProfileTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void getTotalPlayedTimeThrowExceptionTest() throws PlayerProfileException {
        PlayerProfile testProfile = new PlayerProfile("test01", "test01@gmail.com");
        exceptionRule.expect(PlayerProfileException.class);
        exceptionRule.expectMessage("Array which stores minutes played per session not initialized");
        testProfile.getTotalPlayedTime();
    }

    @Test
    public void getTotalPlayedTimeTest() throws PlayerProfileException {
        PlayerProfile testProfile = new PlayerProfile("test01", "test01@gmail.com");
        testProfile.setMinutesPlayedPerSession(new int[]{10, 20});
        assertEquals("Total played time can not be calculated.", 30, testProfile.getTotalPlayedTime());
    }

    @Test
    public void classHasOneConstructorTest() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.playtika.java.academy.challenge1.badea.andreea.classes.player.PlayerProfile");
        Constructor[] constructors = c.getDeclaredConstructors();
        assertEquals("Class has more than one constructor.", 1, constructors.length);
    }


    @Test
    public void constructorHasTwoStringArgsTest() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> c = Class.forName("com.playtika.java.academy.challenge1.badea.andreea.classes.player.PlayerProfile");
        Constructor constructor = c.getDeclaredConstructor(String.class, String.class);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        assertEquals("Class does not have 2 arguments of type String.", 2, parameterTypes.length);

    }


    @Test
    public void setEmailThrowExceptionTest() throws PlayerProfileException {
        boolean exceptionThrown = false;
        PlayerProfile testProfile = new PlayerProfile("test01", "test01@gmail.com");
        try {
            testProfile.setEmail("updatedEmail");
        } catch (PlayerProfileException playerProfileException) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);

    }

    @Test
    public void setEmailTest() throws PlayerProfileException {
        PlayerProfile testProfile = new PlayerProfile("test01", "test01@gmail.com");
        testProfile.setEmail("updatedEmail@gmail.com");
        assertEquals("Email can not be set.","updatedEmail@gmail.com", testProfile.getEmail() );
    }



}
