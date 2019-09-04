package com.bytatech.jest.example.jestDemo.serviceImpl;

import java.util.List;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytatech.jest.example.jestDemo.domain.Employee;
import com.bytatech.jest.example.jestDemo.repository.EmployeeRepository;
import com.bytatech.jest.example.jestDemo.repository.search.EmployeeSearchRepository;
import com.bytatech.jest.example.jestDemo.service.EmployeeService;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;

import io.searchbox.client.JestClient;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeSearchRepository eSearchRepo;

	@Autowired
	EmployeeRepository empRepo;

	@Override
	public Employee save(Employee emp) {
		log.debug("<<<<<<<<<<<create in Esearch>>>>>>>>", emp);
		Employee em = empRepo.save(emp);
		return eSearchRepo.save(emp);
	}

	@Override
	public List<Employee> getAll(Pageable pageable) {
		log.debug("<<<<<<<get all>>>>>>", pageable);

		Page<Employee> page = empRepo.findAll(pageable);
		List<Employee> list = page.getContent();

		return eSearchRepo.findAll(pageable).getContent();
	}

	@Override
	public Employee getOne(String firstName) {
		log.debug("<<<<<<<<<<get one >>>>>>>>", firstName);
		
		Employee em =empRepo.findByFirstName(firstName);
		
		return eSearchRepo.findByFirstName(firstName);
	}
	
	
	

	

}
