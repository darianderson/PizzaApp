package com.pizza.app.dao.mapper;


import com.pizza.app.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setSecondName(resultSet.getString("secondName"));
        user.setPhoneNumber(resultSet.getString("phoneNumber"));
        user.setAddress(resultSet.getString("address"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}