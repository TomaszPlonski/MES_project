package com.github.tomaszplonski.mes_project.service.formService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.service.formService.formDto.OrderFormDto;
import com.github.tomaszplonski.mes_project.service.formService.formDto.ProductFormDto;
import com.github.tomaszplonski.mes_project.service.formService.formDto.TypeFormDto;

import java.util.List;

public interface FormService {

    void createType(TypeFormDto typeFormDto);

    void saveOrder(OrderFormDto order);

    void saveProduct(ProductFormDto productFormDto, Order order);

    List<ProductType> getAllTypes();

    void addNewProductDto(OrderFormDto order, Long newProductType);

    void attributePutInMap(OrderFormDto order, String value);
}
