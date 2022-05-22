package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "product")
    private List<PhaseExecution> phaseExecution = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;






}
