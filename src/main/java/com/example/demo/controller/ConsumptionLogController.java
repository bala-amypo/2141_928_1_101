package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {
    
    @Autowired
    private ConsumptionLogService consumptionLogService;
    
    @PostMapping("/{stockRecordId}")
    public ResponseEntity<ConsumptionLog> logConsumption(
            @PathVariable Long stockRecordId,
            @RequestBody ConsumptionLog log) {
        ConsumptionLog created = consumptionLogService.logConsumption(stockRecordId, log);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/record/{stockRecordId}")
    public ResponseEntity<List<ConsumptionLog>> getLogsByStockRecord(@PathVariable Long stockRecordId) {
        List<ConsumptionLog> logs = consumptionLogService.getLogsByStockRecord(stockRecordId);
        return ResponseEntity.ok(logs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionLog> getLog(@PathVariable Long id) {
        ConsumptionLog log = consumptionLogService.getLog(id);
        return ResponseEntity.ok(log);
    }
}