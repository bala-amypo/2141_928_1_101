package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository repo;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    public StockRecordServiceImpl(
            StockRecordRepository repo,
            ProductRepository productRepo,
            WarehouseRepository warehouseRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.warehouseRepo = warehouseRepo;
    }

    @Override
    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        if (repo.existsByProductIdAndWarehouseId(productId, warehouseId)) {
            throw new IllegalArgumentException("StockRecord already exists");
        }

        if (record.getCurrentQuantity() < 0) {
            throw new IllegalArgumentException("currentQuantity must be >= 0");
        }

        if (record.getReorderThreshold() <= 0) {
            throw new IllegalArgumentException("reorderThreshold must be > 0");
        }

        record.setProduct(product);
        record.setWarehouse(warehouse);
        record.setLastUpdated(LocalDateTime.now());

        return repo.save(record);
    }

    @Override
    public StockRecord getStockRecord(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
    }

    @Override
    public List<StockRecord> getRecordsBy_product(Long productId) {
        return repo.findByProductId(productId);
    }
}
