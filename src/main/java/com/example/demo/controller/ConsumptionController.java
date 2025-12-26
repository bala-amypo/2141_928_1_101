package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionController {
    
    @Autowired
    private ConsumptionLogService consumptionLogService;
    
    @PostMapping("/{stockRecordId}")
    public ResponseEntity<ConsumptionLog> logConsumption(
            @PathVariable Long stockRecordId,
            @RequestBody ConsumptionLog consumptionLog) {
        try {
            ConsumptionLog logged = consumptionLogService.logConsumption(stockRecordId, consumptionLog);
            return ResponseEntity.ok(logged);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/record/{stockRecordId}")
    public ResponseEntity<List<ConsumptionLog>> getLogsByStockRecord(@PathVariable Long stockRecordId) {
        List<ConsumptionLog> logs = consumptionLogService.getLogsByStockRecord(stockRecordId);
        return ResponseEntity.ok(logs);
    }
}