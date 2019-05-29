package com.pizza.app.dao.impl;

import com.pizza.app.config.SecurityConfig;
import com.pizza.app.dao.UserDAO;
import com.pizza.app.dao.mapper.UserRowMapper;
import com.pizza.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final String SQL_ADD = "insert into users(username,firstName,secondName,phoneNumber,address,email) values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update users set firstName=?,secondName=?,phoneNumber=?,address=?,email=?  where username=?";
    private static final String SQL_GET_LIST = "select * from users";
    private static final String SQL_DELETE = "delete from users where username = ?";
    private static final String SQL_GET_USER = "select * from users where username=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<User> get() {
        return jdbcTemplate.query(SQL_GET_LIST, new UserRowMapper());
    }

    @Override
    public User get(int id) {
        return null;
    }

    public User get(String username) {
        return jdbcTemplate.queryForObject(
                SQL_GET_USER,
                new Object[]{username}, new UserRowMapper());
    }

    @Override
    public void add(User user) {
        if (user.getUsername() != null && get(user.getUsername()) != null) {
            update(user);
            return;
        }
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(generatePreparedStatementCreator(user, SQL_ADD), holder);
    }

    @Override
    public void delete(int id) {

    }

    public void updateUser(User user){

        // define query arguments
        Object[] params = { user.getUsername(), user.getFirstName(),user.getSecondName(),
                                    user.getPhoneNumber(),user.getAddress(),user.getEmail()};
        // define SQL types of the arguments
        int[] types = {Types.VARCHAR};

        int rows = jdbcTemplate.update(SQL_UPDATE, params, types);

        System.out.println(rows + " row(s) updated.");
    }

    public void update(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getSecondName());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getUsername());
            return ps;
        }, holder);
    }

    @Override
    public void delete(String username) {
        jdbcTemplate.update(SQL_DELETE, username);
    }

    private PreparedStatementCreator generatePreparedStatementCreator(final User user, final String sql) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getSecondName());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(6, user.getAddress());
            ps.setString(6, user.getEmail());
            return ps;
        };
    }
}
