package com.github.tomaszplonski.mes_project.service.displayService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.OrderRepository;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.repository.StageExecutionRepository;
import com.github.tomaszplonski.mes_project.service.displayService.displayPOJO.*;
import com.github.tomaszplonski.mes_project.service.entitiService.product.StagesOfProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisplayService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StageExecutionRepository stageExecutionRepository;
    private final StagesOfProductService stagesOfProductService;

    @Transactional
    public List<OrderShowAllPOJO> orderShowAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderShowAllPOJO> ordersShowAll = new ArrayList<>();

        orders.forEach(o->ordersShowAll.add(OrderShowAllPOJO.builder()
                        .id(o.getId())
                        .name(o.getName())
                        .orderValue(o.getOrderValue())
                        .build()));

        return ordersShowAll;
    }

    @Transactional
    public List<ProductsOfOrderPOJO> orderDetails(Long orderId){
        Order order = getOrderById(orderId);
        List<ProductsOfOrderPOJO> orderDetails = new ArrayList<>();
        List<Product> products = productRepository.findByOrder(order);


        products.forEach(p->orderDetails.add(ProductsOfOrderPOJO.builder()
                        .id(p.getId())
                        .productType(p.getProductType())
                        .delay(stagesOfProductService.getDelayOfProduction(p))
                        .plannedEndOfProduction(p.getPlannedEndOfProduction())
                        .predictedEndOfProduction()
                        .actualStageName(getActualPhase(p))
                        .status(p)
                        .build()));
        return orderDetails;
    }

    @Transactional
    public ProductsOfOrderPOJO productDetailsGeneral(Long productId){
        Product product = getProductById(productId);

        return ProductsOfOrderPOJO.builder()
                .productType(product.getProductType())
                .delay(stagesOfProductService.getDelayOfProduction(product))
                .plannedEndOfProduction(product.getPlannedEndOfProduction())
                .predictedEndOfProduction()
                .actualStageName(getActualPhase(product))
                .status(product)
                .build();

    }


    @Transactional
    public StagesOfProductPOJO stagesOfProduct(Long productId){
        Product product = getProductById(productId);

       return StagesOfProductPOJO.builder()
                .id(product.getId())
                .productType(product.getProductType().getProductType())
                .stagesDetailsPOJOS(product.getProductionMap().entrySet().stream()
                        .map(e->StagesDetailsPOJO.builder()
                                .phaseName(e.getKey().getName())
                                .actualStartOfStage(e.getValue().getActualStartOfStage())
                                .actualEndOfStage(e.getValue().getActualEndOfStage())
                                .duration(e.getValue().getDuration())
                                .delay()
                                .sequencePosition(e.getValue().getSequencePosition())
                                .build())
                        .sorted(Comparator.comparingInt(StagesDetailsPOJO::getSequencePosition))
                        .collect(Collectors.toList()))
                .build();


    }

    @Transactional
    public ProductDetailsPOJO productDetails (Long productId){
        Product product = getProductById(productId);
        return ProductDetailsPOJO.builder()
                .productId(productId)
                .productType(product.getProductType().getProductType())
                .typeAttributeMap(product.getTypeAttributeMap())
                .build();
    }


    public Order getOrderById(Long orderID){
        return orderRepository.findById(orderID).orElse(new Order());
    }

    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElse(new Product());
    }

    public String getActualPhase(Product product){
        return product.getProductionMap().entrySet().stream()
                .filter(e-> Objects.equals(e.getValue(),product.getActiveStage()))
                .map(Map.Entry::getKey)
                .findAny()
                .get().getName();
    }

    public Integer getStageDelay(StageExecution stage){
        if(stage.getActualEndOfStage()==null || stage.getActualStartOfStage()==null){
            return null;
        } else {
            return  Math.toIntExact(ChronoUnit.DAYS.between(stage.getActualStartOfStage(), stage.getActualEndOfStage())) - stage.getDuration();
        }
    }

}
