package com.github.tomaszplonski.mes_project.service.formService;

import com.github.tomaszplonski.mes_project.model.ProductType;
import com.github.tomaszplonski.mes_project.model.ProductionPhase;
import com.github.tomaszplonski.mes_project.model.TypeAttribute;
import com.github.tomaszplonski.mes_project.repository.ProductTypeRepository;
import com.github.tomaszplonski.mes_project.repository.ProductionPhaseRepository;
import com.github.tomaszplonski.mes_project.repository.TypeAttributeRepository;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.TypeFormPOJO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormService {

    private final ProductTypeRepository productTypeRepository;
    private final TypeAttributeRepository typeAttributeRepository;
    private final ProductionPhaseRepository productionPhaseRepository;

    @Transactional
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

    public void setPhaseSequencePosition(List<ProductionPhase> phases){
        for (int i = 0; i < phases.size(); i++) {
            phases.get(i).setSequencePosition(i+1);
        }
    }

}
