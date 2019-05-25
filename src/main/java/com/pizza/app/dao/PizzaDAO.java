package com.pizza.app.dao;

import com.pizza.app.entity.Pizza;

import java.util.List;

public interface PizzaDAO {
    List<Pizza> get();
    Pizza get(int id);
    void add(Pizza pizza);
    void delete(int id);
}
