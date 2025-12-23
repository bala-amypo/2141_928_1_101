package com.example.demo.service.impl;

import com.example.demo.model.PredictionRule;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.service.PredictionRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionRuleServiceImpl implements PredictionRuleService {

    private final PredictionRuleRepository repository;

    public PredictionRuleServiceImpl(PredictionRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        rule.setCreatedAt(LocalDateTime.now());
        return repository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return repository.findAll();
    }
}
