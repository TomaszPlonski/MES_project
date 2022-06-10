package com.github.tomaszplonski.mes_project.service.formService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.OrderFormPOJO;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.ProductFormPOJO;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.TypeFormPOJO;

public interface FormServiceDefault {

    void createType(TypeFormPOJO typeFormPOJO);

    void saveOrder(OrderFormPOJO order);

    void saveProduct(ProductFormPOJO productFormPOJO, Order order);
}
