package com.example.demo.service.impl;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.StockRecord;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class StockRecordServiceImpl implements StockRecordService {


private final StockRecordRepository repository;
private final ProductRepository productRepository;
private final WarehouseRepository warehouseRepository;


public StockRecordServiceImpl(StockRecordRepository repository,
ProductRepository productRepository,
WarehouseRepository warehouseRepository) {
this.repository = repository;
this.productRepository = productRepository;
this.warehouseRepository = warehouseRepository;
}


@Override
public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {
Product product = productRepository.findById(productId)
.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
Warehouse warehouse = warehouseRepository.findById(warehouseId)
.orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));


repository.findByProductId(productId).stream()
.filter(r -> r.getWarehouse().getId().equals(warehouseId))
.findAny()
.ifPresent(r -> { throw new IllegalArgumentException("StockRecord already exists"); });


if (record.getCurrentQuantity() < 0 || record.getReorderThreshold() <= 0) {
throw new IllegalArgumentException("Invalid stock quantities");
}


record.setProduct(product);
record.setWarehouse(warehouse);
record.setLastUpdated(LocalDateTime.now());
return repository.save(record);
}


@Override
public StockRecord getStockRecord(Long id) {
return repository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
}


@Override
public List<StockRecord> getRecordsBy_product(Long productId) {
return repository.findByProductId(productId);
}


@Override
public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
return repository.findByWarehouseId(warehouseId);
}
}