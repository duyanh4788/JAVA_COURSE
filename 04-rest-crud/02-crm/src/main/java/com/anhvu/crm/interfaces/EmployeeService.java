package com.anhvu.crm.interfaces;

import java.util.List;

import com.anhvu.crm.entity.Employee;

public interface EmployeeService {
    Object findById = null;

    List<Employee> findAll();

    Employee findById(int id);

    Integer save(Employee theEmployee);

    void delete(int theId);
}
