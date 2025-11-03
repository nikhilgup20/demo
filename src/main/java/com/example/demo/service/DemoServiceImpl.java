package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.KafkaResponse;
import com.example.demo.repositories.UserManagementRespository;
import com.example.demo.utility.KafkaProducer;

@Service
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	KafkaProducer kafkaProducer;
	
	@Autowired
	UserManagementRespository userManagementRespository;
	
	@Override
	public KafkaResponse posttoKafka(Object kafkaRequest) {
		
		KafkaResponse response = new KafkaResponse();
		int responseCode = kafkaProducer.sendMessage("demotopic", kafkaRequest);
		
		if(responseCode == 0) {
			response.setCode(0);
			response.setMessage("Success");
			
		}else {
			response.setCode(1);
			response.setMessage("Failure");
		}
		
		return response;
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = userManagementRespository.findAll();
		return userList;
	}

	@Override
	public int createUser(CreateUserRequest createUserRequest) {
		User user = new User();
		user.setAge(createUserRequest.getAge());
		user.setSSN(createUserRequest.getSsn());
		user.setUserName(createUserRequest.getName());
		try {
			userManagementRespository.save(user);
			return 0;
		}catch(Exception e) {
			System.out.println("Exception occured in saving to db::"+e);
			return 1;
		}
	}

	@Override
	public Optional<User> getUserById(Long userId) {
		Optional<User> user = userManagementRespository.findById(userId);
		return user;
	}

	@Override
	public int updateUserById(Long userId, CreateUserRequest createUserRequest) {
		Optional <User> user = userManagementRespository.findById(userId);
		User updatedUser = new User();
		
		if(!user.isPresent()) {
			System.out.println("User not found!");
			return 2;
		}else {
			updatedUser.setId(userId);
			updatedUser.setAge(createUserRequest.getAge());
			updatedUser.setSSN(createUserRequest.getSsn());
			updatedUser.setUserName(createUserRequest.getName());
			try {
				userManagementRespository.save(updatedUser);
				return 0;
			}catch(Exception e) {
				System.out.println("Exception occured in saving to db::"+e);
				return 1;
			}
		}
	}

}
