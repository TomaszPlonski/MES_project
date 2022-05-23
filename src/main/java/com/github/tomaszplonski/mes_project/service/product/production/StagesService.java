package com.github.tomaszplonski.mes_project.service.product.production;

import com.github.tomaszplonski.mes_project.model.PhaseExecutor;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class StagesService implements DefaultStagesService {

    private final StageExecutionRepository stageExecutionRepository;


    @Override
    @Transactional
    public LocalDate StageInitialization(Product product, ProductionPhase productionPhase, PhaseExecutor phaseExecutor, int duration, LocalDate stratOfStage) {
        return stageExecutionRepository.save(StageExecution.builder()
                .product(product)
                .productionPhase(productionPhase)
                .phaseExecutor(phaseExecutor)
                .duration(duration)
                .starOfStage(stratOfStage)
                .build())
                .getEstimatedEndOfStage();
    }

    @Override
    @Transactional
    public Integer EndOfStage(Long stageId) {
        final Integer[] shift = {null};

        stageExecutionRepository.findById(stageId).ifPresent(p->{
            p.setActualEndOfStage(LocalDate.now());
            shift[0] = Math.toIntExact(
                   ChronoUnit.DAYS.between(
                           stageExecutionRepository.save(p).getEstimatedEndOfStage(),
                           LocalDate.now()));
        });
        return shift[0];
    }

    @Override
    @Transactional
    public Integer changeEstimatedEndOfStage(Long stageId, LocalDate newEstimatedEndOfStage) {
        final Integer[] shift = {null};

        stageExecutionRepository.findById(stageId).ifPresent(p->{
            p.setEstimatedEndOfStage(newEstimatedEndOfStage);
            shift[0] = Math.toIntExact(
                    ChronoUnit.DAYS.between(
                            stageExecutionRepository.save(p).getEstimatedEndOfStage(),
                            newEstimatedEndOfStage));
        });
        return shift[0];
    }


    @Override
    @Transactional
    public Integer howManyDaysLeftToEstimatedEndOfStage(Long stageId) {
        final Integer[] shift = {null};

        stageExecutionRepository.findById(stageId).ifPresent(p->
            shift[0] = Math.toIntExact(
                    ChronoUnit.DAYS.between(LocalDate.now(),
                            stageExecutionRepository.save(p).getEstimatedEndOfStage())));
        return shift[0];
    }
}
