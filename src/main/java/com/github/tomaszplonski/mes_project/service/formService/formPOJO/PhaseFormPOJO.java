package com.github.tomaszplonski.mes_project.service.formService.formPOJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhaseFormPOJO {

    @NotBlank @Length(min = 3)
    private String phaseName;

    @NotBlank @Positive
    private int defaultDuration;

    private int sequencePosition;
}
