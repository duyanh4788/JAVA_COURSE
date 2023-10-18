package com.anhvu.crm.interfaces;

import java.util.List;

import com.anhvu.crm.entity.AuthToken;

public interface AuthService {
    AuthToken findById(int id);

    AuthToken findByKeyToken(String keyToken);

    List<AuthToken> findByUserId(int userId);

    void save(AuthToken theToken);

    void delete(int id);

    void deleteByUserId(int userId);

}
