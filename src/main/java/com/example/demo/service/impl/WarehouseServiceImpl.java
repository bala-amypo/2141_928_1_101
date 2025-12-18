package com.example.demo.service.impl;

import com.example.demo.entity.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repository;

    public WarehouseServiceImpl(WarehouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return repository.save(warehouse);
    }

    @Override
    public List<Warehouse> getAll() {
        return repository.findAll();
    }
}
