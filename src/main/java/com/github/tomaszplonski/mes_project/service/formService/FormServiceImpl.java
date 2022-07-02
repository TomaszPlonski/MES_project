package com.github.tomaszplonski.mes_project.service.formService;

import com.github.tomaszplonski.mes_project.model.*;
import com.github.tomaszplonski.mes_project.repository.*;
import com.github.tomaszplonski.mes_project.service.entitiService.product.StagesOfProductService;
import com.github.tomaszplonski.mes_project.service.formService.formDto.OrderFormDto;
import com.github.tomaszplonski.mes_project.service.formService.formDto.ProductFormDto;
import com.github.tomaszplonski.mes_project.service.formService.formDto.TypeFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final ProductTypeRepository productTypeRepository;
    private final TypeAttributeRepository typeAttributeRepository;
    private final ProductionPhaseRepository productionPhaseRepository;
    private final OrderRepository orderRepository;
    private final StagesOfProductService stagesOfProductService;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void createType(TypeFormDto typeFormDto){
        List<TypeAttribute> typeAttributes = new ArrayList<>();
        typeFormDto.getAttributes().forEach(a->typeAttributes
                .add(TypeAttribute.builder()
                .name(a.getAttribute())
                .build()));

        List<ProductionPhase> productionPhases = new ArrayList<>();
        typeFormDto.getPhases().forEach(p->productionPhases
                .add(ProductionPhase.builder()
                        .name(p.getPhaseName())
                        .defaultDuration(p.getDefaultDuration())
                        .build()));
        setPhaseSequencePosition(productionPhases);


        ProductType productType= productTypeRepository.save(ProductType.builder()
                .productType(typeFormDto.getProductType())
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
    public void saveOrder(OrderFormDto order){
        List<ProductFormDto> products = order.getProducts();
        Order orderDB = orderRepository.save(Order.builder()
                .orderValue(order.getOrderValue())
                .name(order.getName())
                .orderFinished(false)
                .build());

        products.forEach(p->saveProduct(p,orderDB));


    }

    @Transactional
    @Override
    public void saveProduct(ProductFormDto productFormDto, Order order){
        productRepository.save(Product.builder()
                .order(order)
                .productType(productFormDto.getProductType())
                .typeAttributeMap(productFormDto.getTypeAttributeMap())
                .productionMap(stagesOfProductService.stageInitialization(productFormDto.getProductType()))
                .activeStage()
                .duration()
                .plannedEndOfProduction()
                .productionFinished(false)
                .build());
    }



    private void setPhaseSequencePosition(List<ProductionPhase> phases){
        for (int i = 0; i < phases.size(); i++) {
            phases.get(i).setSequencePosition(i+1);
        }
    }

    @Override
    public List<ProductType> getAllTypes(){
        return productTypeRepository.findAll();
    }


    @Override
    public void attributePutInMap(OrderFormDto order, String value){
        getActualProductDto(order).getTypeAttributeMap()
                .put(getActualProductDto(order).getAttributes().remove(0)
                        , AttributeValue.builder().value(value).build());
    }

    private ProductFormDto getActualProductDto(OrderFormDto order){
        return order.getProducts().get(order.getProducts().size()-1);
    }

    @Override
    public void addNewProductDto(OrderFormDto order, Long newProductType){
        ProductFormDto product = new ProductFormDto();
        product.setProductType(productTypeRepository.findById(newProductType).orElse(new ProductType()));
        product.setAttributes(typeAttributeRepository.findAllByProductType(product.getProductType()));
        order.getProducts().add(product);
    }


}
