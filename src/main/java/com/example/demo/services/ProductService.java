package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Product;

public interface ProductService {
	 
	Product addproduct(Product product);
	Product getProductById(Long id);
	List<Product> GetAllProduct();
	Product updateProduct(Long id,Product product);
	String deleteProduct(Long id);

}
