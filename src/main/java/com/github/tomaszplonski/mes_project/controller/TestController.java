package com.github.tomaszplonski.mes_project.controller;


import com.github.tomaszplonski.mes_project.repository.*;
import com.github.tomaszplonski.mes_project.service.order.product.StagesOfProductService;
import com.github.tomaszplonski.mes_project.service.order.product.production.StagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final ProductRepository productRepository;
    private final ProductionPhaseRepository productionPhaseRepository;
    private final StagesService stagesService;
    private final StagesOfProductService stagesOfProductService;
    private final StageExecutionRepository stageExecutionRepository;
    private final OrderRepository orderRepository;



    @GetMapping("/test")
    public void test(){
        log.info(stageExecutionRepository.findProductionPhaseNameById(1L).get());
//        Product product = productRepository.getById(1L);
//        List<StageExecution> stageExecutions = stageExecutionRepository.findByProduct(product);
//        log.info(stageExecutions.get(0).getEstimatedEndOfStage().toString());







    }
}
