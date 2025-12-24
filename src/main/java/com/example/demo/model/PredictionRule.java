package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String ruleName;
    
    @Column(nullable = false)
    private Integer averageDaysWindow;
    
    @Column(nullable = false)
    private Integer minDailyUsage;
    
    @Column(nullable = false)
    private Integer maxDailyUsage;
    
    private LocalDateTime createdAt;
}