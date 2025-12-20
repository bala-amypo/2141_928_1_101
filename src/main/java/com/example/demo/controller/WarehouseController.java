package com.example.demo.controller;

import com.example.demo.entity.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    // POST /api/warehouses
    @PostMapping
    public Warehouse create(@RequestBody Warehouse warehouse) {
        return service.save(warehouse);
    }

    // GET /api/warehouses
    @GetMapping
    public List<Warehouse> getAll() {
        return service.getAll();
    }

    // ✅ GET /api/warehouses/{id}
    @GetMapping("/{id}")
    public Warehouse getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
