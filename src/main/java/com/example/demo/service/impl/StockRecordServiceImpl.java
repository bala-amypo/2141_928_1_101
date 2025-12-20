package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.entity.StockRecord;
import com.example.demo.entity.Warehouse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository stockRecordRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockRecordServiceImpl(
            StockRecordRepository stockRecordRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository) {

        this.stockRecordRepository = stockRecordRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord stockRecord) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        stockRecord.setProduct(product);
        stockRecord.setWarehouse(warehouse);

        return stockRecordRepository.save(stockRecord);
    }

    @Override
    public StockRecord getById(Long id) {
        return stockRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockRecord not found"));
    }

    @Override
    public List<StockRecord> getRecordsByProduct(Long productId) {
        return stockRecordRepository.findByProductId(productId);
    }

    @Override
    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return stockRecordRepository.findByWarehouseId(warehouseId);
    }
}
