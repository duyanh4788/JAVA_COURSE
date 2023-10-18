package com.anhvu.crm.interfaces;

import java.util.List;

import com.anhvu.crm.entity.AuthToken;

public interface AuthDAO {
    AuthToken findById(int id);

    List<AuthToken> findByUserId(int id);

    void save(AuthToken theToken);

    void delete(int id);

}
