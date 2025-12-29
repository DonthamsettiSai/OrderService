package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/order")
public class OrderController {
	
//	@Autowired
//	RestTemplate rest;
	
	@GetMapping("/getOrder")
    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackGetOrder")
    public String getOrder() {
        String url = "http://localhost:9090/pay/getPayment";
//        ResponseEntity<String> forEntity = rest.getForEntity(url, String.class);
        return "Order is placed successfully ";
    }

    // Fallback method: Must match signature (return type, params) + add Throwable
    public String fallbackGetOrder(Throwable t) {
        // Optional: Log the error, e.g., log.error("Payment service unavailable", t);
        return "Order placed, but payment deferred (service down: " + t.getMessage() + "). Please retry later.";
    }
}
