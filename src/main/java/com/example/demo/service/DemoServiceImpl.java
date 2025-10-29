package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.KafkaResponse;
import com.example.demo.utility.KafkaProducer;

@Service
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	KafkaProducer kafkaProducer;
	
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

}
