package com.pizza.app.dao.impl;

import com.pizza.app.dao.PizzaDAO;
import com.pizza.app.dao.mapper.PizzaRowMapper;
import com.pizza.app.entity.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PizzaDAOImpl implements PizzaDAO {

    private static final String SQL_ADD = "insert into pizza(id,info,size,price) values(?,?,?,?)";
    private static final String SQL_UPDATE = "update pizza set info=?,size=?,price=? where id=?";
    private static final String SQL_GET_LIST = "select * from pizza";
    private static final String SQL_DELETE = "delete from pizza where id = ?";
    private static final String SQL_GET_PIZZA = "select * from pizza where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pizza> get() {
        return jdbcTemplate.query(SQL_GET_LIST, new PizzaRowMapper());
    }

    @Override
    public Pizza get(int id) {
        return jdbcTemplate.queryForObject(
                SQL_GET_PIZZA,
                new Object[]{id}, new PizzaRowMapper());
    }

    @Override
    public void add(Pizza pizza) {
        if(pizza.getId() !=0 && get(pizza.getId()) != null) {
            update(pizza);
            return;
        }
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(generatePreparedStatementCreator(pizza, SQL_ADD), holder);
    }

    private void update(Pizza pizza) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pizza.getInfo());
            ps.setInt(2, pizza.getSize());
            ps.setInt(3, pizza.getPrice());
            ps.setInt(4, pizza.getId());
            return ps;
        }, holder);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    private PreparedStatementCreator generatePreparedStatementCreator(final Pizza pizza, final String sql) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pizza.getId());
            ps.setString(2, pizza.getInfo());
            ps.setInt(3, pizza.getSize());
            ps.setInt(4, pizza.getPrice());
            return ps;
        };
    }

}


