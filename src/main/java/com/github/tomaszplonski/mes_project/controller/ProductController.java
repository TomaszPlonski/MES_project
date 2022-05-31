package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.service.displayService.DisplayService;
import com.github.tomaszplonski.mes_project.service.displayService.ProductDetailsObject;
import com.github.tomaszplonski.mes_project.service.displayService.StagesOfProductObject;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final DisplayService displayService;

    @GetMapping("/get/{id}")
    public String showOrderDetails(@PathVariable long id, Model model){
        ProductDetailsObject product = displayService.productDetailsGeneral(id);
        List<StagesOfProductObject> stages = displayService.productDetailsStages(id);

        model.addAttribute("product",product);
        model.addAttribute("stages",stages);

        return "product-show-details";
    }

}
