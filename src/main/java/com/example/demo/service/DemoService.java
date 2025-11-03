package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.KafkaResponse;

public interface DemoService {
	
	
	public KafkaResponse posttoKafka(Object kafkaRequest);
	
	public List<User> getAllUsers();
	
	public int createUser(CreateUserRequest createUserRequest);
}
