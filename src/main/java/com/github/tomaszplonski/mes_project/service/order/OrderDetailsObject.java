package com.github.tomaszplonski.mes_project.service.order;


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
public class OrderDetailsObject {

    private String productType;
    private short status;
    private LocalDate initialEndOfProduction;
    private LocalDate predictedEndOfProduction;
    private Integer delay;
    private String actualStageName;

    public static class OrderDetailsObjectBuilder {

        public OrderDetailsObjectBuilder status(Product product){
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

        public OrderDetailsObjectBuilder predictedEndOfProduction(){

            this.predictedEndOfProduction = initialEndOfProduction.plusDays(delay);
            return this;
        }

    }

}
