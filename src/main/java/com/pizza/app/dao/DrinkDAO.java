package com.pizza.app.dao;

import com.pizza.app.entity.Drink;

import java.util.List;

public interface DrinkDAO{
    List<Drink> get();
    Drink get(int id);
    void add(Drink drink);
    void delete(int id);
}
