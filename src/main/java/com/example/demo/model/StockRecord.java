package com.example.demo.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_records",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "warehouse_id"})
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Warehouse warehouse;

    private Integer currentQuantity;

    private Integer reorderThreshold;

    private LocalDateTime lastUpdated;
}
