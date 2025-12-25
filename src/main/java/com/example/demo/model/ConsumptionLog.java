package com.example.demo.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consumption_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private StockRecord stockRecord;

    private Integer consumedQuantity;

    private LocalDate consumedDate;
}
