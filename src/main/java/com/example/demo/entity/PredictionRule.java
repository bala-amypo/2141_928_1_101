package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "prediction_rules",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "ruleName")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private Integer averageDaysWindow;

    @Column(nullable = false)
    private Integer minThreshold;

    @Column(nullable = false)
    private Integer maxThreshold;
}
