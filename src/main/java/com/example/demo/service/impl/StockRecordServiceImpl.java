package com.example.demo.service.impl;

import com.example.demo.model.StockRecord;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository stockRecordRepository;

    @Override
    public StockRecord saveStockRecord(StockRecord record) {
        record.setLastUpdated(LocalDateTime.now());
        return stockRecordRepository.save(record);
    }

    @Override
    public List<StockRecord> getAllRecords() {
        return stockRecordRepository.findAll();
    }
}
