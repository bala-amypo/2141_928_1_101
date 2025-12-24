package com.example.demo.service.impl;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;


@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {


private final ConsumptionLogRepository repository;
private final StockRecordRepository stockRecordRepository;


public ConsumptionLogServiceImpl(ConsumptionLogRepository repository, StockRecordRepository stockRecordRepository) {
this.repository = repository;
this.stockRecordRepository = stockRecordRepository;
}


@Override
public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
StockRecord record = stockRecordRepository.findById(stockRecordId)
.orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));


if (log.getConsumedQuantity() <= 0) {
throw new IllegalArgumentException("Consumed quantity must be positive");
}
if (log.getConsumedDate().isAfter(LocalDate.now())) {
throw new IllegalArgumentException("consumedDate cannot be future");
}


log.setStockRecord(record);
return repository.save(log);
}


@Override
public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
return repository.findByStockRecordId(stockRecordId);
}


@Override
public ConsumptionLog getLog(Long id) {
return repository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
}
}