package com.github.tomaszplonski.mes_project.controller;


import com.github.tomaszplonski.mes_project.model.PhaseExecutor;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.repository.*;
import com.github.tomaszplonski.mes_project.service.product.production.StagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;


@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final ProductRepository productRepository;
    private final PhaseExecutorRepository phaseExecutorRepository;
    private final ProductionPhaseRepository productionPhaseRepository;
    private final StagesService stagesService;


    @GetMapping("/test")
    public void test(){
        Product chair = productRepository.getById(1L);
        PhaseExecutor phaseExecutor = phaseExecutorRepository.getById(1L);
        ProductionPhase productionPhase = productionPhaseRepository.getById(2L);

        log.info(stagesService.StageInitialization(chair,productionPhase,phaseExecutor,productionPhase.getDefaultDuration(),LocalDate.now()).toString());
        log.info(stagesService.EndOfStage(1L).toString());

    }
}
