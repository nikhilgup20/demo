package com.example.demo.service;

import com.example.demo.model.KafkaResponse;

public interface DemoService {
	
	
	public KafkaResponse posttoKafka(Object kafkaRequest);
}
