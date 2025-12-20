package com.example.demo.service;

import com.example.demo.entity.ConsumptionLog;

import java.util.List;

public interface ConsumptionLogService {

    ConsumptionLog save(ConsumptionLog log);

    List<ConsumptionLog> getAll();

    ConsumptionLog getById(Long id);

    List<ConsumptionLog> getByStockRecord(Long stockRecordId);
}
