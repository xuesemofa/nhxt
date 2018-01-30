package org.consume.com.hrzcbfxwx.controller;

import com.github.pagehelper.Page;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.hrzcbfxwx.service.HrzcbfxwxService;
import org.consume.com.util.redirect.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 维修成本
 */
@RestController
@RequestMapping("/hrzcbfxwx")
public class HrzcbfxwxController {

    @Value("${page.pageSize}")
    private Integer pageSize;
    @Autowired
    private HeatService heatService;
    @Autowired
    private HrzcbfxwxService hrzcbfxwxService;

    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        Page<HeatModel> page = heatService.findAllPage(pageNow, pageSize);
        return new ModelAndView("/hrzcbfxwx/index")
                .addObject("page", page);
    }

    @GetMapping("/setWx")
    public ModelAndView setWx(@RequestParam("id") String id) {
        HeatModel model = heatService.getById(id);
        return new ModelAndView("/hrzcbfxwx/setwx")
                .addObject("model", model);
    }

    @PostMapping("/setData")
    public ModelAndView setData(@RequestParam("id") String id, @RequestParam("v") double v) {
        hrzcbfxwxService.setWx(id, v);
        return new ModelAndView(RedirectUtil.getRddirect() + "/hrzcbfxwx/init/0");
    }
}
