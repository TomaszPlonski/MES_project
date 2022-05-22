package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double orderValue;

    @ManyToOne
    @JoinColumn(name = "purchaser_id")
    private Purchaser purchaser;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    @OneToMany(mappedBy = "order")
    private Set<Product> products = new HashSet<>();

}
