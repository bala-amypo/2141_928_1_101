package com.example.demo.controller;

import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predict")
@Tag(name = "Prediction")
public class PredictionRuleController {

    private final PredictionRuleService service;

    public PredictionRuleController(PredictionRuleService service) {
        this.service = service;
    }

    @PostMapping("/rules")
    public PredictionRule createRule(@RequestBody PredictionRule rule) {
        return service.createRule(rule);
    }

    @GetMapping("/rules")
    public List<PredictionRule> getRules() {
        return service.getAllRules();
    }
}
