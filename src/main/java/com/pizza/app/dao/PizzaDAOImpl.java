package com.pizza.app.dao;

import com.pizza.app.entity.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PizzaDAOImpl implements PizzaDAO {



    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Pizza> get() {
        String sql = "SELECT * FROM pizza";

        List<Pizza> pizzas = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Pizza employee = new Pizza();
            employee.setIdPizza(Integer.parseInt(String.valueOf(row.get("idPizza"))));
            employee.setInfo((String)row.get("info"));
            employee.setPrice(Integer.parseInt(String.valueOf(row.get("price"))));
            employee.setSize(Integer.parseInt(String.valueOf(row.get("size"))));
            pizzas.add(employee);
        }

        return pizzas;
    }

    @Override
    public Pizza get(int id) {
        return null;
    }

    @Override
    public void add(Pizza pizza) {

    }

    @Override
    public void delete(int id) {

    }
}
