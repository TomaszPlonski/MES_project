package com.github.tomaszplonski.mes_project.service.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderShowAll {

    private String name;
    private double orderValue;
    private int status;
}
