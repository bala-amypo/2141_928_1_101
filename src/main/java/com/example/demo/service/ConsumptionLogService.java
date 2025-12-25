package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

public interface ConsumptionLogService {
    ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog consumptionLog);
    List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId);
}

@Service
class ConsumptionLogServiceImpl implements ConsumptionLogService {
    
    @Autowired
    private ConsumptionLogRepository consumptionLogRepository;
    
    @Autowired
    private StockRecordRepository stockRecordRepository;
    
    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog consumptionLog) {
        if (consumptionLog.getConsumedDate() != null && consumptionLog.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future");
        }
        
        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId).orElse(null);
        consumptionLog.setStockRecord(stockRecord);
        return consumptionLogRepository.save(consumptionLog);
    }
    
    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return consumptionLogRepository.findByStockRecordId(stockRecordId);
    }
}