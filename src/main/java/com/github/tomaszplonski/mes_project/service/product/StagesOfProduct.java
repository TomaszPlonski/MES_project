package com.github.tomaszplonski.mes_project.service.product;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import com.github.tomaszplonski.mes_project.service.product.production.StagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StagesOfProduct {

    private final ProductRepository productRepository;
    private final StagesService stagesService;
    private final StageExecutionRepository stageExecutionRepository;


    @Transactional
    public void productInitialization(Product product, StageExecution[] stages, LocalDate startOfProduction) {
        stages[0].setStartOfStage(startOfProduction);
        product.setActiveStageId(stageExecutionRepository.save(stages[0]).getId());
        productRepository.save(product);
        for (int i = 0; i < stages.length-1; i++) {
            stagesService.stageQueuing(stages[i],stages[i+1]);
        }
        //jeśli problem będzie na jsp dać każdemu stagowi parametr produkt to można to zrobić tutaj
    }


    @Transactional
    public Product endOfActualStage(Product product) {
        return null;
    }

    @Transactional
    public Product changeEstimatedEndOfActualStage(Product product, LocalDate newEstimatedEndOfStage) {
        return null;
    }

    @Transactional
    public Integer estimatedHowManyDaysLeftToEndOfProduction(Product product) {
        return null;
    }

    @Transactional
    public Product changeEstimatedEndOfActualStage(Product product, Integer daysToShift) {
        return null;
    }
}
