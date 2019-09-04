package com.bytatech.jest.example.jestDemo.repository.search;


import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.bytatech.jest.example.jestDemo.domain.Employee;



public interface EmployeeSearchRepository extends ElasticsearchRepository<Employee, Long> {

	Employee findByFirstName(String fiName);

	
			
	


}
