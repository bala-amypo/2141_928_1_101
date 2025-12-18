package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockId;
    private Integer consumedQuantity;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStockId() { return stockId; }
    public void setStockId(Long stockId) { this.stockId = stockId; }

    public Integer getConsumedQuantity() { return consumedQuantity; }
    public void setConsumedQuantity(Integer consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }
}
