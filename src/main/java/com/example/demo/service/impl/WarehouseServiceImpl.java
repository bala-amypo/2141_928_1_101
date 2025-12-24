package com.example.demo.service.impl;

import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse saveWarehouse(Warehouse warehouse) {
        warehouse.setCreatedAt(LocalDateTime.now());
        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }
}
