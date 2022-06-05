package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private Boolean orderFinished;

    @OneToMany(mappedBy = "order")
    private List<Product> products = new ArrayList<>();

}
