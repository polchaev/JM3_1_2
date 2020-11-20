package com.polchaev.springsecurity.security;

import com.polchaev.springsecurity.dao.UserDao;
import com.polchaev.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Qualifier("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.getUserByName(login);
        return user;
    }
}
