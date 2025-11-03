package com.example.demo.utility;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	/*
	 * @KafkaListener(topics = "demotopic",groupId = "test-consumer-group") public
	 * void readMessage(@Payload String message) {
	 * System.out.println("Incoming Message::"+message);
	 * 
	 * }
	 */
}
