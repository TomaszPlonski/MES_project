package com.github.tomaszplonski.mes_project.repository;


import com.github.tomaszplonski.mes_project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Long> {


}
