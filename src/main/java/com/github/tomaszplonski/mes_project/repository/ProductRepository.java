package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
