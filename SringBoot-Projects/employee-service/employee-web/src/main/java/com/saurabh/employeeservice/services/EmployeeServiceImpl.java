package com.saurabh.employeeservice.services;

import com.saurabh.api.models.Department;
import com.saurabh.api.models.Employee;
import com.saurabh.api.response.EmployeeResponse;
import com.saurabh.employeeservice.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Employee> getEmployeeById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<EmployeeResponse> getEmployeeWithDepartment(UUID id) {
        Optional<Employee> employee = getEmployeeById(id);
        return employee.map(e -> {
            Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + e.getDepartmentId(),
                    Department.class);

            final EmployeeResponse employeeResponse = new EmployeeResponse(e);
            employeeResponse.setDepartment(department);

            return Optional.of(employeeResponse);
        }).orElse(Optional.empty());
    }
}
