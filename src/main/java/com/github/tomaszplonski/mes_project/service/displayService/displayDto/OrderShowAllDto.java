package com.github.tomaszplonski.mes_project.service.displayService.displayDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderShowAllDto {

    private Long id;
    private String name;
    private double orderValue;
    private Boolean orderFinished;

}
