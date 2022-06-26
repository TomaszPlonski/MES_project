package com.github.tomaszplonski.mes_project.service.formService.formDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeFormDto {

    @NotBlank
    @Length(min = 3)
    private String attribute;
}
