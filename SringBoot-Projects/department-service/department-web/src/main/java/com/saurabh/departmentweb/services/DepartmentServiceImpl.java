package com.saurabh.departmentweb.services;

import com.department.api.models.Department;
import com.saurabh.departmentweb.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<Department> getDepartmentId(UUID id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);

    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
