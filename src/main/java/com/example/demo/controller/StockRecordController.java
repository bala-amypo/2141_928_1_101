package com.example.demo.controller;

import com.example.demo.entity.StockRecord;
import com.example.demo.service.StockRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {

    private final StockRecordService service;

    public StockRecordController(StockRecordService service) {
        this.service = service;
    }

    // POST /api/stocks
    @PostMapping
    public StockRecord create(@RequestBody StockRecord stockRecord) {
        return service.save(stockRecord);
    }

    // GET /api/stocks
    @GetMapping
    public List<StockRecord> getAll() {
        return service.getAll();
    }

    // GET /api/stocks/{id}
    @GetMapping("/{id}")
    public StockRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET /api/stocks/product/{productId}
    @GetMapping("/product/{productId}")
    public List<StockRecord> getByProduct(@PathVariable Long productId) {
        return service.getRecordsByProduct(productId);
    }
}
