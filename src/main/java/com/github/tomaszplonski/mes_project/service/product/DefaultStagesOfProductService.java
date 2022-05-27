package com.github.tomaszplonski.mes_project.service.product;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;

import java.time.LocalDate;

public interface DefaultStagesOfProductService {

    public Product stepsOfProductInitialization(Product product, StageExecution[] stages, LocalDate startOfProduction);

    public Product endOfActualStage(Product product);

    public Product changeEstimatedEndOfActualStage(Product product, LocalDate newEstimatedEndOfStage);

    public Integer estimatedHowManyDaysLeftToEndOfProduction(Product product);

    public Product changeEstimatedEndOfActualStage(Product product, Integer daysToShift);


}
