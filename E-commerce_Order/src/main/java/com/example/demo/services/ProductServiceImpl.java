package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addproduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(Long id) {
		
		return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product Not Found With id "+id));
	}

	@Override
	public List<Product> GetAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		 Product existing = getProductById(id);
		 existing.setName(product.getName());
		 existing.setPrice(product.getPrice());
		 existing.setStock(product.getStock());
		return productRepository.save(existing);
	}

	@Override
	public String deleteProduct(Long id) {
		productRepository.deleteById(id);
		return "Item deleted Successfully";
	}

}
