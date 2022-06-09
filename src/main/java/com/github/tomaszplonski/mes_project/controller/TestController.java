package com.github.tomaszplonski.mes_project.controller;


import com.github.tomaszplonski.mes_project.model.UserEntity;
import com.github.tomaszplonski.mes_project.repository.*;
import com.github.tomaszplonski.mes_project.service.entitiService.product.StagesOfProductService;
import com.github.tomaszplonski.mes_project.service.securityService.UserService;
import com.github.tomaszplonski.mes_project.utils.DaysBetween;
import com.github.tomaszplonski.mes_project.utils.WorkingDays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final ProductRepository productRepository;
    private final ProductionPhaseRepository productionPhaseRepository;
    private final StagesOfProductService stagesOfProductService;
    private final StageExecutionRepository stageExecutionRepository;
    private final OrderRepository orderRepository;

    private final UserService userService;



    @GetMapping("/test")
    public void test(){

//        UserEntity user = new UserEntity();
//        user.setUsername("office");
//        user.setPassword("office");
//        userService.saveUser(user);


    }
}
