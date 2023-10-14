package com.saurabh.employeeservice.controllers;

import com.saurabh.api.models.Employee;
import com.saurabh.api.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface EmployeeController {
    ResponseEntity<Employee> saveEmployee(Employee employee);

    ResponseEntity<EmployeeResponse> getEmployeeWithResponse(String id);
}
