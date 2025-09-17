package com.example.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

import com.example.demo.services.ProductServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductServiceTest {
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@BeforeEach
	   void setup() {
		  MockitoAnnotations.openMocks(this);
		  
		  
	}
	  @Test
	   void testAddProduct() {
		  Product product = new Product();
		  product.setName("Laptop");
		  product.setPrice(20000);
		  product.setStock(10);
		  
		 when(productRepository.save(product)).thenReturn(product);
		 
		 Product savedProduct = productService.addproduct(product);
		 
		 assertNotNull(savedProduct);
		 assertEquals("Laptop", savedProduct.getName());
		 assertEquals(20000, savedProduct.getPrice());
		 assertEquals(10, savedProduct.getStock());
		 verify(productRepository,times(1)).save(product);
	  }
	  
	   @Test
	    void  getProduct() {
		   Product product = new Product();
		   product.setId(3L);
		   product.setName("I-Phone");
		   product.setPrice(100000);
		   product.setStock(2);
		   
		   when(productRepository.findById(3L)).thenReturn(Optional.of(product));
		   
		   Product foundProduct = productService.getProductById(3L);
		   
		   assertNotNull(foundProduct);
		   assertEquals("I-Phone", foundProduct.getName());
		   assertEquals(100000, foundProduct.getPrice());
		   assertEquals(2, foundProduct.getStock());
		   verify(productRepository,times(1)).findById(3L);
		   
		   
	   }
	    @Test
		    void getAllProduct() {
	    	List<Product> productList = Arrays.asList(
	    			new Product(1L, "Camera", 100000, 25),
	    			new Product(2L, "watch", 12000, 5)
	    			);
	    	
	    	when(productRepository.findAll()).thenReturn(productList);
	    	
	    	List<Product> getAllProduct = productService.GetAllProduct();
	    	
	    	assertEquals(2, getAllProduct.size());
         	assertEquals("Camera", getAllProduct.get(0).getName());
	    	assertEquals("watch", getAllProduct.get(1).getName());
	    	verify(productRepository,times(1)).findAll();
	    
	    }
	      @Test
	      	       void updateTest() {
	    	  Product existingProduct = new Product(1L, "HeadPhone", 15000, 5);
	    	  Product updatedData = new Product(null, "Android TV", 150000, 20);
	    	  
	    	 when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
	    	 when(productRepository.save(existingProduct)).thenReturn(existingProduct);

	    	 Product updatedProduct = productService.updateProduct(1L, updatedData);
	    	 
	    	 assertEquals("Android TV", updatedProduct.getName());
	    	 assertEquals(150000, updatedProduct.getPrice());
	    	 assertEquals(20, updatedProduct.getStock());
	    	 verify(productRepository,times(1)).save(existingProduct);
	    	 	    	  
	      }
	        @Test
	        void deleteProduct() {
	        	Product product = new Product(1L, "Digital Watch", 6000, 5);
	        	
	        	when(productRepository.findById(1L)).thenReturn(Optional.of(product));
	        	
	        	
	        	productService.deleteProduct(1L);
	        	
             	verify(productRepository,times(1)).deleteById(1L);
	 
             	  // assert return message
                String result = productService.deleteProduct(1L);
                assertEquals("Item deleted Successfully", result);
	        	
                System.out.println("Service Result: " + result);
	        	
	        	
	        	
	        	
	        }
	      
	    
	    
	    
	     
	    
	    
	    
	    
	    
	    
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	  

}
