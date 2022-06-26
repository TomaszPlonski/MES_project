package com.github.tomaszplonski.mes_project.service.formService.formDto;

import com.github.tomaszplonski.mes_project.model.AttributeValue;
import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.model.TypeAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFormDto {

    private String name;

    private ProductType productType;

    private List<TypeAttribute> attributes;

    private Map<TypeAttribute, AttributeValue> typeAttributeMap = new LinkedHashMap<>();

}
