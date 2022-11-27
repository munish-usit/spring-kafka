package com.learning.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class OrderEvent {

	private String message;
	private String status;
	private String orderId;
	private String orderName;
	private int quantity;
	private double amount;
}
