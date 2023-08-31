package com.learning.kafka.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
		Message<OrderEvent> message = MessageBuilder.withPayload(orderEvent)
				.setHeader(KafkaHeaders.MESSAGE_KEY, orderEvent.getClient())
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		ListenableFuture<SendResult<String, OrderEvent>> callback = kafkaTemplate.send(message);
		
		callback.addCallback(new ListenableFutureCallback<SendResult<String, OrderEvent>>() {
		      @Override
		      public void onSuccess(SendResult<String, OrderEvent> result) {
		        log.info("order id {} ,partition {}, offset {}",orderEvent.getClient(),result.getRecordMetadata().partition(),result.getRecordMetadata().offset());
		      }
		  
		      @Override
		      public void onFailure(Throwable ex) {
		        log.error("not able to deliver message {} ",ex.getLocalizedMessage());
		      }
		    });
		
		log.info("message sent topic name  {}, message {}",topic.name(),message);
	}
}
