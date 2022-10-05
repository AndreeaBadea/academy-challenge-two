package com.playtika.java.academy.challenge3.badea.andreea.services;

import com.playtika.java.academy.challenge3.badea.andreea.models.enums.ServerType;

import java.io.*;

public class Reader {

    public ServerType readFromFile(String fileName) throws IOException {
        File configFile = new File(fileName);
        FileReader fileReader = new FileReader(configFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String serverType = bufferedReader.readLine().toUpperCase();
        return ServerType.valueOf(serverType);
    }


}
