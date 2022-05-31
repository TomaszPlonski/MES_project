package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @OrderBy("sequencePosition")
    @OneToMany(mappedBy = "product")
    private List<StageExecution> stageExecution = new ArrayList<>();

    private LocalDate plannedEndOfProduction;

    private Boolean productionFinished;

    @OneToOne
    @JoinColumn(name="active_stage_id")
    private StageExecution activeStage;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
