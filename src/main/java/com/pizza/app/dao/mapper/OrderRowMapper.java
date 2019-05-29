package com.pizza.app.dao.mapper;

import com.pizza.app.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setIdClient(resultSet.getString("idClient"));
        order.setIdPizza(resultSet.getInt("idPizza"));
        return order;
    }
}