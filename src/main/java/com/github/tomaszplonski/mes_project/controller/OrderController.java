package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.service.order.OrderDisplayService;
import com.github.tomaszplonski.mes_project.service.order.OrderShowAllObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDisplayService orderDisplayService;

    @GetMapping("/all")
    public String showAllOrders(Model model){
        List<OrderShowAllObject> orderShowAll = orderDisplayService.orderShowAll();
        model.addAttribute("orders",orderShowAll);
        return "order-show-all";
    }

}
