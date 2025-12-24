package com.example.demo.service.impl;

import com.example.demo.model.PredictionRule;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRuleRepository repository;

    @Override
    public PredictionRule saveRule(PredictionRule rule) {
        rule.setCreatedAt(LocalDateTime.now());
        return repository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return repository.findAll();
    }
}
