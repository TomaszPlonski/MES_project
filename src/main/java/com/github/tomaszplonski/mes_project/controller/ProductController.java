package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.service.displayService.DisplayService;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.ProductDetailsPOJO;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.ProductsOfOrderPOJO;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.StagesOfProductPOJO;
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

    @GetMapping("/stages/{id}")
    public String showStagesOfProduct(@PathVariable long id, Model model){
        ProductsOfOrderPOJO product = displayService.productDetailsGeneral(id);
        List<StagesOfProductPOJO> stages = displayService.stagesOfProduct(id);

        model.addAttribute("product",product);
        model.addAttribute("stages",stages);

        return "product-show-stages";
    }

    @GetMapping("/details/{id}")
    public String showProductDetails(@PathVariable long id, Model model){
        ProductDetailsPOJO details = displayService.productDetails(id);

        model.addAttribute("details",details);

        return "product-show-details";
    }

}
