package com.polchaev.springsecurity.service;

import com.polchaev.springsecurity.model.Role;
import com.polchaev.springsecurity.model.User;

import java.util.List;


public interface UserService {
    List<User> getAll();

    User getById(Long id);

    void add(User user);

    void delete(User user);

    void deleteById(Long id);

    void update(User user);

    User getUserByName(String login);

    Role getRoleByName(String login);
}
