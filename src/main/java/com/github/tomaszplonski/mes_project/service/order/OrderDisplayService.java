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
public class OrderDisplayService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

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

    public List<Product> getAllProducts(Order order){
        return null;
    }

    public List<StageExecution> getAllStageExecutions(Product product){
        return null;
    }

}
