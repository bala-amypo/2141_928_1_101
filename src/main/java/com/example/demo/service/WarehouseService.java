package com.example.demo.service;

import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface WarehouseService {
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse getWarehouse(Long id);
}

@Service
class WarehouseServiceImpl implements WarehouseService {
    
    @Autowired
    private WarehouseRepository warehouseRepository;
    
    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }
    
    @Override
    public Warehouse getWarehouse(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }
}
