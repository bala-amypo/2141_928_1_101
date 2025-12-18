package com.example.demo.repository;

import com.example.demo.entity.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {

    Optional<StockRecord> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}
