package com.github.tomaszplonski.mes_project.service.displayService.displayDto;

import com.github.tomaszplonski.mes_project.model.AttributeValue;
import com.github.tomaszplonski.mes_project.model.TypeAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {

    private Long orderId;
    private Long productId;
    private String productType;
    private Map<TypeAttribute, AttributeValue> typeAttributeMap;
}
