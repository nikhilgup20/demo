package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.CreateUserResponse;
import com.example.demo.model.KafkaResponse;
import com.example.demo.model.UserEntity;
import com.example.demo.service.DemoService;

@RestController
@RequestMapping(value = "/demoapp")
public class DemoController{
	
	@Autowired
	UserEntity entity;
	
	@Autowired
	DemoService demoService;
	
	//@GetMapping("/getDemo")
	@RequestMapping(method = RequestMethod.GET,path = "/getDemo")
	public String getDemo(){
		System.out.println("Inside Demo method::");
		return "Demo application";
	}
	
	
	 @GetMapping("/data") 
     public UserEntity getEntity(){
	   System.out.println("Inside data method::");
       return entity;
   }
	 @PostMapping("/kafkaPost")
	 public KafkaResponse kafkaPost(@RequestBody Object kafkaRequest) {
		 KafkaResponse reponse = demoService.posttoKafka(kafkaRequest);
		 return reponse;
		 
	 }
	 
	 @GetMapping("/getAllUsers") 
     public List<User> getAllUsers(){
	   List<User> userList = demoService.getAllUsers();
	   return userList;
   }
	 
	 //http://localhost:9090/demoapp/getUserById/101
	 @GetMapping("/getUserById/{userId}") 
     public Optional<User>  getUserById(@PathVariable Long userId){
	   Optional<User> user = demoService.getUserById(userId);
	   return user;
	 }
	 
	 //http://localhost:9090/demoapp/getUserById1?userId=101
	 @GetMapping("/getUserById1") 
     public Optional<User>  getUserById1(@RequestParam(name = "userId")  Long userId){
	   Optional<User> user = demoService.getUserById(userId);
	   return user;
	 }
	 
	 
	 @PutMapping("/updateUserById") 
     public CreateUserResponse  updateUserById(@RequestParam(name = "userId")  Long userId,
    		 								@RequestBody CreateUserRequest createUserRequest){
	   int responseCode =  demoService.updateUserById(userId,createUserRequest);
	   CreateUserResponse createUserResponse = new CreateUserResponse();
	   if(responseCode == 0) {
			 createUserResponse.setCode(0);
			 createUserResponse.setMessage("User updated Successfully");
			 return createUserResponse; 
		 }else if(responseCode == 2){
			 createUserResponse.setCode(2);
			 createUserResponse.setMessage("User not found");
			 return createUserResponse;	 
		 }else {
			 createUserResponse.setCode(1);
			 createUserResponse.setMessage("User updation Failed");
			 return createUserResponse; 
		 }
	 }
	 
	 
	 @PostMapping("/createUser")
	 public CreateUserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
		 int responseCode = demoService.createUser(createUserRequest); 
		 CreateUserResponse createUserResponse = new CreateUserResponse();
		 
		 if(responseCode == 0) {
			 createUserResponse.setCode(0);
			 createUserResponse.setMessage("User created Successfully");
			 return createUserResponse; 
		 }else {
			 createUserResponse.setCode(1);
			 createUserResponse.setMessage("User creation Failed");
			 return createUserResponse; 
		 }
		 
	 }
	
	

}
