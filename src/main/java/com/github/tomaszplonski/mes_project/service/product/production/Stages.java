package com.github.tomaszplonski.mes_project.service.product.production;

import com.github.tomaszplonski.mes_project.model.PhaseExecutor;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;

import java.time.LocalDate;

public interface Stages {

   public LocalDate StageInitialization(Product product, ProductionPhase productionPhase, PhaseExecutor phaseExecutor, int duration, LocalDate stratOfStage);

   public Integer EndOfStage(Long stageId);

   public Integer changeEstimatedEndOfStage(Long stageId, LocalDate newEstimatedEndOfStage);
}
