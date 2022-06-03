package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "production_mapping",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "stage_execution_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "production_phase_id")
    private Map<ProductionPhase, StageExecution> productionMap = new LinkedHashMap<>();

    private LocalDate plannedEndOfProduction;

    private Boolean productionFinished;

    @OneToOne
    @JoinColumn(name="active_stage_id")
    private StageExecution activeStage;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_attribute_mapping",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_value_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "type_attribute_id")
    private Map<TypeAttribute, AttributeValue> typeAttributeMap = new LinkedHashMap<>();


    @ManyToOne
    @JoinColumn(name="product_type_id")
    private ProductType productType;

}
