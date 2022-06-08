package com.macrosoft.service;

import java.util.List;

import com.macrosoft.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(long id);
	
}
