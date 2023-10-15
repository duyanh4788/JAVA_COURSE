package com.anhvu.crm.interfaces;

import com.anhvu.crm.entity.User;

public interface UserService {
    User findById(int id);

    User findByUserName(String userName);

    Integer save(User theUser);
}
