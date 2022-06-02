package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {
}
