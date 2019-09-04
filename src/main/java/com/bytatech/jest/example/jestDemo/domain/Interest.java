package com.bytatech.jest.example.jestDemo.domain;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;

import org.springframework.data.elasticsearch.annotations.Document;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@Entity
@Document(indexName = "interest" )
public class Interest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
private String name;
@ManyToOne
@JsonIgnoreProperties("interests")
private Employee employee;


public Interest(String name, Employee emp) {
	this.name = name;
	this.employee = emp; 
}



}
