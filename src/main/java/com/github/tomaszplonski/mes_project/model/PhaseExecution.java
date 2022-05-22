package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="phase_execution")
public class PhaseExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate starOfPhase;

    private LocalDate estimatedEndOfPhase;

    private LocalDate actualEndOfPhase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "production_phase_id")
    private ProductionPhase productionPhase;

    @ManyToOne
    @JoinColumn(name = "production_executor_id")
    private PhaseExecutor phaseExecutor;
}
