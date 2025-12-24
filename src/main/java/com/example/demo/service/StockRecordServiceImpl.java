package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.StockRecord;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {
    
    @Autowired
    private StockRecordRepository stockRecordRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private WarehouseRepository warehouseRepository;
    
    @Override
    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        
        // Check for duplicate stock record
        List<StockRecord> existingRecords = stockRecordRepository.findByProductId(productId);
        boolean duplicateExists = existingRecords.stream()
                .anyMatch(sr -> sr.getWarehouse().getId().equals(warehouseId));
        
        if (duplicateExists) {
            throw new IllegalArgumentException("StockRecord already exists");
        }
        
        if (record.getCurrentQuantity() < 0) {
            throw new IllegalArgumentException("Current quantity must be greater than or equal to zero");
        }
        
        if (record.getReorderThreshold() <= 0) {
            throw new IllegalArgumentException("Reorder threshold must be greater than zero");
        }
        
        record.setProduct(product);
        record.setWarehouse(warehouse);
        record.setLastUpdated(LocalDateTime.now());
        
        return stockRecordRepository.save(record);
    }
    
    @Override
    public StockRecord getStockRecord(Long id) {
        return stockRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
    }
    
    @Override
    public List<StockRecord> getRecordsBy_product(Long productId) {
        return stockRecordRepository.findByProductId(productId);
    }
    
    @Override
    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return stockRecordRepository.findByWarehouseId(warehouseId);
    }
}