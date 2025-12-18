package com.example.demo.service.impl;

import com.example.demo.entity.PredictionRule;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.service.PredictionRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionRuleServiceImpl implements PredictionRuleService {

    private final PredictionRuleRepository repository;

    public PredictionRuleServiceImpl(PredictionRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public PredictionRule save(PredictionRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<PredictionRule> getAll() {
        return repository.findAll();
    }
}
