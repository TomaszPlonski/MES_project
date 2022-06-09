package com.github.tomaszplonski.mes_project.service.formService.formPOJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFormPOJO {

    @NotBlank @Length(min = 3)
    private String name;

    @Positive
    private double orderValue;

    @Builder.Default
    private List<ProductFormPOJO> products = new ArrayList<>();

}
