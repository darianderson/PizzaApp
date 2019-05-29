package com.pizza.app.dao;

import com.pizza.app.entity.User;

public interface UserDAO extends DAO<User> {
    User get(String username);
    void delete(String username);
}
