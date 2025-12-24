package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {
    
    @Autowired
    private PredictionRuleRepository predictionRuleRepository;
    
    @Autowired
    private StockRecordRepository stockRecordRepository;
    
    @Autowired
    private ConsumptionLogRepository consumptionLogRepository;
    
    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("Average days window must be greater than zero");
        }
        
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("Min daily usage must be less than or equal to max daily usage");
        }
        
        if (predictionRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Rule name must be unique");
        }
        
        rule.setCreatedAt(LocalDateTime.now());
        return predictionRuleRepository.save(rule);
    }
    
    @Override
    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }
    
    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
        
        List<ConsumptionLog> logs = consumptionLogRepository.findByStockRecordId(stockRecordId);
        
        if (logs.isEmpty()) {
            return LocalDate.now().plusDays(30); // Default prediction if no consumption data
        }
        
        // Simple prediction logic: calculate average daily consumption
        double totalConsumed = logs.stream().mapToInt(ConsumptionLog::getConsumedQuantity).sum();
        long daysCovered = logs.size(); // Simplified - assuming one log per day
        
        if (daysCovered == 0) {
            return LocalDate.now().plusDays(30);
        }
        
        double avgDailyConsumption = totalConsumed / daysCovered;
        
        if (avgDailyConsumption <= 0) {
            return LocalDate.now().plusDays(365); // Very long time if no consumption
        }
        
        int currentQuantity = stockRecord.getCurrentQuantity();
        int reorderThreshold = stockRecord.getReorderThreshold();
        
        int quantityUntilReorder = currentQuantity - reorderThreshold;
        
        if (quantityUntilReorder <= 0) {
            return LocalDate.now(); // Need to restock now
        }
        
        long daysUntilRestock = (long) Math.ceil(quantityUntilReorder / avgDailyConsumption);
        
        return LocalDate.now().plusDays(daysUntilRestock);
    }
}