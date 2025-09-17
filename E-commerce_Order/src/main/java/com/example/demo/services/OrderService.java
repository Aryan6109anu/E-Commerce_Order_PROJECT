package com.example.demo.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.exception.OutOfStockException;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional  // ensure atomic operation
    public Order placeOrder(Long userId, Map<Long, Integer> productIdQty, String location) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found with id: " + userId));

        double total = 0;

        // Check stock for all products first
        for (Long productId : productIdQty.keySet()) {
            Product p = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product Not Found with id: " + productId));

            int requestedQty = productIdQty.get(productId);

            System.out.println("Product: " + p.getName() + ", Requested: " + requestedQty + ", Available: " + p.getStock());

            if (requestedQty > p.getStock()) {
                throw new OutOfStockException("Item Out of STOCK: " + p.getName());
            }
        }

        // Reduce stock and calculate total
        for (Long productId : productIdQty.keySet()) {
            Product p = productRepository.findById(productId).get();
            int qty = productIdQty.get(productId);

            total += p.getPrice() * qty;
            p.setStock(p.getStock() - qty); // reduce stock
            productRepository.save(p);
        }

        // Apply discount
        if (total > 1000) total = total * 0.9;

        // Shipping charges
        double shipping = location.equalsIgnoreCase("Local") ? 50 : 100;

        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setShippingCharge(shipping);
        order.setTotalAmount(total);
        order.setShipped(true);

        return orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        if (order.isShipped())
            throw new RuntimeException("Cannot cancel Order already Shipped");

        orderRepository.delete(order);
    }
}
