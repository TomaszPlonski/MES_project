package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="product_types")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productType;

    @OneToMany(mappedBy = "productType")
    List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "productType")
    List<TypeAttribute> typeAttributes = new ArrayList<>();
}
