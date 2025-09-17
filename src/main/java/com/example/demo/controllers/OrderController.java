package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderRequest;
import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/place")
	public Order placeOrder(@RequestBody OrderRequest request) {
		
		return orderService.placeOrder(request.getUserId(), request.getProducts(), request.getLocation());
	}

	@DeleteMapping("/cancel/{id}")
	public String cancelOrder(@PathVariable Long id) {
		orderService.cancelOrder(id);
				return "Order cancel Successfully";
	}
	
	
}
