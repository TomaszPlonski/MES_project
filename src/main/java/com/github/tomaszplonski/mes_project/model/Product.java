package com.github.tomaszplonski.mes_project.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "product")
    private List<StageExecution> stageExecution = new ArrayList<>();

    @Nullable
    private Long activeStageId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
