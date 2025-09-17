package com.example.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.eq;

import com.example.demo.controllers.ProductController;
import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProduct() throws Exception {
        // Arrange (expected product)
        Product product = new Product(1L, "Laptop", 50000,10);
        Product savedProduct = new Product(1L, "Laptop", 50000.0, 10);

        // Mocking service
        when(productService.addproduct(any(Product.class))).thenReturn(savedProduct);

        // Act + Assert
        mockMvc.perform(post("/product/add")
        		 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(product)))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.id").value(1L))
         .andExpect(jsonPath("$.name").value("Laptop"));
    }
    
    // ------------------ GET ALL PRODUCTS -------------------
    @Test
    void testGetAllProducts() throws Exception {
        Product p1 = new Product(1L, "Laptop", 50000.0, 10);
        Product p2 = new Product(2L, "Phone", 30000.0, 20);

        when(productService.GetAllProduct()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
     // ----------- get by Id---------//
    @Test
    void testGetProductById() throws Exception {
        Product product = new Product(1L, "Laptop", 50000.0, 10);

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }
    
    // ------------------ UPDATE PRODUCT -------------------
    @Test
    void testUpdateProduct() throws Exception {
        Product product = new Product(1L, "Laptop", 55000.0, 12);

        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(product);
        
        mockMvc.perform(put("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(55000.0))
                .andExpect(jsonPath("$.stock").value(12));
    }
    
    //------------------Delete Product-----------//
    @Test
    void testDeleteProduct() throws Exception {
        // Mock service call
        when(productService.deleteProduct(1L)).thenReturn("product Deleted Successfully");

        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk()) // ✅ 200 OK
                .andExpect(content().string("product Deleted Successfully")); // ✅ check body
    }


    
    
}

	 
	
