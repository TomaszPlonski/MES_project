package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.service.displayService.DisplayService;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.ProductDetailsPOJO;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.StagesOfProductPOJO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final DisplayService displayService;

    @GetMapping("/stages/{id}")
    public String showStagesOfProduct(@PathVariable long id, Model model){
        StagesOfProductPOJO stages = displayService.stagesOfProduct(id);
        if(stages.getId()==null){
            return "404";
        }
        model.addAttribute("stages",stages);

        return "product/product-show-stages";
    }

    @GetMapping("/details/{id}")
    public String showProductDetails(@PathVariable long id, Model model){
        ProductDetailsPOJO details = displayService.productDetails(id);
        if(details.getOrderId()==null || details.getProductId()==null){
            return "404";
        }

        model.addAttribute("details",details);

        return "product/product-show-details";
    }

    @PostMapping(path = "/stages/end/active", params = "productId")
    public String endActualStage(@RequestParam("productId") Long productId){
        displayService.endActiveStage(productId);
        return "redirect:/product/stages/" + productId;
    }

}
