package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
	
	private String ssn;
	private int age;
	private String name;

}
