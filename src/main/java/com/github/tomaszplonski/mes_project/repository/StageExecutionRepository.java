package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.StageExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageExecutionRepository extends JpaRepository<StageExecution,Long> {
}
