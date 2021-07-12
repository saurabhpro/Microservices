package com.saurabh.employeeservice.services;

import com.saurabh.api.models.Employee;
import com.saurabh.api.response.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {

    Optional<Employee> getEmployeeById(UUID id);

    Employee saveEmployee(Employee employee);

    Optional<EmployeeResponse> getEmployeeWithDepartment(UUID id);
}
