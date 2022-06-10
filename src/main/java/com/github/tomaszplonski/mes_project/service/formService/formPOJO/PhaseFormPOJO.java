package com.github.tomaszplonski.mes_project.service.formService.formPOJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhaseFormPOJO {

    @NotBlank @Length(min = 3)
    private String phaseName;

    @Min(value = 0)
    @Digits(integer = 2,fraction = 0)
    private int defaultDuration;

    private int sequencePosition;
}
