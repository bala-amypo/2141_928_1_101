package com.example.demo.controller;

import com.example.demo.entity.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumptions")
public class ConsumptionLogController {

    private final ConsumptionLogService service;

    public ConsumptionLogController(ConsumptionLogService service) {
        this.service = service;
    }

    // POST /api/consumptions
    @PostMapping
    public ConsumptionLog create(@RequestBody ConsumptionLog log) {
        return service.save(log);
    }

    // GET /api/consumptions
    @GetMapping
    public List<ConsumptionLog> getAll() {
        return service.getAll();
    }

    // ✅ GET /api/consumptions/{id}
    @GetMapping("/{id}")
    public ConsumptionLog getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // (OPTIONAL – useful for project, safe to keep)
    // GET /api/consumptions/stock/{stockRecordId}
    @GetMapping("/stock/{stockRecordId}")
    public List<ConsumptionLog> getByStockRecord(@PathVariable Long stockRecordId) {
        return service.getByStockRecord(stockRecordId);
    }
}
