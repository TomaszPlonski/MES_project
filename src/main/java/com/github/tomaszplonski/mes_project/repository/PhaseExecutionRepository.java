package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.PhaseExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseExecutionRepository extends JpaRepository<PhaseExecution,Long> {
}
