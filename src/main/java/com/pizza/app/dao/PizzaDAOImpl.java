package com.pizza.app.dao;

import com.pizza.app.entity.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class PizzaDAOImpl implements PizzaDAO {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Pizza> get() {
        return jdbcTemplate.query("select * from pizza", new PizzaRowMaper());
    }

    @Override
    public Pizza get(int id) {
        return jdbcTemplate.queryForObject(
                "select * from pizza where idPizza=?",
                new Object[]{id}, new PizzaRowMaper());
    }

    @Override
    public Pizza add(Pizza pizza) {
        final String sql = "insert into pizza(idPizza,info,size,price) values(?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, pizza.getIdPizza());
                ps.setString(2, pizza.getInfo());
                ps.setInt(3, pizza.getSize());
                ps.setInt(4, pizza.getPrice());
                return ps;
            }
        }, holder);
        return pizza;
    }

    @Override
    public void delete(int id) {
        String SQL = "delete from pizza where idPizza = ?";
        jdbcTemplate.update(SQL, id);
    }
}

class PizzaRowMaper implements RowMapper<Pizza> {
    @Override
    public Pizza mapRow(ResultSet resultSet, int i) throws SQLException {
        Pizza pizza = new Pizza();
        pizza.setIdPizza(resultSet.getInt("idPizza"));
        pizza.setInfo(resultSet.getString("info"));
        pizza.setSize(resultSet.getInt("size"));
        pizza.setPrice(resultSet.getInt("price"));

        return pizza;
    }
}

