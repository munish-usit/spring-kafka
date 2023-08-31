package com.learning.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class OrderEvent {

	private String message;
	private String status;
	private String client;
	private String id;
	private int quantity;
	private double amount;
}
