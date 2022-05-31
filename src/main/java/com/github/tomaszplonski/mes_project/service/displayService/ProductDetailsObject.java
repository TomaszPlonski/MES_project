package com.github.tomaszplonski.mes_project.service.displayService;


import com.github.tomaszplonski.mes_project.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsObject {

    private String productType;
    private short status;
    private LocalDate plannedEndOfProduction;
    private LocalDate predictedEndOfProduction;
    private Integer delay;
    private String actualStageName;

    public static class ProductDetailsObjectBuilder {

        public ProductDetailsObjectBuilder status(Product product){
            if (product.getStageExecution().size() == 0) {
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

        public ProductDetailsObjectBuilder predictedEndOfProduction(){

            this.predictedEndOfProduction = plannedEndOfProduction.plusDays(delay);
            return this;
        }

    }

}
