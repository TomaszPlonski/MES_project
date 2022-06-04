package com.github.tomaszplonski.mes_project.service.formService.formPOJO;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeFormPOJO {

    @NotBlank @Length(min = 3)
    private String productType;

    @Builder.Default
    private List<AttributeFormPOJO> attributes = new ArrayList<>();

    @Builder.Default
    private List<PhaseFormPOJO> phases = new ArrayList<>();

}
