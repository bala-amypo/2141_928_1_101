package com.example.demo.controller;

import com.example.demo.entity.PredictionRule;
import com.example.demo.service.PredictionRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predictions")
public class PredictionRuleController {

    private final PredictionRuleService service;

    public PredictionRuleController(PredictionRuleService service) {
        this.service = service;
    }

    @PostMapping
    public PredictionRule create(@RequestBody PredictionRule rule) {
        return service.save(rule);
    }

    @GetMapping
    public List<PredictionRule> getAll() {
        return service.getAll();
    }
}
