package com.pizza.app.dao.impl;

import com.pizza.app.dao.DrinkDAO;
import com.pizza.app.dao.mapper.DrinkRowMapper;
import com.pizza.app.entity.Drink;
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
public class DrinkDAOImpl implements DrinkDAO {

    private static final String SQL_ADD = "insert into drink(id,price,name) values(?,?,?)";
    private static final String SQL_UPDATE = "update drink set price=?,name=? where id=?";
    private static final String SQL_GET_LIST = "select * from drink";
    private static final String SQL_DELETE = "delete from drink where id = ?";
    private static final String SQL_GET_DRINK = "select * from drink where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Drink> get() {
        return jdbcTemplate.query(SQL_GET_LIST, new DrinkRowMapper());
    }

    @Override
    public Drink get(int id) {
        return jdbcTemplate.queryForObject(
                SQL_GET_DRINK,
                new Object[]{id}, new DrinkRowMapper());
    }

    @Override
    public void add(Drink drink) {
        if (drink.getId() != 0 && get(drink.getId()) != null) {
            update(drink);
            return;
        }
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(generatePreparedStatementCreator(drink, SQL_ADD), holder);
    }

    private void update(Drink drink) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, drink.getPrice());
            ps.setString(2, drink.getName());
            ps.setInt(3, drink.getId());
            return ps;
        }, holder);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    private PreparedStatementCreator generatePreparedStatementCreator(final Drink drink, final String sql) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, drink.getId());
            ps.setInt(2, drink.getPrice());
            ps.setString(3, drink.getName());
            return ps;
        };
    }
}
