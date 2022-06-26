package com.github.tomaszplonski.mes_project.service.displayService.displayDto;

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
public class StagesDetailsDto {

    private String phaseName;

    private Integer duration;

    private LocalDate actualStartOfStage;

    private LocalDate actualEndOfStage;

    private Integer delay;

    private int sequencePosition;

    public static class StagesDetailsDtoBuilder {

        public StagesDetailsDtoBuilder delay() {
            if (actualEndOfStage == null || actualStartOfStage == null) {
                this.delay = null;
            } else {
                this.delay = DaysBetween.daysBetween(actualStartOfStage,actualEndOfStage) - duration;
            }
            return this;
        }

    }

}
