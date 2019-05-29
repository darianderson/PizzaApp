package com.pizza.app.dao.mapper;

import com.pizza.app.entity.Order;
import com.pizza.app.entity.Pizza;
import com.pizza.app.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setStatus(resultSet.getInt("status"));

        String username = resultSet.getString("idClient");
        User user = new User();
        user.setUsername(username);
        order.setUser(user);

        Pizza pizza = new Pizza();
        pizza.setId(resultSet.getInt("idPizza"));
        pizza.setInfo(resultSet.getString("info"));
        pizza.setSize(resultSet.getInt("size"));
        pizza.setPrice(resultSet.getInt("price"));
        order.setPizza(pizza);

        return order;
    }
}