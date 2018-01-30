package org.consume.com.hrzmj.controller;

import com.github.pagehelper.Page;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.hrzmj.model.HrzmjModel;
import org.consume.com.hrzmj.service.HrzmjService;
import org.consume.com.util.redirect.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hrzmj")
public class HrzmjController {

    @Value("${page.pageSize}")
    private Integer pageSize;
    @Autowired
    private HrzmjService service;
    @Autowired
    private HeatService heatService;

    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        Page<HrzmjModel> page = service.findPage(pageNow, pageSize);
        List<HeatModel> all = heatService.findAll();
        return new ModelAndView("/hrzmj/index")
                .addObject("page", page)
                .addObject("model", new HrzmjModel())
                .addObject("all", all);
    }

    @PostMapping("/add")
    public ModelAndView init(@Valid @ModelAttribute("model") HrzmjModel model, BindingResult result) {
        Page<HrzmjModel> page = service.findPage(0, pageSize);
        List<HeatModel> all = heatService.findAll();
        if (result.hasErrors()) {
            return new ModelAndView("/hrzmj/index")
                    .addObject("model", model)
                    .addObject("page", page)
                    .addObject("all", all)
                    .addObject("errortext", result.getFieldError().getDefaultMessage());
        } else {
            HrzmjModel jzid = service.getByJzid(model.getJzid());
            if (jzid != null)
                return new ModelAndView("/hrzmj/index")
                        .addObject("model", model)
                        .addObject("page", page)
                        .addObject("all", all)
                        .addObject("errortext", "该换热站已经添加过面积");

            int i = service.add(model);
            if (i > 0)
                return new ModelAndView(RedirectUtil.getRddirect() + "/hrzmj/init/0");
            else
                return new ModelAndView("/hrzmj/index")
                        .addObject("model", model)
                        .addObject("page", page)
                        .addObject("all", all)
                        .addObject("errortext", "添加失败");
        }
    }

    @GetMapping("/del")
    public ModelAndView init(@RequestParam("id") String id) {
        service.del(id);
        Page<HrzmjModel> page = service.findPage(0, pageSize);
        List<HeatModel> all = heatService.findAll();
        return new ModelAndView("/hrzmj/index")
                .addObject("page", page)
                .addObject("model", new HrzmjModel())
                .addObject("all", all);
    }
}
