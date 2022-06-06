package com.github.tomaszplonski.mes_project.service.displayService.displayPOJO;

import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.model.StageExecution;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StagesOfProductPOJO {

    private Long orderId;

    private long id;

    private String productType;

    private List<StagesDetailsPOJO> stagesDetailsPOJOS;



}
