package com.github.tomaszplonski.mes_project.service.formService;

import com.github.tomaszplonski.mes_project.model.*;
import com.github.tomaszplonski.mes_project.repository.*;
import com.github.tomaszplonski.mes_project.service.entitiService.product.StagesOfProductService;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.OrderFormPOJO;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.ProductFormPOJO;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.TypeFormPOJO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormService implements FormServiceDefault{

    private final ProductTypeRepository productTypeRepository;
    private final TypeAttributeRepository typeAttributeRepository;
    private final ProductionPhaseRepository productionPhaseRepository;
    private final OrderRepository orderRepository;
    private final StagesOfProductService stagesOfProductService;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void createType(TypeFormPOJO typeFormPOJO){
        List<TypeAttribute> typeAttributes = new ArrayList<>();
        typeFormPOJO.getAttributes().forEach(a->typeAttributes
                .add(TypeAttribute.builder()
                .name(a.getAttribute())
                .build()));

        List<ProductionPhase> productionPhases = new ArrayList<>();
        typeFormPOJO.getPhases().forEach(p->productionPhases
                .add(ProductionPhase.builder()
                        .name(p.getPhaseName())
                        .defaultDuration(p.getDefaultDuration())
                        .build()));
        setPhaseSequencePosition(productionPhases);


        ProductType productType= productTypeRepository.save(ProductType.builder()
                .productType(typeFormPOJO.getProductType())
                .typeAttributes(typeAttributes)
                .ProductionPhase(productionPhases)
                .build());

        typeAttributes.forEach(t->t.setProductType(productType));
        typeAttributeRepository.saveAll(typeAttributes);

        productionPhases.forEach(p->p.setProductType(productType));
        productionPhaseRepository.saveAll(productionPhases);

    }

    @Override
    @Transactional
    public void saveOrder(OrderFormPOJO order){
        List<ProductFormPOJO> products = order.getProducts();
        Order orderDB = orderRepository.save(Order.builder()
                .orderValue(order.getOrderValue())
                .name(order.getName())
                .orderFinished(false)
                .build());

        products.forEach(p->saveProduct(p,orderDB));


    }

    @Transactional
    @Override
    public void saveProduct(ProductFormPOJO productFormPOJO, Order order){
        productRepository.save(Product.builder()
                .order(order)
                .productType(productFormPOJO.getProductType())
                .typeAttributeMap(productFormPOJO.getTypeAttributeMap())
                .productionMap(stagesOfProductService.stageInitialization(productFormPOJO.getProductType()))
                .activeStage()
                .duration()
                .plannedEndOfProduction()
                .productionFinished(false)
                .build());
    }



    public void setPhaseSequencePosition(List<ProductionPhase> phases){
        for (int i = 0; i < phases.size(); i++) {
            phases.get(i).setSequencePosition(i+1);
        }
    }

    public List<ProductType> getAllTypes(){
        return productTypeRepository.findAll();
    }



    public void attributePutInMap(OrderFormPOJO order, String value){
        getActualProductPOJO(order).getTypeAttributeMap()
                .put(getActualProductPOJO(order).getAttributes().remove(0)
                        , AttributeValue.builder().value(value).build());
    }

    public ProductFormPOJO getActualProductPOJO(OrderFormPOJO order){
        return order.getProducts().get(order.getProducts().size()-1);
    }

    public void addNewProductPOJO(OrderFormPOJO order, Long newProductType){
        ProductFormPOJO product = new ProductFormPOJO();
        product.setProductType(productTypeRepository.findById(newProductType).orElse(new ProductType()));
        product.setAttributes(typeAttributeRepository.findAllByProductType(product.getProductType()));
        order.getProducts().add(product);
    }


}
