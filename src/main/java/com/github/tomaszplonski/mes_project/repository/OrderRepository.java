package com.github.tomaszplonski.mes_project.repository;


import com.github.tomaszplonski.mes_project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByOrderFinishedIsFalse();

    List<Order> findAllByOrderFinishedIsTrue();


}
