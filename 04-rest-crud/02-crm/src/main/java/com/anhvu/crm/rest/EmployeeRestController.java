package com.anhvu.crm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.crm.entity.Employee;
import com.anhvu.crm.exception.ExceptionError;
import com.anhvu.crm.interfaces.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/get-list")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId) {

        if (employeeId == null || employeeId < 0) {
            throw new ExceptionError("Employee Id not available " + employeeId);
        }

        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new ExceptionError("Employee Id not available " + employeeId);
        }

        return employee;
    }

    @PostMapping("/create")
    public Integer createEmployee(@RequestBody Employee theEmployee) {
        return employeeService.save(theEmployee);
    }

    @PutMapping("/update")
    public Integer updateEmployee(@RequestBody Employee theEmployee) {
        return employeeService.save(theEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId) {
        if (employeeId == null || employeeId < 0) {
            throw new ExceptionError("Employee Id not available " + employeeId);
        }
        employeeService.delete(employeeId);

        return "Delete success: " + employeeId;
    }
}
