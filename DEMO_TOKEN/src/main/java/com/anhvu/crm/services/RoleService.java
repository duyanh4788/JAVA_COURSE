package com.anhvu.crm.services;

import org.springframework.stereotype.Service;

import com.anhvu.crm.config.PreAuthorize;

@Service
public class RoleService {

    @PreAuthorize("hasRole('1')")
    public String userOnlyMethod() {
        return "This is an user only method.";
    }

    @PreAuthorize("hasRole('2')")
    public String adminOnlyMethod() {
        return "This is an admin only method.";
    }
}
