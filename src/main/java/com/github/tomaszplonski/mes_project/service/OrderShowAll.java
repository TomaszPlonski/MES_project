package com.github.tomaszplonski.mes_project.service;


import com.github.tomaszplonski.mes_project.model.OrderHandler;
import com.github.tomaszplonski.mes_project.model.Purchaser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderShowAll {

    private String name;
    private double orderValue;
    private String purchaserName;
    private String orderHandlerName;
    private boolean isProductionStarted;
    private boolean isProductionEnded;
}
