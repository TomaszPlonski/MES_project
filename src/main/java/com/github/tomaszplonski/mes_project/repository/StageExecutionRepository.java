package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StageExecutionRepository extends JpaRepository<StageExecution,Long> {

//    List<StageExecution> findByProduct(Product product);

//    @Query("SELECT se.estimatedEndOfStage from StageExecution se WHERE se.id = :stageId")
//    Optional<LocalDate> findEstimatedEndOfStageById(@Param("stageId") Long stageId);
//
////    @Query("SELECT se.productionPhase.name from StageExecution se WHERE se.id = :stageId")
////    Optional<String> findProductionPhaseNameById(@Param("stageId") Long stageId);


}
