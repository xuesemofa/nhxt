package org.consume.com.ryssgklssj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ryssgklssj")
public class RyssgklssjController {

    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/ryssgklssj/index");
    }
}
