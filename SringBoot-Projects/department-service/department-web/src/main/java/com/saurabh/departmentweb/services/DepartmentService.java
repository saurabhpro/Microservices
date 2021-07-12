package com.saurabh.departmentweb.services;

import com.department.api.models.Department;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentService {
    Optional<Department> getDepartmentId(UUID id);

    Department saveDepartment(Department department);

    List<Department> getAllDepartments();
}
