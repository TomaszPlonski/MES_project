package com.github.tomaszplonski.mes_project.service.displayService.displayPOJO;


import com.github.tomaszplonski.mes_project.model.Product;
import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.utils.WorkingDays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsOfOrderPOJO {

    private Long id;
    private ProductType productType;
    private short status;
    private LocalDate plannedEndOfProduction;
    private LocalDate predictedEndOfProduction;
    private int duration;
    private Integer delay;
    private String actualStageName;

    public static class ProductsOfOrderPOJOBuilder {

        public ProductsOfOrderPOJOBuilder status(Product product){
            if (product.getProductionMap().size() == 0) {
                this.status = 0;
            } else if (delay>0) {
                this.status = -1;
            } else if (product.getProductionFinished()) {
                this.status = 2;
            } else {
                this.status = 1;
            }
            return this;
        }

        public ProductsOfOrderPOJOBuilder predictedEndOfProduction(){

            this.predictedEndOfProduction = plannedEndOfProduction.with(WorkingDays.addWorkingDays(delay));
            return this;
        }

    }

}
