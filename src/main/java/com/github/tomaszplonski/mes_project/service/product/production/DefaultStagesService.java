package com.github.tomaszplonski.mes_project.service.product.production;

import com.github.tomaszplonski.mes_project.model.PhaseExecutor;
import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;

import java.time.LocalDate;

public interface DefaultStagesService {

   /**
    * Initializes a new instance of Stages and put it to the database.
    *
    *
    * @param product
    * @param productionPhase
    * @param phaseExecutor
    * @param duration productPhase got field defaultDuration use it as 'duration' if this is the standard case
    * @param stratOfStage
    *
    * @return LocalDate: date of Estimated End Of Stage. This will serve as the start date for the next stage
    */
   public LocalDate StageInitialization(Product product, ProductionPhase productionPhase, PhaseExecutor phaseExecutor, int duration, LocalDate stratOfStage);

   /**
    * Enter the end date of the stage as today's date
    *
    *
    * @param stageId
    *
    * @return Integer as differences between Estimated End Of Stage and Actual End Of Stage
    * 0 result means that the production time was as long as expected
    * positive result means that the production time was longer than expected by the return value
    * negative result means that production time was shorter than expected by the return value
    */
   public Integer EndOfStage(Long stageId);

   /**
    * Change Estimated End Of Stage
    *
    *
    * @param stageId
    * @param newEstimatedEndOfStage
    *
    * @return Integer as differences between Estimated End Of Stage and New Estimated End Of Stage
    * 0 result means that the new estimated time is same as old estimated time
    * positive result means that the new estimated time will extend productions by the return value
    * negative result means that the new estimated time will shorten the production by the return value
    */
   public Integer changeEstimatedEndOfStage(Long stageId, LocalDate newEstimatedEndOfStage);

   /**
    * Gives the number of days left to the end of production
    *
    *
    * @param stageId
    *
    * @return Integer as differences between actual date and Estimated End Of Stage
    * 0 result means that production should end today
    * positive result means number of days remaining until the end of production
    * negative result means by how many days the production has been exceeded
    */
   public Integer howManyDaysLeftToEstimatedEndOfStage(Long stageId);
}
