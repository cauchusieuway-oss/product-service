package com.vanson.product_service.controller;

import com.vanson.product_service.entity.Product;
import com.vanson.product_service.service.ProductService;
import com.vanson.product_service.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getProducts(@RequestHeader("Authorization") String token) {

        String email = JwtUtil.extractEmail(token);

        return productService.getProductsByUser(email);
    }
}