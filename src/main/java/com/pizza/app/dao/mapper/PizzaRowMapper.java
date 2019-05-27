package com.pizza.app.dao.mapper;

import com.pizza.app.entity.Pizza;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzaRowMapper implements RowMapper<Pizza> {
    @Override
    public Pizza mapRow(ResultSet resultSet, int i) throws SQLException {
        Pizza pizza = new Pizza();
        pizza.setId(resultSet.getInt("id"));
        pizza.setInfo(resultSet.getString("info"));
        pizza.setSize(resultSet.getInt("size"));
        pizza.setPrice(resultSet.getInt("price"));

        return pizza;
    }
}
