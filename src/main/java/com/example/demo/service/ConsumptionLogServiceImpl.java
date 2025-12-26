package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.repository.ConsumptionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    
    @Autowired
    private ConsumptionLogRepository consumptionLogRepository;
    
    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog consumptionLog) {
        return consumptionLogRepository.save(consumptionLog);
    }
    
    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return consumptionLogRepository.findByStockRecordId(stockRecordId);
    }
}