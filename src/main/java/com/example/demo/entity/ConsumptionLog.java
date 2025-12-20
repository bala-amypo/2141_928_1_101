package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockRecordId;
    private Integer quantity;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStockRecordId() { return stockRecordId; }
    public void setStockRecordId(Long stockRecordId) {
        this.stockRecordId = stockRecordId;
    }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
