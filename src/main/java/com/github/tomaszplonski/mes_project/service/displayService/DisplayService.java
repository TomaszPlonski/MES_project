package com.github.tomaszplonski.mes_project.service.displayService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.repository.OrderRepository;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.*;
import com.github.tomaszplonski.mes_project.service.entitiService.product.StagesOfProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisplayService implements DisplayServiceDefault {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StageExecutionRepository stageExecutionRepository;
    private final StagesOfProductService stagesOfProductService;

    @Transactional
    @Override
    public List<OrderShowAllPOJO> orderShowAll() {
        return buildShowAllPojo(orderRepository.findAll());
    }

    @Transactional
    @Override
    public List<OrderShowAllPOJO> orderShowInProgressOnly() {
        return buildShowAllPojo(orderRepository.findAllByOrderFinishedIsFalse());
    }

    @Transactional
    @Override
    public List<OrderShowAllPOJO> orderShowEndedOnly() {
        return buildShowAllPojo(orderRepository.findAllByOrderFinishedIsTrue());
    }

    @Transactional
    @Override
    public List<ProductsOfOrderPOJO> orderDetails(Long orderId){
        Order order = getOrderById(orderId);
        List<ProductsOfOrderPOJO> orderDetails = new ArrayList<>();
        List<Product> products = productRepository.findByOrder(order);


        products.forEach(p->orderDetails.add(ProductsOfOrderPOJO.builder()
                        .id(p.getId())
                        .productType(p.getProductType())
                        .delay(stagesOfProductService.getDelayOfProduction(p))
                        .plannedEndOfProduction(p.getPlannedEndOfProduction())
                        .duration(p.getDuration())
                        .predictedEndOfProduction()
                        .actualStageName(getActualPhase(p))
                        .status(p)
                        .build()));
        return orderDetails;
    }


    @Transactional
    @Override
    public StagesOfProductPOJO stagesOfProduct(Long productId){
        Product product = getProductById(productId);

       return StagesOfProductPOJO.builder()
                .id(product.getId())
                .orderId(product.getOrder().getId())
                .productType(product.getProductType().getProductType())
                .stagesDetailsPOJOS(product.getProductionMap().entrySet().stream()
                        .map(e->StagesDetailsPOJO.builder()
                                .phaseName(e.getKey().getName())
                                .actualStartOfStage(e.getValue().getActualStartOfStage())
                                .actualEndOfStage(e.getValue().getActualEndOfStage())
                                .duration(e.getValue().getDuration())
                                .delay()
                                .sequencePosition(e.getKey().getSequencePosition())
                                .build())
                        .sorted(Comparator.comparingInt(StagesDetailsPOJO::getSequencePosition))
                        .collect(Collectors.toList()))
                .build();


    }

    @Transactional
    @Override
    public ProductDetailsPOJO productDetails (Long productId){
        Product product = getProductById(productId);
        return ProductDetailsPOJO.builder()
                .productId(productId)
                .productType(product.getProductType().getProductType())
                .typeAttributeMap(product.getTypeAttributeMap())
                .orderId(product.getOrder().getId())
                .build();
    }

    @Transactional
    @Override
    public void endActiveStage(Long productId){
        if (stagesOfProductService.endActiveStage(productId)){
            Order order = productRepository.findById(productId)
                    .orElse(new Product())
                    .getOrder();

            if(productRepository.findByOrder(order)
                            .stream()
                            .allMatch(Product::getProductionFinished)) {
                order.setOrderFinished(true);
                orderRepository.save(order);
            }
        }
    }

    @Transactional
    @Override
    public Order getOrderById(Long orderID){
        return orderRepository.findById(orderID).orElse(new Order());
    }

    @Transactional
    @Override
    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElse(new Product());
    }

    @Transactional
    @Override
    public String getActualPhase(Product product){
        return product.getProductionMap().entrySet().stream()
                .filter(e-> Objects.equals(e.getValue(),product.getActiveStage()))
                .map(Map.Entry::getKey)
                .findAny()
                .orElse(new ProductionPhase()).getName();
    }

    @Transactional
    @Override
    public List<OrderShowAllPOJO> buildShowAllPojo(List<Order> orders){
        List<OrderShowAllPOJO> ordersShowAll = new ArrayList<>();

        orders.forEach(o->ordersShowAll.add(OrderShowAllPOJO.builder()
                .id(o.getId())
                .name(o.getName())
                .orderFinished(o.getOrderFinished())
                .orderValue(o.getOrderValue())
                .build()));

        return ordersShowAll;
    }


}
