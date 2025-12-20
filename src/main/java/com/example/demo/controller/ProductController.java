package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // POST /api/products
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    // GET /api/products
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    // ✅ GET /api/products/{id}
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
