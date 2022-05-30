package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.service.order.OrderDetailsObject;
import com.github.tomaszplonski.mes_project.service.order.OrderDisplayService;
import com.github.tomaszplonski.mes_project.service.order.OrderShowAllObject;
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

    private final OrderDisplayService orderDisplayService;

    @GetMapping("/all")
    public String showAllOrders(Model model){
        List<OrderShowAllObject> orderShowAll = orderDisplayService.orderShowAll();
        model.addAttribute("orders",orderShowAll);
        return "order-show-all";
    }

    @GetMapping("/get/{id}")
    public String showOrderDetails(@PathVariable long id, Model model){
        Order order = orderDisplayService.getOrderById(id);
        List<OrderDetailsObject> orderDetails = orderDisplayService.orderDetails(id);

        model.addAttribute("order",order);
        model.addAttribute("orderDetails",orderDetails);

        return "order-show-details";
    }

}
