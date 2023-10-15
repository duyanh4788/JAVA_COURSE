package com.anhvu.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "role")
    private Integer role;

    public User() {
    }

    public User(int id, String userName, String passWord, Integer role) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getRole() {
        return this.role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", userName='" + getUserName() + "'" +
                ", passWord='" + getPassWord() + "'" +
                ", role='" + getRole() + "'" +
                "}";
    }
}
