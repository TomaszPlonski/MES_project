package com.github.tomaszplonski.mes_project.service.order.product.production;

import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.model.StageExecution;

import java.time.LocalDate;

public interface DefaultStagesService {

   /**
    * Initializes a new instance of Stages and put it to the database.
    *
    *
    * @param product
    * @param productionPhase
    * @param duration productPhase got field defaultDuration use it as 'duration' if this is the standard case
    *
    * @return created Stage
    */
   public StageExecution StageInitialization(Product product, ProductionPhase productionPhase, int duration, LocalDate startOfStage);

   /**
    * Enter the end date of the stage as today's date
    *
    *
    * @param endedStage
    *
    * @return updated Stage
    */
   public StageExecution endOfStage(StageExecution endedStage);

   /**
    * Change Estimated End Of Stage
    *
    *
    * @param stage
    * @param newEstimatedEndOfStage
    *
    * @return updated Stage
    */
   public StageExecution changeEstimatedEndOfStage(StageExecution stage, LocalDate newEstimatedEndOfStage);

   /**
    * Gives the number of days left to the end of production
    *
    *
    * @param stage
    *
    * @return Integer as differences between actual date and Estimated End Of Stage
    * 0 result means that production should end today
    * positive result means number of days remaining until the end of production
    * negative result means by how many days the production has been exceeded
    */
   public Integer howManyDaysLeftToEstimatedEndOfStage(StageExecution stage);

   /**
    * Change Estimated End Of Stage by numbers of days by @param daysToShift
    *
    *
    * @param stage
    * @param daysToShift
    *
    * @return updated Stage
    */
   public StageExecution changeEstimatedEndOfStage(StageExecution stage, Integer daysToShift);

   /**
    * Change Estimated End Of Stage based on the end date of the previous stage
    *
    *
    * @param stage
    * @param previousStage
    *
    * @return updated Stage
    */
   public StageExecution changeNoActiveEstimatedEndOfStage(StageExecution stage, StageExecution previousStage);

   /**
    * Sets the start date of nextStage by Estimated End Of Stage of previousStage. Sets id of nextStage to field NextStageId of object previousStage
    *
    *
    * @param previousStage
    * @param nextStage
    *
    * @return updated next Stage
    */
   public StageExecution stageQueuing(StageExecution previousStage, StageExecution nextStage);
}
