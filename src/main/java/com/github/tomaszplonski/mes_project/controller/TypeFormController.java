package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.service.formService.FormService;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.AttributeFormPOJO;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.PhaseFormPOJO;
import com.github.tomaszplonski.mes_project.service.formService.formPOJO.TypeFormPOJO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/type/add")
@RequiredArgsConstructor
public class TypeFormController {

    private final FormService formService;

    @GetMapping()
    public String prepareView(Model model){
     model.addAttribute("typeForm",new TypeFormPOJO());
     return "type/type-create";
    }

    @PostMapping(params = "createType")
    public String createType(@Valid @ModelAttribute("typeForm") TypeFormPOJO typeForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.debug("===========");
            bindingResult.getModel().values().forEach(e->log.debug("Values" + e.toString()));
            bindingResult.getModel().keySet().forEach(e->log.debug("keys" + e));
            return "type/type-create";
        }
        formService.createType(typeForm);

        return "redirect:/order/add";
    }

    @PostMapping(params = "addAttribute")
    public String addAttribute(@ModelAttribute("typeForm") TypeFormPOJO typeForm, AttributeFormPOJO attributeFormPOJO){
        typeForm.getAttributes().add(attributeFormPOJO);
        return "type/type-create";
    }

    @PostMapping(params = "removeAttribute")
    public String removeAttribute(@ModelAttribute("typeForm") TypeFormPOJO typeForm, @RequestParam int removeAttribute){
        typeForm.getAttributes().remove(removeAttribute);
        return "type/type-create";
    }

    @PostMapping(params = "addPhase")
    public String addPhase(@Valid @ModelAttribute("typeForm") TypeFormPOJO typeForm, BindingResult bindingResult, PhaseFormPOJO phaseFormPOJO){
        typeForm.getPhases().add(phaseFormPOJO);
        return "type/type-create";
    }

    @PostMapping(params = "removePhase")
    public String removePhase(@ModelAttribute("typeForm") TypeFormPOJO typeForm, @RequestParam int removePhase){
        typeForm.getPhases().remove(removePhase);
        return "type/type-create";
    }




}
