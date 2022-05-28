package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageExecutionRepository extends JpaRepository<StageExecution,Long> {

    List<StageExecution> findByProduct(Product product);

}
