package com.learning.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.kafka.model.OrderEvent;
import com.learning.kafka.service.OrderProducerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/kafka")
@Slf4j
public class OrderController {

	@Autowired
	private OrderProducerService orderProducerService;
	
	@PostMapping("/order/publish")
	public ResponseEntity<String> publishOrder(@RequestBody OrderEvent orderEvent) {
		log.info("order publish request {}",orderEvent);
		orderProducerService.sendMessage(orderEvent);
		return ResponseEntity.ok("order pulished successfully");
	}
	
}
