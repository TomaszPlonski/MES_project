package com.github.tomaszplonski.mes_project.service;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderShowAllService {

    private final OrderRepository orderRepository;

    @Transactional
    public List<OrderShowAll> orderShowAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderShowAll> orderShowAll = new ArrayList<>();

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

        return orderShowAll;
    }
}
