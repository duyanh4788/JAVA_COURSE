package com.anhvu.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhvu.crm.entity.AuthToken;
import com.anhvu.crm.interfaces.AuthDAO;
import com.anhvu.crm.interfaces.AuthService;

@Service
public class AuthServiceImpt implements AuthService {

    private AuthDAO authDAO;

    @Autowired
    public AuthServiceImpt(AuthDAO theAuthDAO) {
        authDAO = theAuthDAO;
    }

    @Override
    public AuthToken findById(int id) {
        return authDAO.findById(id);
    }

    @Override
    public List<AuthToken> findByUserId(int id) {
        return authDAO.findByUserId(id);
    }

    @Override
    public void save(AuthToken theToken) {
        authDAO.save(theToken);
    }

    @Override
    public void delete(int id) {
        authDAO.delete(id);
    }

}
