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
@Table(name ="stage_execution")
public class StageExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long nextStepId;

    private int duration;

    private LocalDate startOfStage;

    private LocalDate estimatedEndOfStage;

    private LocalDate actualEndOfStage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "production_phase_id")
    private ProductionPhase productionPhase;

    @ManyToOne
    @JoinColumn(name = "production_executor_id")
    private PhaseExecutor phaseExecutor;

    @PrePersist
    public void prePersist(){
        estimatedEndOfStage = startOfStage.plusDays(duration);
    }

}
