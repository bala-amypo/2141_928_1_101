package com.example.demo.service;

import com.example.demo.entity.Product;
import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> getAll();

    Product getById(Long id);
}
