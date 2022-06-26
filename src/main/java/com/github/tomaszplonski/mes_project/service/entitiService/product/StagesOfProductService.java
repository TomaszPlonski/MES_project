package com.github.tomaszplonski.mes_project.service.entitiService.product;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.model.StageExecution;

import java.time.LocalDate;
import java.util.Map;

public interface StagesOfProductService {

    Map<ProductionPhase, StageExecution> stageInitialization(ProductType type);

    Integer getDelayOfProduction(Product product);

    StageExecution initialEstimatedEndOfStage(StageExecution actualStage, LocalDate estimatedStart);

    Boolean endActiveStage(Long productId);


}
