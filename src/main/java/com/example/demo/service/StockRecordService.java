package com.example.demo.service;

import com.example.demo.entity.StockRecord;
import java.util.List;

public interface StockRecordService {

    StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord stockRecord);

    StockRecord getById(Long id);

    List<StockRecord> getRecordsByProduct(Long productId);

    List<StockRecord> getRecordsByWarehouse(Long warehouseId);
}
