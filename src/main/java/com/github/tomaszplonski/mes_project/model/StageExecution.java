package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="stage_execution")
public class StageExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int duration;

    private LocalDate estimatedStartOfStage;

    private LocalDate actualStartOfStage;

    private LocalDate estimatedEndOfStage;

    private LocalDate actualEndOfStage;

    @PrePersist
    public void prePersist(){
        estimatedEndOfStage = estimatedStartOfStage.plusDays(duration);
    }

}
