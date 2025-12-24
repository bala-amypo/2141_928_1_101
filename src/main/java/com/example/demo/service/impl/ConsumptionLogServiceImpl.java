=package com.example.demo.service.impl;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository repository;

    @Override
    public ConsumptionLog saveLog(ConsumptionLog log) {
        return repository.save(log);
    }

    @Override
    public List<ConsumptionLog> getAllLogs() {
        return repository.findAll();
    }
}
