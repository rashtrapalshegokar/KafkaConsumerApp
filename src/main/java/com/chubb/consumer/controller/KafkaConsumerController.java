package com.chubb.consumer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chubb.consumer.entity.Product;

@SpringBootApplication
@RestController
public class KafkaConsumerController {
	List<String> messages = new ArrayList<>();
	List<Product> productList = new ArrayList<>();


	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg() {
		return messages;
	}
	Product productFromTopic = null;

	@GetMapping("/consumeJsonMessage")
	public List<Product> consumeJsonMessage() {
		
		return productList;
	}
	@KafkaListener(groupId = "rajdb-1", topics = "rajdb", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		messages.add(data);
		return messages;
	}

	
	@KafkaListener(groupId = "rajdb-2", topics = "rajdb", containerFactory = "userKafkaListenerContainerFactory")
	public List<Product> getJsonMsgFromTopic(Product product) {
		productFromTopic = product;
		productList.add(productFromTopic);
		return productList;
	}
}
