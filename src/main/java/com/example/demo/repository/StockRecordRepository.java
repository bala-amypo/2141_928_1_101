package com.example.demo.repository;

import com.example.demo.entity.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
}
