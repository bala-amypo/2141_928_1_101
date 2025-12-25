package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/consumption")
@Tag(name = "Consumption Management", description = "APIs for managing consumption logs")
public class ConsumptionController {
    
    @Autowired
    private ConsumptionLogService consumptionLogService;
    
    @PostMapping("/{stockRecordId}")
    @Operation(summary = "Log consumption")
    public ResponseEntity<ConsumptionLog> logConsumption(
            @PathVariable Long stockRecordId,
            @Valid @RequestBody ConsumptionLog consumptionLog) {
        try {
            ConsumptionLog created = consumptionLogService.logConsumption(stockRecordId, consumptionLog);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/record/{stockRecordId}")
    @Operation(summary = "Get consumption logs by stock record ID")
    public ResponseEntity<List<ConsumptionLog>> getLogsByStockRecord(@PathVariable Long stockRecordId) {
        return ResponseEntity.ok(consumptionLogService.getLogsByStockRecord(stockRecordId));
    }
}
