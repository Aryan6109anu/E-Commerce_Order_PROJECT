package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	  @Id 
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;
	    private double totalAmount;
	    private double shippingCharge;
	    private boolean shipped;
		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Order(Long id, User user, double totalAmount, double shippingCharge, boolean shipped) {
			super();
			this.id = id;
			this.user = user;
			this.totalAmount = totalAmount;
			this.shippingCharge = shippingCharge;
			this.shipped = shipped;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public double getShippingCharge() {
			return shippingCharge;
		}
		public void setShippingCharge(double shippingCharge) {
			this.shippingCharge = shippingCharge;
		}
		public boolean isShipped() {
			return shipped;
		}
		public void setShipped(boolean shipped) {
			this.shipped = shipped;
		}
	    
	    
	    

}
