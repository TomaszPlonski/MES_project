package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.service.OrderShowAll;
import com.github.tomaszplonski.mes_project.service.OrderShowAllService;
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

    private final OrderShowAllService orderShowAllService;

    @GetMapping("/all")
    public String showAllOrders(Model model){
        List<OrderShowAll> testList = orderShowAllService.orderShowAll();
        testList.stream()
                .forEach(l->log.info("==============\n" + l.getName() +"\n"
                        + l.getOrderHandlerName() +"\n"
                        + l.getOrderValue() +"\n"
                        + l.getPurchaserName() +"\n" +
                        l.isProductionEnded()));


        model.addAttribute("orders",testList);
        return "order-show-all";
    }

}
