package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.ProductsOfOrderPOJO;
import com.github.tomaszplonski.mes_project.service.displayService.DisplayService;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.OrderShowAllPOJO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final DisplayService displayService;

    @GetMapping("/all")
    public String showAllOrders(Model model){
        List<OrderShowAllPOJO> orderShowAll = displayService.orderShowAll();
        model.addAttribute("orders",orderShowAll);
        return "order-show-all";
    }

    @GetMapping("/get/{id}")
    public String showOrderDetails(@PathVariable long id, Model model){
        Order order = displayService.getOrderById(id);
        List<ProductsOfOrderPOJO> orderDetails = displayService.orderDetails(id);

        model.addAttribute("order",order);
        model.addAttribute("orderDetails",orderDetails);

        return "order-show-details";
    }

}
