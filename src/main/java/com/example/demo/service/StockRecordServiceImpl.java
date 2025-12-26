package com.example.demo.service;

import com.example.demo.model.StockRecord;
import com.example.demo.repository.StockRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {
    
    @Autowired
    private StockRecordRepository stockRecordRepository;
    
    @Override
    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord stockRecord) {
        return stockRecordRepository.save(stockRecord);
    }
    
    @Override
    public StockRecord getStockRecord(Long id) {
        return stockRecordRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<StockRecord> getRecordsBy_product(Long productId) {
        return stockRecordRepository.findByProductId(productId);
    }
}