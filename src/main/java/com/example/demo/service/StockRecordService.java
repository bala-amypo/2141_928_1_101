package com.example.demo.service;

import com.example.demo.entity.StockRecord;

import java.util.List;

public interface StockRecordService {

    StockRecord save(StockRecord stockRecord);

    List<StockRecord> getAll();

    StockRecord getById(Long id);

    List<StockRecord> getRecordsByProduct(Long productId);
}
