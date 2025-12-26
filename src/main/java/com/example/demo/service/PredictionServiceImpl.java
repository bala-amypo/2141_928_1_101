package com.example.demo.service;

import com.example.demo.model.PredictionRule;
import com.example.demo.repository.PredictionRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {
    
    @Autowired
    private PredictionRuleRepository predictionRuleRepository;
    
    @Override
    public PredictionRule createRule(PredictionRule rule) {
        return predictionRuleRepository.save(rule);
    }
    
    @Override
    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }
    
    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        return LocalDate.now().plusDays(7);
    }
}