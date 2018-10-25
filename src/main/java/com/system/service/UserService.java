package com.system.service;

import com.system.entity.User;
import com.system.dao.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepositoty userRepositoty;

    public User findUserByName(String name) {
        User user = null;
        try {
            user = userRepositoty.findByUserName(name);
        } catch (Exception e) {
        }
        return user;
    }
}
