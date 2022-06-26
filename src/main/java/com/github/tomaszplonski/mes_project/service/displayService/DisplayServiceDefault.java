package com.github.tomaszplonski.mes_project.service.displayService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.service.displayService.displayDto.OrderShowAllDto;
import com.github.tomaszplonski.mes_project.service.displayService.displayDto.ProductDetailsDto;
import com.github.tomaszplonski.mes_project.service.displayService.displayDto.ProductsOfOrderDto;
import com.github.tomaszplonski.mes_project.service.displayService.displayDto.StagesOfProductDto;

import java.util.List;

public interface DisplayServiceDefault {

    List<OrderShowAllDto> orderShowAll();

    List<OrderShowAllDto> orderShowInProgressOnly();

    List<OrderShowAllDto> orderShowEndedOnly();

    List<ProductsOfOrderDto> orderDetails(Long orderId);

    StagesOfProductDto stagesOfProduct(Long productId);

    ProductDetailsDto productDetails (Long productId);

    void endActiveStage(Long productId);

    Order getOrderById(Long orderID);

    Product getProductById(Long productId);

    String getActualPhase(Product product);

    List<OrderShowAllDto> buildShowAllPojo(List<Order> orders);


}
