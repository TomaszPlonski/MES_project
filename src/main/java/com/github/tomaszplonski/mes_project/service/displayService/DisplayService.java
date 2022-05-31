package com.github.tomaszplonski.mes_project.service.displayService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.OrderRepository;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import com.github.tomaszplonski.mes_project.service.order.product.StagesOfProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisplayService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StageExecutionRepository stageExecutionRepository;
    private final StagesOfProductService stagesOfProductService;

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

    @Transactional
    public List<ProductDetailsObject> orderDetails(Long orderId){
        Order order = getOrderById(orderId);
        List<ProductDetailsObject> orderDetails = new ArrayList<>();
        List<Product> products = productRepository.findByOrder(order);


        products.forEach(p->orderDetails.add(ProductDetailsObject.builder()
                        .productType(p.getType())
                        .delay(stagesOfProductService.getDelayOfProduction(p))
                        .plannedEndOfProduction(p.getPlannedEndOfProduction())
                        .predictedEndOfProduction()
                        .actualStageName(p.getActiveStage().getProductionPhase().getName())
                        .status(p)
                        .build()));
        return orderDetails;
    }

    @Transactional
    public ProductDetailsObject productDetailsGeneral(Long productId){
        Product product = getProductById(productId);

        return ProductDetailsObject.builder()
                .productType(product.getType())
                .delay(stagesOfProductService.getDelayOfProduction(product))
                .plannedEndOfProduction(product.getPlannedEndOfProduction())
                .predictedEndOfProduction()
                .actualStageName(product.getActiveStage().getProductionPhase().getName())
                .status(product)
                .build();

    }


    @Transactional
    public List<StagesOfProductObject> productDetailsStages(Long productId){
        Product product = getProductById(productId);
        List<StagesOfProductObject> productDetails = new ArrayList<>();
        List<StageExecution> stages = stageExecutionRepository.findByProduct(product);

        stages.forEach(s->productDetails.add(StagesOfProductObject.builder()
                        .productionPhaseName(s.getProductionPhase().getName())
                        .duration(s.getDuration())
                        .actualStartOfStage(s.getActualStartOfStage())
                        .actualEndOfStage(s.getActualEndOfStage())
                        .delay()
                        .build()));

        return productDetails;
    }


    public Order getOrderById(Long orderID){
        return orderRepository.findById(orderID).orElse(new Order());
    }

    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElse(new Product());
    }

}
