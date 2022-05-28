package com.github.tomaszplonski.mes_project.service.order;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import com.github.tomaszplonski.mes_project.repository.OrderRepository;
import com.github.tomaszplonski.mes_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDisplay {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public List<OrderShowAll> orderShowAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderShowAll> ordersShowAll = new ArrayList<>();

//        orders.forEach(o->ordersShowAll.add(OrderShowAll.builder()
//                        .name(o.getName())
//                        .orderValue(o.getOrderValue())
//                        .status(o.getProducts().stream()
//                                .filter())
//                        .build()));

        //        orders.forEach(o->orderShowAll.add(OrderShowAll.builder()
//                                .name(o.getName())
//                                .orderSupervisor(o.getSupervisor().getFullName())
//                                .purchaserName(o.getPurchaser().getName())
//                                .orderValue(o.getOrderValue())
//                                .isProductionStarted(o.getExpansionJoints().stream()
//                                        .anyMatch(ej->ej.getStarOfProduction()==null))
//                                .isProductionEnded(o.getExpansionJoints().stream()
//                                        .allMatch(ej->ej.getActualEndOfProduction()!=null))
//                                .build()));

        return null;
    }

    public List<Product> getAllProducts(Order order){
        return null;
    }

    public List<StageExecution> getAllStageExecutions(Product product){
        return null;
    }

}
