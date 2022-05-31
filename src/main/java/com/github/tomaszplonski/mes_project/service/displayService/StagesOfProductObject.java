package com.github.tomaszplonski.mes_project.service.displayService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StagesOfProductObject {

    private int duration;

    private LocalDate actualStartOfStage;

    private LocalDate actualEndOfStage;

    private String productionPhaseName;

    private Integer delay;

    public static class StagesOfProductObjectBuilder{

        public StagesOfProductObjectBuilder delay(){
            if(actualEndOfStage==null || actualStartOfStage==null){
                this.delay = null;
            } else {
                this.delay = Math.toIntExact(ChronoUnit.DAYS.between(actualStartOfStage, actualEndOfStage)) - duration;
            }
            return this;
        }

    }


}
