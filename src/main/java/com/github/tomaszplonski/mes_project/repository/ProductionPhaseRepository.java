package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionPhaseRepository extends JpaRepository<ProductionPhase, Long> {

    List<ProductionPhase> findAllByProductType(ProductType productType);
}
