package com.example.demo.service.impl;

import com.example.demo.entity.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repo;

    public WarehouseServiceImpl(WarehouseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return repo.save(warehouse);
    }

    @Override
    public List<Warehouse> getAll() {
        return repo.findAll();
    }

    @Override
    public Warehouse getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }
}

