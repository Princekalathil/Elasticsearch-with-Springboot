package com.bytatech.jest.example.jestDemo.web;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.HashSet;
import java.util.*;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bytatech.jest.example.jestDemo.domain.*;
import com.bytatech.jest.example.jestDemo.repository.*;
import com.bytatech.jest.example.jestDemo.repository.search.*;
import com.bytatech.jest.example.jestDemo.service.EmployeeService;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
//import com.bytatech.jest.example.jestDemo.search.custom.ArtistSearchRepositoryCustomImpl;
import com.github.vanroy.springdata.jest.aggregation.AggregatedPage;

import io.searchbox.client.JestClient;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

	private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

	@Autowired
	EmployeeService empService;

	private final JestClient jestClient;
	private final JestElasticsearchTemplate esTemplate;

	public EmployeeResource(JestClient jestClient, JestElasticsearchTemplate elasticsearchTemplate) {
		this.esTemplate = elasticsearchTemplate;
		this.jestClient = jestClient;
	}

	@PostMapping("/create")
	public Employee createEmployee(@RequestBody Employee emp) {
		System.out.println("working++++++++++" + emp);
		return empService.save(emp);
	}

	@GetMapping("/getall")
	public List<Employee> getAll(Pageable pageable) {
		return empService.getAll(pageable);

	}

	@GetMapping("/getone")
	public Employee getOne(@RequestParam(value = "first") String firstName) {

		return empService.getOne(firstName);

	}

	@GetMapping("/getsearchFname/{fName}")
	public Page<Employee> searchFirstName(@PathVariable String fName, Pageable pageable) {
		log.debug(" <<<<<<<<<< getSearchFirstName>>>>>>>>> ", fName);
		SearchQuery sq = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("firstName", fName)).build();
		return esTemplate.queryForPage(sq, Employee.class);

	}

	@GetMapping("/getsearchLname")
	public Page<Employee> searchLastName(@RequestParam String lastName, Pageable pageable) {
		log.debug(" <<<<<<<<<< getSearchFirstName>>>>>>>>> ", lastName);
		SearchQuery sq = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("lastName", lastName))
				.build();
		return esTemplate.queryForPage(sq, Employee.class);

	}

	@GetMapping("/getsearchAbout/{about}")
	public Page<Employee> searchAbout(@PathVariable String about, Pageable apgeable) {
		log.debug("<<<<<<<<<<<<< getsearchAbout >>>>>>>>", about);
		SearchQuery sq = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("about", about).fuzziness(Fuzziness.TWO)).build();
		return esTemplate.queryForPage(sq, Employee.class);

	}

}
