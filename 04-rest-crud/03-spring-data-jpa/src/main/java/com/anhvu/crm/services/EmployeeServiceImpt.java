package com.anhvu.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhvu.crm.entity.Employee;
import com.anhvu.crm.exception.ExceptionError;
import com.anhvu.crm.interfaces.EmployeeRepository;
import com.anhvu.crm.interfaces.EmployeeService;

@Service
public class EmployeeServiceImpt implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpt(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new ExceptionError("Employee not found " + id);
        }

        return employee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void delete(int theId) {
        employeeRepository.deleteById(theId);
    }
}
