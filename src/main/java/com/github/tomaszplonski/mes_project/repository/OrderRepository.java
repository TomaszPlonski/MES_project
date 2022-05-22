package com.github.tomaszplonski.mes_project.repository;


import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Supervisor;
import com.github.tomaszplonski.mes_project.model.Purchaser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {


    List<Order> findByPurchaser(Purchaser purchaser);

}
