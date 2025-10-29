package com.example.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class KafkaProducer {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	
	public int sendMessage(String topicName,Object message) {
		try {
			kafkaTemplate.send(topicName, message.toString()).addCallback(
	            result -> System.out.println("✅ Sent message=[" + message + 
	                                          "] to topic=[" + topicName + 
	                                          "] offset=[" + result.getRecordMetadata().offset() + "]"),
	            ex -> System.err.println("❌ Failed to send message=[" + message + 
	                                      "] to topic=[" + topicName + "] due to: " + ex.getMessage())
	        );
			return 0;
		}catch(Exception e) {
			System.out.println("Exception occurred sending to Kafka::"+e);
			return 1;
		}
		
		
	}
}
