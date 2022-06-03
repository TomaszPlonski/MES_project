package com.github.tomaszplonski.mes_project.service.entitiService.product;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import com.github.tomaszplonski.mes_project.service.entitiService.product.production.StagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class StagesOfProductService {

    private final ProductRepository productRepository;
    private final StagesService stagesService;
    private final StageExecutionRepository stageExecutionRepository;


    @Transactional
    public void productInitialization(Product product, StageExecution[] stages, LocalDate startOfProduction) {
        stages[0].setEstimatedStartOfStage(startOfProduction);
        product.setActiveStage(stageExecutionRepository.save(stages[0]));
        productRepository.save(product);
        for (int i = 0; i < stages.length-1; i++) {
            stagesService.stageQueuing(stages[i],stages[i+1]);
        }
        //jeśli problem będzie na jsp dać każdemu stagowi parametr produkt to można to zrobić tutaj
    }

    @Transactional
    public Integer getDelayOfProduction(Product product){
        if(product.getActiveStage().getEstimatedEndOfStage().isBefore(LocalDate.now())){
            return Math.toIntExact(ChronoUnit.DAYS.between(product.getActiveStage().getEstimatedEndOfStage(),LocalDate.now()));
        } else {
            return Math.toIntExact(ChronoUnit.DAYS.between(product.getActiveStage().getEstimatedStartOfStage(), product.getActiveStage().getActualStartOfStage()));
        }
    }


}
