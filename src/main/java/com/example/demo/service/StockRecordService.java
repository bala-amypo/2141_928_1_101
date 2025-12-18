package com.example.demo.service;

import com.example.demo.entity.StockRecord;
import java.util.List;

public interface StockRecordService {
    StockRecord save(StockRecord stock);
    List<StockRecord> getAll();
}
