package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@Tag(name = "Stock Management", description = "APIs for managing stock records")
public class StockController {
    
    @Autowired
    private StockRecordService stockRecordService;
    
    @PostMapping("/{productId}/{warehouseId}")
    @Operation(summary = "Create a new stock record")
    public ResponseEntity<StockRecord> createStockRecord(
            @PathVariable Long productId,
            @PathVariable Long warehouseId,
            @Valid @RequestBody StockRecord stockRecord) {
        try {
            StockRecord created = stockRecordService.createStockRecord(productId, warehouseId, stockRecord);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get stock record by ID")
    public ResponseEntity<StockRecord> getStockRecord(@PathVariable Long id) {
        StockRecord stockRecord = stockRecordService.getStockRecord(id);
        return stockRecord != null ? ResponseEntity.ok(stockRecord) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/product/{productId}")
    @Operation(summary = "Get stock records by product ID")
    public ResponseEntity<List<StockRecord>> getRecordsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(stockRecordService.getRecordsBy_product(productId));
    }
}
