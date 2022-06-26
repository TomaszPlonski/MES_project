package com.github.tomaszplonski.mes_project.service.formService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.service.formService.formDto.OrderFormDto;
import com.github.tomaszplonski.mes_project.service.formService.formDto.ProductFormDto;
import com.github.tomaszplonski.mes_project.service.formService.formDto.TypeFormDto;

public interface FormServiceDefault {

    void createType(TypeFormDto typeFormDto);

    void saveOrder(OrderFormDto order);

    void saveProduct(ProductFormDto productFormDto, Order order);
}
