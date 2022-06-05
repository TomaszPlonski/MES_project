package com.github.tomaszplonski.mes_project.service.entitiService.product;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.repository.ProductionPhaseRepository;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import com.github.tomaszplonski.mes_project.service.entitiService.product.production.StagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StagesOfProductService {

    private final ProductRepository productRepository;
    private final StagesService stagesService;
    private final StageExecutionRepository stageExecutionRepository;
    private final ProductionPhaseRepository productionPhaseRepository;


    @Transactional
    public Map<ProductionPhase,StageExecution> stageInitialization(ProductType type) {
        Map<ProductionPhase,StageExecution> map = new LinkedHashMap<>();

        List<ProductionPhase> phases = productionPhaseRepository.findAllByProductType(type);
        phases.sort(Comparator.comparingInt(ProductionPhase::getSequencePosition));

        List<StageExecution> stagesTemp = new ArrayList<>();
        List<StageExecution> stages = new ArrayList<>();

        phases.forEach(p->stagesTemp.add(StageExecution.builder()
                .duration(p.getDefaultDuration())
                .build()));



        LocalDate start = LocalDate.now();
        stagesTemp.get(0).setActualStartOfStage(start);

        for (StageExecution stageExecution : stagesTemp) {
            start = initialEstimatedEndOfStage(stageExecution, start).getEstimatedEndOfStage();
            stages.add(stageExecution);
        }

        for (int i = 0; i < phases.size(); i++) {
            map.put(phases.get(i),stages.get(i));
        }
        return map;

    }

    @Transactional
    public Integer getDelayOfProduction(Product product){
        if(product.getActiveStage().getEstimatedEndOfStage().isBefore(LocalDate.now())){
            return Math.toIntExact(ChronoUnit.DAYS.between(product.getActiveStage().getEstimatedEndOfStage(),LocalDate.now()));
        } else {
            return Math.toIntExact(ChronoUnit.DAYS.between(product.getActiveStage().getEstimatedStartOfStage(), product.getActiveStage().getActualStartOfStage()));
        }
    }

    @Transactional
    public StageExecution initialEstimatedEndOfStage(StageExecution actualStage, LocalDate estimatedStart){
        actualStage.setEstimatedStartOfStage(estimatedStart);
        return stageExecutionRepository.save(actualStage);
    }



}
