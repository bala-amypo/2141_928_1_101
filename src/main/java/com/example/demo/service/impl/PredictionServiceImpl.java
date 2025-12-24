package com.example.demo.service.impl;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PredictionServiceImpl implements PredictionService {


private final StockRecordRepository stockRepository;
private final ConsumptionLogRepository logRepository;
private final PredictionRuleRepository ruleRepository;


public PredictionServiceImpl(StockRecordRepository stockRepository,
ConsumptionLogRepository logRepository,
PredictionRuleRepository ruleRepository) {
this.stockRepository = stockRepository;
this.logRepository = logRepository;
this.ruleRepository = ruleRepository;
}


@Override
public PredictionRule createRule(PredictionRule rule) {
if (rule.getAverageDaysWindow() <= 0) {
throw new IllegalArgumentException("averageDaysWindow must be greater than zero");
}
if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
throw new IllegalArgumentException("Invalid usage range");
}
ruleRepository.findByRuleName(rule.getRuleName()).ifPresent(r -> {
throw new IllegalArgumentException("Rule name already exists");
});
rule.setCreatedAt(LocalDateTime.now());
return ruleRepository.save(rule);
}


@Override
public List<PredictionRule> getAllRules() {
return ruleRepository.findAll();
}


@Override
public LocalDate predictRestockDate(Long stockRecordId) {
StockRecord record = stockRepository.findById(stockRecordId)
.orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));


List<ConsumptionLog> logs = logRepository.findByStockRecordId(stockRecordId);
if (logs.isEmpty()) {
return LocalDate.now();
}


double avgDaily = logs.stream().mapToInt(ConsumptionLog::getConsumedQuantity).average().orElse(0);
if (avgDaily <= 0) {
return LocalDate.now();
}


int remaining = record.getCurrentQuantity() - record.getReorderThreshold();
int days = (int) Math.ceil(remaining / avgDaily);
return LocalDate.now().plusDays(Math.max(days, 0));
}
}