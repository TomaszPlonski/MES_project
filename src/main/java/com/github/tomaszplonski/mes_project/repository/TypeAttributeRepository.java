package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.model.TypeAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeAttributeRepository extends JpaRepository<TypeAttribute,Long> {

    List<TypeAttribute> findAllByProductType(ProductType productType);

}
