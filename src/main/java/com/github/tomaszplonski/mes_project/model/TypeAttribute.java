package com.github.tomaszplonski.mes_project.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type_attribute")
public class TypeAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private ProductType productType;


}
