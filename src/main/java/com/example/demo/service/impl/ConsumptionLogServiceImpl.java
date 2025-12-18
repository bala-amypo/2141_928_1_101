package com.example.demo.service.impl;

import com.example.demo.entity.ConsumptionLog;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository repo;

    public ConsumptionLogServiceImpl(ConsumptionLogRepository repo) {
        this.repo = repo;
    }

    public ConsumptionLog save(ConsumptionLog log) {
        return repo.save(log);
    }

    public List<ConsumptionLog> getAll() {
        return repo.findAll();
    }
}
