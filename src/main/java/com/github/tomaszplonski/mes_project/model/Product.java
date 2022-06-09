package com.github.tomaszplonski.mes_project.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

   private Integer duration;

    public static class ProductBuilder{

        public ProductBuilder activeStage(){
            this.activeStage = productionMap.values().stream().filter(e->e.getActualEndOfStage()==null).findFirst().get();
            return this;
        }

        public ProductBuilder duration(){
            this.duration = productionMap.values().stream().mapToInt(StageExecution::getDuration).sum();
            return this;
        }

        public ProductBuilder plannedEndOfProduction(){
            this.plannedEndOfProduction = activeStage.getActualStartOfStage().plusDays(duration);
            return this;
        }

    }

}
