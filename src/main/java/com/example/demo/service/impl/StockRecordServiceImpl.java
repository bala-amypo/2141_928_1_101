package com.example.demo.service.impl;

import com.example.demo.entity.StockRecord;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;

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
    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord stockRecord) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        stockRecord.setProduct(product);
        stockRecord.setWarehouse(warehouse);

        return repo.save(stockRecord);
    }

    @Override
    public StockRecord getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("StockRecord not found"));
    }

    @Override
    public List<StockRecord> getRecordsByProduct(Long productId) {
        return repo.findByProductId(productId);
    }

    @Override
    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return repo.findByWarehouseId(warehouseId);
    }
}

