=package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_record_id", nullable = false)
    private StockRecord stockRecord;
    
    @Column(nullable = false)
    private Integer consumedQuantity;
    
    @Column(nullable = false)
    private LocalDate consumedDate;
    
    @PrePersist
    protected void onCreate() {
        if (consumedDate == null) {
            consumedDate = LocalDate.now();
        }
    }
}