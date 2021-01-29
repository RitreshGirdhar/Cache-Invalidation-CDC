package com.cdc.demo.invoiceservice.listeners;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class CustomerListener {

	private final CountDownLatch latch1 = new CountDownLatch(1);

	@KafkaListener(id = "myListener", topics = "myTopic",
			autoStartup = "${listen.auto.start:true}", concurrency = "${listen.concurrency:3}")
	public void listen1(String foo) {
		this.latch1.countDown();
	}
}
