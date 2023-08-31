package com.learning.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learning.kafka.model.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumerService {

	/**
	 * Each listener is equivalent to consumer.
	 * Try to comment or add new listener to check partition rebalancing strategy
	 * In prod we should use CooperativeStickyAssignor
	 * partition.assignment.strategy = [class org.apache.kafka.clients.consumer.RangeAssignor, class org.apache.kafka.clients.consumer.CooperativeStickyAssignor]
	 */
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeMessage1(ConsumerRecord<String, OrderEvent> record) {
		log.info("consumer 1 key {}, partition {}, offset {}",record.key(),record.partition(),record.offset());
		log.info("consumer 1 key {}, payload {}",record.key(),record.value());
	}
	
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeMessage2(ConsumerRecord<String, OrderEvent> record) {
		log.info("consumer 2 key {}, partition {}, offset {}",record.key(),record.partition(),record.offset());
		log.info("consumer 2 key {}, payload {}",record.key(),record.value());
	}
	
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeMessage3(ConsumerRecord<String, OrderEvent> record) {
		log.info("consumer 3 key {}, partition {}, offset {}",record.key(),record.partition(),record.offset());
		log.info("consumer 3 key {}, payload {}",record.key(),record.value());
	}
}
