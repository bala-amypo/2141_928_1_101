package com.example.demo.controller;

import com.example.demo.entity.PredictionRule;
import com.example.demo.service.PredictionRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predict")
public class PredictionRuleController {

    private final PredictionRuleService service;

    public PredictionRuleController(PredictionRuleService service) {
        this.service = service;
    }

    // POST /api/predict/rules
    @PostMapping("/rules")
    public PredictionRule create(@RequestBody PredictionRule rule) {
        return service.save(rule);
    }

    // GET /api/predict/rules
    @GetMapping("/rules")
    public List<PredictionRule> getAll() {
        return service.getAll();
    }
}
