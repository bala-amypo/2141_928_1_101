package com.example.demo.controller;

import com.example.demo.entity.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Warehouse create(@RequestBody Warehouse warehouse) {
        return warehouseService.save(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAll() {
        return warehouseService.getAll();
    }
}
