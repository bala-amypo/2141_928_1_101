package com.example.demo.controller;

import com.example.demo.entity.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumptions")
public class ConsumptionLogController {

    private final ConsumptionLogService service;

    public ConsumptionLogController(ConsumptionLogService service) {
        this.service = service;
    }

    @PostMapping
    public ConsumptionLog create(@RequestBody ConsumptionLog log) {
        return service.save(log);
    }

    @GetMapping
    public List<ConsumptionLog> getAll() {
        return service.getAll();
    }
}
