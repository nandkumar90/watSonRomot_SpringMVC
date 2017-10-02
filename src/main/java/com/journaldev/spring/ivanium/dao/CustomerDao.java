package com.journaldev.spring.ivanium.dao;

import java.util.List;

import com.journaldev.spring.model.Employee;

public interface CustomerDao {
	public List<Employee> getAllEmployees();
	
	public void saveEmployee(Employee employee);

}
