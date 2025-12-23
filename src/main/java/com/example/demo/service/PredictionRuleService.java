package com.example.demo.service;

import com.example.demo.model.PredictionRule;

import java.util.List;

public interface PredictionRuleService {

    PredictionRule createRule(PredictionRule rule);

    List<PredictionRule> getAllRules();
}
