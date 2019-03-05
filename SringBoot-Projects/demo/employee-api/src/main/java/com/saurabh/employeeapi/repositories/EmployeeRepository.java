package com.saurabh.employeeapi.repositories;

import com.saurabh.employeeapi.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
