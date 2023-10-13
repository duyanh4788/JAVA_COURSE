package com.anhvu.database.dao;

import java.util.List;

import com.anhvu.database.entity.Student;

public interface StudentDAO {
    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByEmail(String email);

    void update(Student theStudent);

    void delete(int theId);

    int deleteAll();

}
