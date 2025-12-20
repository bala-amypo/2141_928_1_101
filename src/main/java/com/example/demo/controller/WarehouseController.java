package com.example.demo.controller;

import com.example.demo.entity.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @PostMapping
    public Warehouse create(@RequestBody Warehouse warehouse) {
        return warehouseService.save(warehouse);
    }
}
