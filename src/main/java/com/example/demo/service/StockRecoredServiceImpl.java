package com.example.demo.service;

import com.example.demo.model.StockRecord;
import com.example.demo.model.Product;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord stockRecord) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
            .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        
        // Check for existing record
        List<StockRecord> existing = stockRecordRepository.findByProductId(productId);
        if (existing.stream().anyMatch(sr -> sr.getWarehouse().getId().equals(warehouseId))) {
            throw new IllegalArgumentException("StockRecord already exists");
        }
        
        stockRecord.setProduct(product);
        stockRecord.setWarehouse(warehouse);
        return stockRecordRepository.save(stockRecord);
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
}