package org.consume.com.rwssjccs.controller;

import org.consume.com.opc.service.OPC2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/rwssjccs")
public class RwssjccsController {

    @Autowired
    private OPC2Service opc2Service;

    @GetMapping("/init")
    public ModelAndView init() {
//        List<Datas2Model> models2 = opc2Service.findList2(zd, px);
        return new ModelAndView("/rwssjccs/index");
    }
}
