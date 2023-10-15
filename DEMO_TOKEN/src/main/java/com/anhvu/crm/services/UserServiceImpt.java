package com.anhvu.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhvu.crm.entity.User;
import com.anhvu.crm.interfaces.UserDAO;
import com.anhvu.crm.interfaces.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpt implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpt(UserDAO theUserDAO) {
        userDAO = theUserDAO;
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    @Transactional
    public Integer save(User theUser) {
        return userDAO.save(theUser);
    }

}
