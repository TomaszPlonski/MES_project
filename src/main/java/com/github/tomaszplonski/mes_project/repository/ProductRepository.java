package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrder(Order order);

    @Query("SELECT p.activeStage from products p WHERE p = :product")
    Optional<StageExecution> findActiveStage(@Param("product") Product product);
}
