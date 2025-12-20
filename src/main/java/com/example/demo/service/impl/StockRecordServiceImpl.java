package com.example.demo.service.impl;

import com.example.demo.entity.StockRecord;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository repository;

    public StockRecordServiceImpl(StockRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public StockRecord save(StockRecord stockRecord) {
        return repository.save(stockRecord);
    }

    @Override
    public List<StockRecord> getAll() {
        return repository.findAll();
    }

    @Override
    public StockRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockRecord not found"));
    }

    @Override
    public List<StockRecord> getRecordsByProduct(Long productId) {
        return repository.findByProductId(productId);
    }
}
