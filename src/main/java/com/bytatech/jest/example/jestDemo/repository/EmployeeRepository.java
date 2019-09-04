package com.bytatech.jest.example.jestDemo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.bytatech.jest.example.jestDemo.domain.Employee;




@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByFirstName(String firstName);

		
}
