package com.learning.kafka.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.learning.kafka.model.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderProducerService {

	@Autowired
	private NewTopic topic;
	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	public void sendMessage(OrderEvent orderEvent) {
		Message<OrderEvent> message = MessageBuilder.withPayload(orderEvent).setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		kafkaTemplate.send(message);
		log.info("message sent topic name  {}, message {}",topic.name(),message);
	}
}
