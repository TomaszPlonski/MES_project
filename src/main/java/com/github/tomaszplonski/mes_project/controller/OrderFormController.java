package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.service.formService.FormService;
import com.github.tomaszplonski.mes_project.service.formService.formDto.OrderFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order/add")
@RequiredArgsConstructor
@SessionAttributes("order")
public class OrderFormController {

    private final FormService formService;

    @ModelAttribute("types")
    public List<ProductType> types(){
        return formService.getAllTypes();
    }


    @GetMapping()
    public String prepareView(Model model){
        model.addAttribute("order",new OrderFormDto());
        return "order/order-create";
    }

    @PostMapping(params = "addProduct")
    public String showAttributesOfType(@ModelAttribute("order") OrderFormDto order,
                                       @RequestParam(name="newProductType") Long newProductType){
        formService.addNewProductDto(order,newProductType);
        return "order/order-create";
    }

    @PostMapping(params = "addValue")
    public String addValue(@ModelAttribute("order") OrderFormDto order,
                           @RequestParam(name="value") String value, Model model){
        formService.attributePutInMap(order,value);
        return "order/order-create";
    }

    @PostMapping(params = "removeProduct")
    public String removeProduct(@ModelAttribute("order") OrderFormDto order, @RequestParam int removeProduct){
        order.getProducts().remove(removeProduct);
        return "order/order-create";
    }

    @PostMapping(params = "createOrder")
    public String createOrder(@Valid @ModelAttribute("order") OrderFormDto order, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "order/order-create";
        }

        formService.saveOrder(order);
        return "redirect:/";
    }
}
