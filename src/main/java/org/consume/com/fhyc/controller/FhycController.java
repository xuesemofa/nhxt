package org.consume.com.fhyc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/fhyc")
public class FhycController {

    @GetMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/fhyc/index");
    }
}
