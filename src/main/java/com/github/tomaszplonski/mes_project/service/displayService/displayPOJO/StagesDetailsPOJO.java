package com.github.tomaszplonski.mes_project.service.displayService.displayPOJO;

import com.github.tomaszplonski.mes_project.utils.DaysBetween;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StagesDetailsPOJO {

    private String phaseName;

    private Integer duration;

    private LocalDate actualStartOfStage;

    private LocalDate actualEndOfStage;

    private Integer delay;

    private int sequencePosition;

    public static class StagesDetailsPOJOBuilder {

        public StagesDetailsPOJOBuilder delay() {
            if (actualEndOfStage == null || actualStartOfStage == null) {
                this.delay = null;
            } else {
                this.delay = DaysBetween.daysBetween(actualStartOfStage,actualEndOfStage) - duration;
            }
            return this;
        }

    }

}
