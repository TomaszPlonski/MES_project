package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="expansion_joints")
public class ExpansionJoint extends Product {

    private String bridgeName;

    private String type;

    private double length;

    private LocalDate estimatedEndOfGeometryWelding;

    private LocalDate estimatedEndOfAnchoringWelding;

    private LocalDate estimatedEndOfMakingOfAntiCorrosionProtection;

    private LocalDate actualEndOfGeometryWelding;

    private LocalDate actualEndOfAnchoringWelding;

    private LocalDate actualEndOfMakingOfAntiCorrosionProtection;

    private LocalDate actualEndOfProduction;

    @ManyToOne
    private Order order;

    public void setEstimatedTimes(){
        this.setEstimatedEndOfGeometryWelding(this.getStarOfProduction().plusDays(10));
        this.setEstimatedEndOfAnchoringWelding(this.getStarOfProduction().plusDays(13));
        this.setEstimatedEndOfMakingOfAntiCorrosionProtection(this.getStarOfProduction().plusDays(16));
        this.setEstimatedEndOfProduction(this.getStarOfProduction().plusDays(20));
    }

}
