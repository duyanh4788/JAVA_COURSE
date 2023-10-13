package com.anhvu.crm.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anhvu.crm.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
