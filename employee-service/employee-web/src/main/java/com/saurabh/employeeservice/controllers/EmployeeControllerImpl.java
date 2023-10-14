package com.saurabh.employeeservice.controllers;

import com.saurabh.api.models.Employee;
import com.saurabh.api.response.EmployeeResponse;
import com.saurabh.api.utils.CommonUtils;
import com.saurabh.employeeservice.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeControllerImpl implements EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

        log.info("save starting {}...", employee);
        final var saved = employeeService.saveEmployee(employee);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(CommonUtils.getStringIdFromUUID(saved.getId()))
                .toUri();

        return ResponseEntity
                .created(location)
                .body(saved);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeWithResponse(@PathVariable String id) {

        log.info("get employee with department {}...", id);
        final Optional<EmployeeResponse> response = employeeService.getEmployeeWithDepartment(UUID.fromString(id));

        return response.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
