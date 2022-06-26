package com.github.tomaszplonski.mes_project.service.entitiService.product;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.repository.ProductionPhaseRepository;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import com.github.tomaszplonski.mes_project.utils.DaysBetween;
import com.github.tomaszplonski.mes_project.utils.WorkingDays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StagesOfProductServiceImpl implements StagesOfProductService {

    private final ProductRepository productRepository;
    private final StageExecutionRepository stageExecutionRepository;
    private final ProductionPhaseRepository productionPhaseRepository;


    @Transactional
    @Override
    public Map<ProductionPhase,StageExecution> stageInitialization(ProductType type) {
        Map<ProductionPhase,StageExecution> map = new LinkedHashMap<>();

        List<ProductionPhase> phases = productionPhaseRepository.findAllByProductType(type);
        phases.sort(Comparator.comparingInt(ProductionPhase::getSequencePosition));

        List<StageExecution> stagesTemp = new ArrayList<>();
        List<StageExecution> stages = new ArrayList<>();

        phases.forEach(p->stagesTemp.add(StageExecution.builder()
                .duration(p.getDefaultDuration())
                .build()));



        LocalDate start = LocalDate.now().with(WorkingDays.addWorkingDays(0));
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
    @Override
    public Integer getDelayOfProduction(Product product){
        if(product.getProductionFinished()){
            return DaysBetween.daysBetween(product.getPlannedEndOfProduction(),
                    product.getActiveStage().getActualEndOfStage());

        }
        if(!product.getActiveStage().getEstimatedEndOfStage().isBefore(LocalDate.now())){
            return DaysBetween.daysBetween(product.getActiveStage().getEstimatedStartOfStage(),
                    product.getActiveStage().getActualStartOfStage());

        } else {
            return DaysBetween.daysBetween(product.getActiveStage().getEstimatedEndOfStage(),
                    LocalDate.now().with(WorkingDays.addWorkingDays(0)));

        }
    }

    @Transactional
    @Override
    public StageExecution initialEstimatedEndOfStage(StageExecution actualStage, LocalDate estimatedStart){
        actualStage.setEstimatedStartOfStage(estimatedStart);
        return stageExecutionRepository.save(actualStage);
    }

    @Transactional
    @Override
    public Boolean endActiveStage(Long productId) {
        Product product = productRepository.findById(productId).orElse(new Product());
        Map<ProductionPhase, StageExecution> productionMap = product.getProductionMap();

        List<StageExecution> nonEndedStages = productionMap.entrySet().stream()
                .sorted(Comparator.comparingInt(a -> a.getKey().getSequencePosition()))
                .filter(s->s.getValue().getActualEndOfStage()==null)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        LocalDate endOfStage = LocalDate.now().with(WorkingDays.addWorkingDays(0));
        nonEndedStages.get(0).setActualEndOfStage(endOfStage);
        stageExecutionRepository.save(nonEndedStages.get(0));
        if(nonEndedStages.size()==1){
            product.setProductionFinished(true);
        }
        if (nonEndedStages.size() > 1) {
            StageExecution secondStage = nonEndedStages.get(1);
            secondStage.setActualStartOfStage(endOfStage);

            product.setActiveStage(secondStage);
            stageExecutionRepository.save(secondStage);
        }
        productRepository.save(product);
        return product.getProductionFinished();

    }

}
