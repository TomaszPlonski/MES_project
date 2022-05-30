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

    @OneToOne
    @JoinColumn(name="next_stage_id")
    private StageExecution nextStage;

    private int duration;

    private int sequencePosition;

    private LocalDate estimatedStartOfStage;

    private LocalDate actualStartOfStage;

    private LocalDate estimatedEndOfStage;

    private LocalDate actualEndOfStage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "production_phase_id")
    private ProductionPhase productionPhase;

    @PrePersist
    public void prePersist(){
        estimatedEndOfStage = estimatedStartOfStage.plusDays(duration);
    }

}
