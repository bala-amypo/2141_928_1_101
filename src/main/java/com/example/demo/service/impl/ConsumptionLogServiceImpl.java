package com.example.demo.service.impl;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository consumptionLogRepository;
    private final StockRecordRepository stockRecordRepository;

    @Autowired
    public ConsumptionLogServiceImpl(ConsumptionLogRepository consumptionLogRepository,
                                     StockRecordRepository stockRecordRepository) {
        this.consumptionLogRepository = consumptionLogRepository;
        this.stockRecordRepository = stockRecordRepository;
    }

    @Override
    public ConsumptionLog saveConsumptionLog(ConsumptionLog log) {
        // Ensure stock record exists
        StockRecord record = log.getStockRecord();
        if (record != null) {
            record.setLastUpdated(LocalDateTime.now());
            stockRecordRepository.save(record);
        }
        return consumptionLogRepository.save(log);
    }

    @Override
    public List<ConsumptionLog> getAllConsumptionLogs() {
        return consumptionLogRepository.findAll();
    }

    @Override
    public Optional<ConsumptionLog> getConsumptionLogById(Long id) {
        return consumptionLogRepository.findById(id);
    }

    @Override
    public void deleteConsumptionLog(Long id) {
        consumptionLogRepository.deleteById(id);
    }
}
