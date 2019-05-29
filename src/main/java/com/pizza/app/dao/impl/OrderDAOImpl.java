package com.pizza.app.dao.impl;

import com.pizza.app.dao.OrderDAO;
import com.pizza.app.dao.mapper.OrderRowMapper;
import com.pizza.app.entity.Order;
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
public class OrderDAOImpl implements OrderDAO {

    private static final String SQL_ADD = "insert into orders(id,idPizza,idClient) values(?,?,?)";
    private static final String SQL_UPDATE = "update orders set status=?, idPizza=?,idClient=? where id=?";
    private static final String SQL_GET_LIST = "SELECT orders.*, pizza.* FROM orders, pizza WHERE pizza.id=orders.idPizza";
    private static final String SQL_DELETE = "delete from orders where id = ?";
    private static final String SQL_GET_ORDER = "SELECT orders.*, pizza.* FROM orders, pizza WHERE pizza.id=orders.idPizza and orders.id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> get() {
        return jdbcTemplate.query(SQL_GET_LIST, new OrderRowMapper());
    }

    @Override
    public Order get(int id) {
        return jdbcTemplate.queryForObject(
                SQL_GET_ORDER,
                new Object[]{id}, new OrderRowMapper());
    }

    @Override
    public void add(Order order) {
        if(order.getId() !=0 && get(order.getId()) != null) {
            update(order);
            return;
        }
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(generatePreparedStatementCreator(order, SQL_ADD), holder);
    }

    private void update(Order order) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getStatus());
            ps.setInt(2, order.getPizza().getId());
            ps.setString(3, order.getUser().getUsername());
            ps.setInt(4, order.getId());
            return ps;
        }, holder);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    private PreparedStatementCreator generatePreparedStatementCreator(final Order order, final String sql) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getId());
            ps.setString(2, order.getUser().getUsername());
            ps.setInt(3, order.getPizza().getId());
            return ps;
        };
    }

}
