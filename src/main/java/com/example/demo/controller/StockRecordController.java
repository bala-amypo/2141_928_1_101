package com.example.demo.controller;

import com.example.demo.entity.StockRecord;
import com.example.demo.service.StockRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockRecordController {

    private final StockRecordService service;

    public StockRecordController(StockRecordService service) {
        this.service = service;
    }

    @PostMapping
    public StockRecord create(@RequestBody StockRecord stock) {
        return service.save(stock);
    }

    @GetMapping
    public List<StockRecord> getAll() {
        return service.getAll();
    }
}
