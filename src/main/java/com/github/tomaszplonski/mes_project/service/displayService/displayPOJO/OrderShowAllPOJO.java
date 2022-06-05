package com.github.tomaszplonski.mes_project.service.displayService.displayPOJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderShowAllPOJO {

    private Long id;
    private String name;
    private double orderValue;
    private Boolean orderFinished;

}
