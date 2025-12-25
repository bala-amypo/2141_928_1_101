package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/warehouses")
@Tag(name = "Warehouse Management", description = "APIs for managing warehouses")
public class WarehouseController {
    
    @Autowired
    private WarehouseService warehouseService;
    
    @PostMapping
    @Operation(summary = "Create a new warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody Warehouse warehouse) {
        Warehouse created = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get warehouse by ID")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getWarehouse(id);
        return warehouse != null ? ResponseEntity.ok(warehouse) : ResponseEntity.notFound().build();
    }
}
