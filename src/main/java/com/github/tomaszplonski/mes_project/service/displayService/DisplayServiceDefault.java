package com.github.tomaszplonski.mes_project.service.displayService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.OrderShowAllPOJO;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.ProductDetailsPOJO;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.ProductsOfOrderPOJO;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.StagesOfProductPOJO;

import java.util.List;

public interface DisplayServiceDefault {

    List<OrderShowAllPOJO> orderShowAll();

    List<OrderShowAllPOJO> orderShowInProgressOnly();

    List<OrderShowAllPOJO> orderShowEndedOnly();

    List<ProductsOfOrderPOJO> orderDetails(Long orderId);

    StagesOfProductPOJO stagesOfProduct(Long productId);

    ProductDetailsPOJO productDetails (Long productId);

    void endActiveStage(Long productId);

    Order getOrderById(Long orderID);

    Product getProductById(Long productId);

    String getActualPhase(Product product);

    List<OrderShowAllPOJO> buildShowAllPojo(List<Order> orders);


}
