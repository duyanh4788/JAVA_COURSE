package com.anhvu.crm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anhvu.crm.entity.User;
import com.anhvu.crm.interfaces.UserDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDAOImpt implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpt(EntityManager thentityManager) {
        entityManager = thentityManager;
    }

    @Override
    public Integer save(User theUser) {
        User user = entityManager.merge(theUser);
        return user.getId();
    }

    @Override
    public User findById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> query = entityManager.createQuery("FROM User where userName=:theData", User.class);
        query.setParameter("theData", userName);
        User user = query.getSingleResult();
        return user;
    }

}
