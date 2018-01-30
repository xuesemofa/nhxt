package org.consume.com.sdrdj.controller;

import org.consume.com.sdrdj.model.SdrdjModel;
import org.consume.com.sdrdj.service.SdrdjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sdrdj")
public class SdrdjController {

    @Autowired
    private SdrdjService sdrdjService;

    @GetMapping("/init")
    public ModelAndView init() {
        List<SdrdjModel> finds = sdrdjService.finds();
        SdrdjModel model;
        if (finds != null && finds.size() > 0)
            model = finds.get(0);
        else
            model = new SdrdjModel();
        return new ModelAndView("/sdrdj/index")
                .addObject("model", model);
    }

    @PostMapping("/add")
    public ModelAndView add(@ModelAttribute("model") SdrdjModel model1) {

        sdrdjService.del();
        sdrdjService.add(model1);

        List<SdrdjModel> finds = sdrdjService.finds();
        SdrdjModel model;
        if (finds != null && finds.size() > 0)
            model = finds.get(0);
        else
            model = new SdrdjModel();
        return new ModelAndView("/sdrdj/index")
                .addObject("model", model);
    }
}
