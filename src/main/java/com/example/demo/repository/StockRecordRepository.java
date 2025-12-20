package com.example.demo.repository;

import com.example.demo.entity.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {

    List<StockRecord> findByProductId(Long productId);
}
