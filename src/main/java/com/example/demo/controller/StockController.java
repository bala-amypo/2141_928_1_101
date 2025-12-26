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
    public ResponseEntity<StockRecord> createStockRecord(
            @PathVariable Long productId,
            @PathVariable Long warehouseId,
            @RequestBody StockRecord stockRecord) {
        try {
            StockRecord created = stockRecordService.createStockRecord(productId, warehouseId, stockRecord);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StockRecord> getStockRecord(@PathVariable Long id) {
        StockRecord stockRecord = stockRecordService.getStockRecord(id);
        return ResponseEntity.ok(stockRecord);
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockRecord>> getRecordsByProduct(@PathVariable Long productId) {
        List<StockRecord> records = stockRecordService.getRecordsBy_product(productId);
        return ResponseEntity.ok(records);
    }
}