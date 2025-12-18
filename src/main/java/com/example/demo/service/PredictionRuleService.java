package com.example.demo.service;

import com.example.demo.entity.PredictionRule;

import java.util.List;

public interface PredictionRuleService {

    PredictionRule save(PredictionRule rule);

    List<PredictionRule> getAll();
}
