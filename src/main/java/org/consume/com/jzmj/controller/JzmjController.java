package org.consume.com.jzmj.controller;

import com.github.pagehelper.Page;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.crew.service.CrewService;
import org.consume.com.jzmj.model.JzmjModel;
import org.consume.com.jzmj.service.JzmjService;
import org.consume.com.util.redirect.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jzmj")
public class JzmjController {

    @Value("${page.pageSize}")
    private Integer pageSize;
    @Autowired
    private JzmjService service;
    @Autowired
    private CrewService crewService;

    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        Page<JzmjModel> page = service.findPage(pageNow, pageSize);
        List<CrewModel> all = crewService.findAll();
        return new ModelAndView("/jzmj/index")
                .addObject("page", page)
                .addObject("model", new JzmjModel())
                .addObject("all", all);
    }

    @PostMapping("/add")
    public ModelAndView init(@Valid @ModelAttribute("model") JzmjModel model, BindingResult result) {
        Page<JzmjModel> page = service.findPage(0, pageSize);
        List<CrewModel> all = crewService.findAll();
        if (result.hasErrors()) {
            return new ModelAndView("/jzmj/index")
                    .addObject("model", model)
                    .addObject("page", page)
                    .addObject("all", all)
                    .addObject("errortext", result.getFieldError().getDefaultMessage());
        } else {
            JzmjModel jzid = service.getByJzid(model.getJzid());
            if (jzid != null)
                return new ModelAndView("/jzmj/index")
                        .addObject("model", model)
                        .addObject("page", page)
                        .addObject("all", all)
                        .addObject("errortext", "该机组已经添加过面积");

            int i = service.add(model);
            if (i > 0)
                return new ModelAndView(RedirectUtil.getRddirect() + "/jzmj/init/0");
            else
                return new ModelAndView("/jzmj/index")
                        .addObject("model", model)
                        .addObject("page", page)
                        .addObject("all", all)
                        .addObject("errortext", "添加失败");
        }
    }

    @GetMapping("/del")
    public ModelAndView init(@RequestParam("id") String id) {
        service.del(id);
        Page<JzmjModel> page = service.findPage(0, pageSize);
        List<CrewModel> all = crewService.findAll();
        return new ModelAndView("/jzmj/index")
                .addObject("page", page)
                .addObject("model", new JzmjModel())
                .addObject("all", all);
    }
}
