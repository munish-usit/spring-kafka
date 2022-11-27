package com.learning.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learning.kafka.model.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumerService {

	
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeMessage(OrderEvent orderEvent) {
		log.info("message consumed message {}",orderEvent);
	}
}
