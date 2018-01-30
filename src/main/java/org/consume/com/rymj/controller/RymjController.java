package org.consume.com.rymj.controller;

import org.consume.com.rymj.model.RymjModel;
import org.consume.com.rymj.service.RymjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/rymj")
public class RymjController {
    @Autowired
    private RymjService rymjService;

    @GetMapping("/init")
    public ModelAndView init() {
        List<RymjModel> finds = rymjService.get();
        RymjModel model;
        if (finds != null && finds.size() > 0)
            model = finds.get(0);
        else
            model = new RymjModel();
        return new ModelAndView("/rymj/index")
                .addObject("model", model);
    }

    @PostMapping("/add")
    public ModelAndView add(@ModelAttribute("model") RymjModel model1) {

        rymjService.set(model1);

        List<RymjModel> finds = rymjService.get();
        RymjModel model;
        if (finds != null && finds.size() > 0)
            model = finds.get(0);
        else
            model = new RymjModel();
        return new ModelAndView("/rymj/index")
                .addObject("model", model);
    }
}
