package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consumption_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int consumedQuantity;
    private LocalDateTime consumedDate;

    @ManyToOne
    @JoinColumn(name = "stock_record_id")
    private StockRecord stockRecord;
}
