package com.example.demo.service;

import com.example.demo.model.Warehouse;

public interface WarehouseService {
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse getWarehouse(Long id);
}