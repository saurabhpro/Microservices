package com.saurabh.departmentweb.controllers;

import com.department.api.models.Department;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentController {

    ResponseEntity<Department> getDepartment(String id);

    ResponseEntity<List<Department>> getAllDepartments();

    ResponseEntity<Department> saveDepartment(Department department);
}
