package com.pizza.app.dao;

import com.pizza.app.entity.Drink;

import java.util.List;

public interface DAO<T> {
    List<T> get();
    T get(int id);
    void add(T T);
    void delete(int id);
}
