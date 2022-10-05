package com.playtika.java.academy.challenge3.badea.andreea.models.interfaces;

import java.util.List;

public interface Repository<T> {
    void add(T object);
    T getByHashcode(int hash);
    List<T> getAll(String string);
    List<T> getLatest(int number);
    void update(int hash, T object);
    void delete(int hash);

}
