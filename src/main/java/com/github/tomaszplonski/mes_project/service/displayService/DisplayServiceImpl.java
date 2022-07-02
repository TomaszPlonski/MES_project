package com.github.tomaszplonski.mes_project.service.displayService;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.repository.OrderRepository;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import com.github.tomaszplonski.mes_project.service.displayService.displayDto.*;
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
public class DisplayServiceImpl implements DisplayService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StagesOfProductService stagesOfProductService;

    @Override
    public List<OrderShowAllDto> orderShowAll() {
        return buildShowAllDto(orderRepository.findAll());
    }

    @Override
    public List<OrderShowAllDto> orderShowInProgressOnly() {
        return buildShowAllDto(orderRepository.findAllByOrderFinishedIsFalse());
    }

    @Override
    public List<OrderShowAllDto> orderShowEndedOnly() {
        return buildShowAllDto(orderRepository.findAllByOrderFinishedIsTrue());
    }

    @Override
    public List<ProductsOfOrderDto> orderDetails(Long orderId){
        Order order = getOrderById(orderId);
        List<ProductsOfOrderDto> orderDetails = new ArrayList<>();
        List<Product> products = productRepository.findByOrder(order);

        products.forEach(p->orderDetails.add(ProductsOfOrderDto.builder()
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

    @Override
    public StagesOfProductDto stagesOfProduct(Long productId){
        Product product = getProductById(productId);

       return StagesOfProductDto.builder()
                .id(product.getId())
                .orderId(product.getOrder().getId())
                .productType(product.getProductType().getProductType())
                .stagesDetailsDtos(product.getProductionMap().entrySet().stream()
                        .map(e-> StagesDetailsDto.builder()
                                .phaseName(e.getKey().getName())
                                .actualStartOfStage(e.getValue().getActualStartOfStage())
                                .actualEndOfStage(e.getValue().getActualEndOfStage())
                                .duration(e.getValue().getDuration())
                                .delay()
                                .sequencePosition(e.getKey().getSequencePosition())
                                .build())
                        .sorted(Comparator.comparingInt(StagesDetailsDto::getSequencePosition))
                        .collect(Collectors.toList()))
                .build();


    }

    @Override
    public ProductDetailsDto productDetails (Long productId){
        Product product = getProductById(productId);
        return ProductDetailsDto.builder()
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

    @Override
    public Order getOrderById(Long orderID){
        return orderRepository.findById(orderID).orElse(new Order());
    }

    private Product getProductById(Long productId){
        return productRepository.findById(productId).orElse(new Product());
    }

    private String getActualPhase(Product product){
        return product.getProductionMap().entrySet().stream()
                .filter(e-> Objects.equals(e.getValue(),product.getActiveStage()))
                .map(Map.Entry::getKey)
                .findAny()
                .orElse(new ProductionPhase()).getName();
    }

    private List<OrderShowAllDto> buildShowAllDto(List<Order> orders){
        List<OrderShowAllDto> ordersShowAll = new ArrayList<>();

        orders.forEach(o->ordersShowAll.add(OrderShowAllDto.builder()
                .id(o.getId())
                .name(o.getName())
                .orderFinished(o.getOrderFinished())
                .orderValue(o.getOrderValue())
                .build()));

        return ordersShowAll;
    }


}
