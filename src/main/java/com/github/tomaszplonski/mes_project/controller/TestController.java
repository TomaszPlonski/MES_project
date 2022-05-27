package com.github.tomaszplonski.mes_project.controller;


import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.*;
import com.github.tomaszplonski.mes_project.service.product.StagesOfProduct;
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
    private final ProductionPhaseRepository productionPhaseRepository;
    private final StagesService stagesService;
    private final StagesOfProduct stagesOfProduct;



    @GetMapping("/test")
    public void test(){
        Product chair = productRepository.getById(1L);
        ProductionPhase productionPhase = productionPhaseRepository.getById(2L);

        StageExecution stage1 = new StageExecution();
        StageExecution stage2 = new StageExecution();
        StageExecution stage3 = new StageExecution();
        stage1.setDuration(2);
        stage2.setDuration(3);
        stage3.setDuration(4);

        stage1.setProduct(chair);
        stage2.setProduct(chair);
        stage3.setProduct(chair);

        StageExecution[] stages = {stage1,stage2,stage3};

        stagesOfProduct.productInitialization(chair,stages, LocalDate.now());

    }
}
