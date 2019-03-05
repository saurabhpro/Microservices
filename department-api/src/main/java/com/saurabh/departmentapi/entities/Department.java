package com.saurabh.departmentapi.entities;

import javax.persistence.*;

@Entity
// @Table(name = "departments") by default takes the class name in lower case
public class Department {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String deptName; // in database dept_name should be the column name by default

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
