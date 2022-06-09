package com.github.tomaszplonski.mes_project.service.displayService.displayPOJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StagesOfProductPOJO {

    private Long orderId;

    private Long id;

    private String productType;

    private List<StagesDetailsPOJO> stagesDetailsPOJOS;



}
