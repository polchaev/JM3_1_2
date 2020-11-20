package com.polchaev.springsecurity.dao;


import com.polchaev.springsecurity.model.Role;
import com.polchaev.springsecurity.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getById(Long id);

    void add(User user);

    void delete(User user);

    void deleteById(Long id);

    void update(User user);

    User getUserByName(String name);

    Role getRoleByName(String name);


}
