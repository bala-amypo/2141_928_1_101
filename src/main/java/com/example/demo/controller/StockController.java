package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    
    @Autowired
    private StockRecordService stockRecordService;
    
    @PostMapping("/{productId}/{warehouseId}")
    public ResponseEntity<StockRecord> createStockRecord(@PathVariable Long productId, @PathVariable Long warehouseId, @RequestBody StockRecord stockRecord) {
        try {
            return ResponseEntity.ok(stockRecordService.createStockRecord(productId, warehouseId, stockRecord));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public StockRecord getStockRecord(@PathVariable Long id) {
        return stockRecordService.getStockRecord(id);
    }
    
    @GetMapping("/product/{productId}")
    public List<StockRecord> getRecordsByProduct(@PathVariable Long productId) {
        return stockRecordService.getRecordsBy_product(productId);
    }
}