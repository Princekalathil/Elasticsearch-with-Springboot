package com.bytatech.jest.example.jestDemo.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bytatech.jest.example.jestDemo.domain.Employee;


public interface EmployeeService   {

	Employee save(Employee emp);

	List<Employee> getAll(Pageable pageable);

	Employee getOne(String firstName);

	
	 
	
	
	
	
	
}
