package com.github.tomaszplonski.mes_project.service.formService.formDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeFormDto {

    @NotBlank @Length(min = 3)
    private String productType;

    @NotEmpty
    @Builder.Default
    private List<AttributeFormDto> attributes = new ArrayList<>();

    @NotEmpty
    @Builder.Default
    private List<PhaseFormDto> phases = new ArrayList<>();

}
