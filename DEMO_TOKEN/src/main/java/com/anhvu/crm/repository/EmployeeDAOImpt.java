package com.anhvu.crm.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anhvu.crm.entity.Employee;
import com.anhvu.crm.interfaces.EmployeeDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOImpt implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpt(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;

    }

    @Override
    public Integer save(Employee theEmployee) {
        Employee employee = entityManager.merge(theEmployee);
        return employee.getId();
    }

    @Override
    public void delete(int id) {
        Employee employee = entityManager.find(Employee.class, id);

        entityManager.remove(employee);
    }
}
