package com.playtika.java.academy.challenge3.badea.andreea.repository;

import com.playtika.java.academy.challenge3.badea.andreea.models.AbstractPlayer;
import com.playtika.java.academy.challenge3.badea.andreea.models.interfaces.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerRepo implements Repository<AbstractPlayer> {

    private List<AbstractPlayer> playersList = new ArrayList<>();


    @Override
    public void add(AbstractPlayer abstractPlayer) {
        playersList.add(abstractPlayer);
    }

    @Override
    public AbstractPlayer getByHashcode(int hash) {
        for(AbstractPlayer player : playersList){
            if(player.hashCode() == hash){
                return player;
            }
        }
        return null;
    }

    @Override
    public List<AbstractPlayer> getAll(String string) {
        return playersList.stream().filter(player -> player.getName().startsWith(string)).collect(Collectors.toList());
    }

    @Override
    public List<AbstractPlayer> getLatest(int number) {
        return playersList.stream().sorted().limit(number).collect(Collectors.toList());
    }

    @Override
    public void update(int hash, AbstractPlayer object) {
        Optional<AbstractPlayer> abstractPlayer = playersList.stream().filter(x->x.hashCode() == hash).findFirst();
        if(abstractPlayer.isPresent()){
            playersList.remove(abstractPlayer.get());
            playersList.add(object);
            System.out.println("Update successfully made.");
        }
        System.out.println("Could not efectuate update.");
    }

    @Override
    public void delete(int hash) {
        Optional<AbstractPlayer> abstractPlayer = playersList.stream().filter(x->x.hashCode() == hash).findFirst();
        abstractPlayer.ifPresent(player -> playersList.remove(player));
    }


}
