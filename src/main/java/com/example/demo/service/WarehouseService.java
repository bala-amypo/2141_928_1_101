package com.example.demo.service;

import com.example.demo.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse save(Warehouse warehouse);

    List<Warehouse> getAll();

    Warehouse getById(Long id);
}
