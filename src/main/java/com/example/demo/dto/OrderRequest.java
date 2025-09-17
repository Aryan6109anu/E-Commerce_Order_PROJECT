package com.example.demo.dto;

import java.util.Map;


public class OrderRequest {

    private Long userId; // Who is placing the order
    private Map<Long, Integer> products; // Key=ProductId, Value=Quantity
    private String location; // Shipping location	
    // Getters & Setters
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Map<Long, Integer> getProducts() {
		return products;
	}
	public void setProducts(Map<Long, Integer> products) {
		this.products = products;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
    
}