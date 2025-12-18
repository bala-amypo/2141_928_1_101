package com.example.demo.service.impl;

import com.example.demo.entity.StockRecord;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository repo;

    public StockRecordServiceImpl(StockRecordRepository repo) {
        this.repo = repo;
    }

    public StockRecord save(StockRecord stock) {
        return repo.save(stock);
    }

    public List<StockRecord> getAll() {
        return repo.findAll();
    }
}
