package com.saurabh.api.response;

import com.saurabh.api.models.Department;
import com.saurabh.api.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    /**
     * return employee for sure or return 404
     */
    private final Employee employee;
    private Department department;

    public EmployeeResponse(Employee employee) {
        this.employee = employee;
    }
}
