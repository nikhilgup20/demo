package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "TEST_USERS")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonView(Views.External.class)
	@Column(name = "USER_NAME", length = 50,nullable = false,unique = true)
	private String userName;
	
	@JsonView(Views.External.class)
	@Column(name = "AGE", length = 5,nullable = false)
	private int age;
	
	@JsonView(Views.Internal.class)
	@Column(name = "SSN", length = 50,nullable = false,unique = true)
	private String ssn;
	
	// TODO Auto-generated constructor stub
	public User() {
		
	}

	public User(Long id, String userName, int age, String sSn) {
		super();
		this.id = id;
		this.userName = userName;
		this.age = age;
		ssn = sSn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", age=" + age + ", SSN=" + ssn + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSSN() {
		return ssn;
	}

	public void setSSN(String sSN) {
		ssn = sSN;
	}
	
	
	
	
	
}
