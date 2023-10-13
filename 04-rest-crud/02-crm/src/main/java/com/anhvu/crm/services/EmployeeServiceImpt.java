package com.anhvu.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhvu.crm.entity.Employee;
import com.anhvu.crm.interfaces.EmployeeDAO;
import com.anhvu.crm.interfaces.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpt implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpt(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Integer save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    @Override
    @Transactional
    public void delete(int theId) {
        employeeDAO.delete(theId);
    }
}
