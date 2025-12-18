package com.example.demo.repository;

import com.example.demo.entity.ConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {
}
