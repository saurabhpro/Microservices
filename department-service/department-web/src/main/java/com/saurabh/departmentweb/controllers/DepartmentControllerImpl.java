package com.saurabh.departmentweb.controllers;

import com.department.api.models.Department;
import com.department.api.utils.CommonUtils;
import com.saurabh.departmentweb.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequestMapping("/departments")
@RestController
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentControllerImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Department> getDepartment(@PathVariable String id) {

        // step 1
        log.info("get department {}...", id);
        final Optional<Department> department = departmentService.getDepartmentId(UUID.fromString(id));
        return department
                .map(d -> ResponseEntity.ok(department.get()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Department>> getAllDepartments() {
        log.info("get all departments...");
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    @Override
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        log.info("save starting {}...", department);
        final var saved = departmentService.saveDepartment(department);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(CommonUtils.getStringIdFromUUID(saved.getId()))
                .toUri();

        return ResponseEntity
                .created(location)
                .body(saved);
    }
}
