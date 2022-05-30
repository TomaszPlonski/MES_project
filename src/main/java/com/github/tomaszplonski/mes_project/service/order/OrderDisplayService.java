package com.github.tomaszplonski.mes_project.service.order;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.repository.OrderRepository;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import com.github.tomaszplonski.mes_project.service.order.product.StagesOfProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDisplayService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StageExecutionRepository stageExecutionRepository;
    private final StagesOfProduct stagesOfProduct;

    @Transactional
    public List<OrderShowAllObject> orderShowAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderShowAllObject> ordersShowAll = new ArrayList<>();

        orders.forEach(o->ordersShowAll.add(OrderShowAllObject.builder()
                        .id(o.getId())
                        .name(o.getName())
                        .orderValue(o.getOrderValue())
                        .build()));

        return ordersShowAll;
    }


    public List<OrderDetailsObject> orderDetails(Long orderId){
        Order order = getOrderById(orderId);
        List<OrderDetailsObject> orderDetails = new ArrayList<>();
        List<Product> products = productRepository.findByOrder(order);


        products.forEach(p->orderDetails.add(OrderDetailsObject.builder()
                        .productType(p.getType())
                        .delay(stagesOfProduct.getDelayOfProduction(p))
                        .initialEndOfProduction(p.getInitialEndOfProduction())
                        .predictedEndOfProduction()
                        .actualStageName(p.getActiveStage().getProductionPhase().getName())
                        .status(p)
                        .build()));
        return orderDetails;
    }

    public Order getOrderById(Long orderID){
        return orderRepository.findById(orderID).orElse(new Order());
    }

    public Long getIdOfLastStage(Product product){
      return product.getStageExecution().get(product.getStageExecution().size()-1).getId();
    }

}
