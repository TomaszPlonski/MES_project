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

    @OneToMany(mappedBy = "order")
    private Set<Product> products = new HashSet<>();

}
