package com.github.tomaszplonski.mes_project.service.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderShowAllObject {

    private Long id;
    private String name;
    private double orderValue;

}
