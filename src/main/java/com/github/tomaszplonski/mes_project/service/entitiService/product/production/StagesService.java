package com.github.tomaszplonski.mes_project.service.entitiService.product.production;

import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class StagesService {

    private final StageExecutionRepository stageExecutionRepository;


//    @Transactional
//    public StageExecution StageInitialization(Product product, ProductionPhase productionPhase, int duration, LocalDate startOfStage) {
//        return stageExecutionRepository.save(StageExecution.builder()
//                .product(product)
//                .productionPhase(productionPhase)
//                .duration(duration)
//                .build());
//    }

    @Transactional
    public StageExecution endOfStage(StageExecution endedStage) {
        final StageExecution[] updatedStage = {null};

        stageExecutionRepository.findById(endedStage.getId()).ifPresent(s->{
            s.setActualEndOfStage(LocalDate.now());
            updatedStage[0] = stageExecutionRepository.save(s);
        });
        return updatedStage[0];
    }

    @Transactional
    public StageExecution changeEstimatedEndOfStage(StageExecution stage, LocalDate newEstimatedEndOfStage) {
        final StageExecution[] updatedStage = {null};

        stageExecutionRepository.findById(stage.getId()).ifPresent(s->{
            s.setEstimatedEndOfStage(newEstimatedEndOfStage);
            updatedStage[0] = stageExecutionRepository.save(s);
        });
        return updatedStage[0];
    }


    @Transactional
    public Integer howManyDaysLeftToEstimatedEndOfStage(StageExecution stage) {
        final Integer[] shift = {null};

        stageExecutionRepository.findById(stage.getId()).ifPresent(s->
            shift[0] = Math.toIntExact(
                    ChronoUnit.DAYS.between(LocalDate.now(),
                            stageExecutionRepository.save(s).getEstimatedEndOfStage())));
        return shift[0];
    }

    @Transactional
    public StageExecution changeEstimatedEndOfStage(StageExecution stage, Integer daysToShift) {
        final StageExecution[] updatedStage = {null};

        stageExecutionRepository.findById(stage.getId()).ifPresent(s->{
            s.setEstimatedEndOfStage(s.getEstimatedEndOfStage()
                    .plusDays(daysToShift));
            updatedStage[0] = stageExecutionRepository.save(s);
                });

        return updatedStage[0];
    }

    @Transactional
    public StageExecution changeNoActiveEstimatedEndOfStage(StageExecution stage, StageExecution previousStage) {
        final StageExecution[] updatedStage = {null};

        stageExecutionRepository.findById(stage.getId()).ifPresent(s->
                stageExecutionRepository.findById(previousStage.getId()).ifPresent(ps->{
                    if(ps.getActualEndOfStage()==null){
                        s.setEstimatedStartOfStage(ps.getEstimatedEndOfStage());
                    }
                    else {
                        s.setEstimatedEndOfStage(ps.getActualEndOfStage());
                    }
                    s.prePersist();
                    updatedStage[0] = stageExecutionRepository.save(s);
                }));
        return updatedStage[0];
    }

    @Transactional
    public StageExecution stageQueuing(StageExecution previousStage, StageExecution nextStage){
        nextStage.setEstimatedStartOfStage(previousStage.getEstimatedEndOfStage());
        previousStage.setNextStage(stageExecutionRepository.save(nextStage));
        stageExecutionRepository.save(previousStage);
        return nextStage;
    }
}
