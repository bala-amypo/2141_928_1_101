package com.example.demo.controller;

import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/predict")
@Tag(name = "Prediction Management", description = "APIs for managing predictions")
public class PredictionController {
    
    @Autowired
    private PredictionService predictionService;
    
    @PostMapping("/rules")
    @Operation(summary = "Create a new prediction rule")
    public ResponseEntity<PredictionRule> createRule(@Valid @RequestBody PredictionRule rule) {
        PredictionRule created = predictionService.createRule(rule);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/rules")
    @Operation(summary = "Get all prediction rules")
    public ResponseEntity<List<PredictionRule>> getAllRules() {
        return ResponseEntity.ok(predictionService.getAllRules());
    }
    
    @GetMapping("/restock-date/{stockRecordId}")
    @Operation(summary = "Predict restock date")
    public ResponseEntity<String> predictRestockDate(@PathVariable Long stockRecordId) {
        LocalDate date = predictionService.predictRestockDate(stockRecordId);
        return ResponseEntity.ok(date.toString());
    }
}