package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="production_phases")
public class ProductionPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int defaultDuration;

    @OneToMany(mappedBy = "productionPhase")
    private Set<StageExecution> stageExecution = new HashSet<>();
}
