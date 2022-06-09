package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.ProductsOfOrderPOJO;
import com.github.tomaszplonski.mes_project.service.displayService.DisplayService;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.OrderShowAllPOJO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes({"inProgressOrders" , "endedOrders"})
public class OrderController {

    private final DisplayService displayService;

    @GetMapping("/")
    public String showAllOrders(Model model){
        List<OrderShowAllPOJO> orderShowAll = displayService.orderShowAll();
        model.addAttribute("orders",orderShowAll);
        if(!model.containsAttribute("inProgressOrders") && !model.containsAttribute("endedOrders")){
            model.addAttribute("inProgressOrders","checked=\"\"");
            model.addAttribute("endedOrders","checked=\"\"");
        }

        return "order-all";
    }

    @GetMapping("/order/get/{id}")
    public String showOrderDetails(@PathVariable long id, Model model){
        Order order = displayService.getOrderById(id);
        if(order.getId()==null){
            return "404";
        }
        List<ProductsOfOrderPOJO> orderDetails = displayService.orderDetails(id);

        model.addAttribute("order",order);
        model.addAttribute("orderDetails",orderDetails);

        return "order-show-details";
    }

    @PostMapping(params = {"inProgressOrders", "endedOrders"})
    public String viewAllOrders(@RequestParam String inProgressOrders, @RequestParam String endedOrders, Model model){
        model.addAttribute("inProgressOrders","checked=\"\"");
        model.addAttribute("endedOrders","checked=\"\"");
        return "redirect:/";
    }

    @PostMapping(params = "inProgressOrders")
    public String viewOnlyInProgressOrders(@RequestParam String inProgressOrders,Model model){
        List<OrderShowAllPOJO> orderShowInProgressOnly = displayService.orderShowInProgressOnly();
        model.addAttribute("orders",orderShowInProgressOnly);
        model.addAttribute("inProgressOrders",inProgressOrders);
        model.addAttribute("endedOrders","");
        return "order-all";
    }

    @PostMapping(params = "endedOrders")
    public String viewEndedOrders(@RequestParam String endedOrders, Model model){
        List<OrderShowAllPOJO> orderShowEndedOnly = displayService.orderShowEndedOnly();
        model.addAttribute("orders",orderShowEndedOnly);
        model.addAttribute("endedOrders",endedOrders);
        model.addAttribute("inProgressOrders","");
        return "order-all";
    }

    @PostMapping()
    public String noParams(Model model){
        model.addAttribute("endedOrders","");
        model.addAttribute("inProgressOrders","");
        return "order-all";
    }

}
