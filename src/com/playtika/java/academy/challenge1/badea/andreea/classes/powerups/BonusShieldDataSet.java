package com.playtika.java.academy.challenge1.badea.andreea.classes.powerups;

import com.playtika.java.academy.challenge1.badea.andreea.classes.powerups.interfaces.Processable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class BonusShieldDataSet implements Cloneable {

    private List<BonusShield> shields;

    public BonusShieldDataSet(List<BonusShield> shields) {
        this.shields = shields;
    }

    public void printToFile(String filename){
        File file = new File(filename);
        Processable processable = null;
        FileWriter fileWriter ;
        try {
            fileWriter = new FileWriter(filename, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(Arrays.toString(process(processable)));
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNoShields(){
        return shields.size();
    }

    public void addShield(BonusShield shield){
        shields.add(shield);
    }

    public BonusShield getBonusShield(String name){
       for(BonusShield bonusShield : shields){
           if(name.equals((bonusShield.getName()))){
               return bonusShield;
           }
       }
       return null;
    }


    public List<BonusShield> getShields() {
        return shields;
    }

    public void removeBonusShield(String name){
        shields.remove(getBonusShield(name));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public BonusShield[] process(Processable processor){
        return processor.process(shields);
    }
}
