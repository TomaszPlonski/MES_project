package com.github.tomaszplonski.mes_project.controller;

import com.github.tomaszplonski.mes_project.service.formService.FormService;
import com.github.tomaszplonski.mes_project.service.formService.formDto.AttributeFormDto;
import com.github.tomaszplonski.mes_project.service.formService.formDto.PhaseFormDto;
import com.github.tomaszplonski.mes_project.service.formService.formDto.TypeFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
     model.addAttribute("typeForm",new TypeFormDto());
     return "type/type-create";
    }

    @PostMapping(params = "createType")
    public String createType(@Valid @ModelAttribute("typeForm") TypeFormDto typeForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "type/type-create";
        }
        formService.createType(typeForm);

        return "redirect:/order/add";
    }

    @PostMapping(params = "addAttribute")
    public String addAttribute(@ModelAttribute("typeForm") TypeFormDto typeForm, AttributeFormDto attributeFormDto){
        typeForm.getAttributes().add(attributeFormDto);
        return "type/type-create";
    }

    @PostMapping(params = "removeAttribute")
    public String removeAttribute(@ModelAttribute("typeForm") TypeFormDto typeForm, @RequestParam int removeAttribute){
        typeForm.getAttributes().remove(removeAttribute);
        return "type/type-create";
    }

    @PostMapping(params = "addPhase")
    public String addPhase(@Valid @ModelAttribute("typeForm") TypeFormDto typeForm, BindingResult bindingResult, PhaseFormDto phaseFormDto){
        typeForm.getPhases().add(phaseFormDto);
        return "type/type-create";
    }

    @PostMapping(params = "removePhase")
    public String removePhase(@ModelAttribute("typeForm") TypeFormDto typeForm, @RequestParam int removePhase){
        typeForm.getPhases().remove(removePhase);
        return "type/type-create";
    }




}
