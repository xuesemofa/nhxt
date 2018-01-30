package org.consume.com.heatattribute.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.crew.service.CrewService;
import org.consume.com.heatattribute.model.AttributeModel;
import org.consume.com.heatattribute.service.AttributeService;
import org.consume.com.util.redirect.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @name 换热站维护
 * @shiro heatattribute:
 */
@RestController
@RequestMapping("/heatattribute")
public class AttributeController {

    private static String view = "/heatattribute/";
    @Value("${page.pageSize}")
    private Integer pageSize;
    @Autowired
    private AttributeService service;
    @Autowired
    private CrewService crewService;

    /**
     * init
     *
     * @param pageNow Integer
     * @return ModelAndView Page<AttributeModel> /heat/index
     */
//    @RequiresPermissions(value = "heatattribute:init")
    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow,
                             @RequestParam(value = "error", required = false) String error) {
        Page<AttributeModel> page = service.findAllPage(pageNow, pageSize);
        List<CrewModel> crewModels = crewService.findAll();
        return new ModelAndView(view + "index")
                .addObject("model", new AttributeModel())
                .addObject("error", error)
                .addObject("page", page)
                .addObject("crewModels", crewModels);
    }

    /**
     * @param model  HeatModel
     * @param result BindingResult
     * @return ModelAndView
     */
//    @RequiresPermissions(value = "heatattribute:add")
    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute("model") AttributeModel model, BindingResult result) {
//        Page<AttributeModel> page = service.findAllPage(0, pageSize);
//        if (result.hasErrors()) {
//            return new ModelAndView(view + "index")
//                    .addObject("model", model)
//                    .addObject("errortext",
//                            result.getFieldError().getDefaultMessage())
//                    .addObject("page", page);
//        }
//        AttributeModel model1 = service.getByNames(model.getNames());
//        if (model1 != null)
//            return new ModelAndView(view + "index")
//                    .addObject("model", model)
//                    .addObject("errortext", "站点名称重复")
//                    .addObject("page", page);
        service.add(model);
        return new ModelAndView(RedirectUtil.getRddirect() + view + "init/0");
    }

    /**
     * @param id String
     * @return ModelAndView
     */
//    @RequiresPermissions(value = "heatattribute:del")
    @GetMapping("/del/{id}")
    public ModelAndView del(@PathVariable("id") String id) {
        service.del(id);
        return new ModelAndView(RedirectUtil.getRddirect() + view + "init/0");
    }

//    @RequiresPermissions(value = "heatattribute:update")
    @GetMapping("/toUpdate/{id}")
    public ModelAndView toUpdate(@PathVariable("id") String id) {
        AttributeModel model = service.getById(id);
        return new ModelAndView(view + "update")
                .addObject("model", model);
    }

//    @RequiresPermissions(value = "heatattribute:update")
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("model") AttributeModel model, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(view + "update")
                    .addObject("model", model)
                    .addObject("errortext",
                            result.getFieldError().getDefaultMessage());
        }
        AttributeModel model1 = service.getByNames(model.getNames());
        if (model1 != null)
            return new ModelAndView(view + "update")
                    .addObject("model", model)
                    .addObject("errortext", "站点名称重复");
        service.update(model);
        return new ModelAndView(RedirectUtil.getRddirect() + view + "init/0");
    }

//    @RequiresPermissions(value = "heatattribute:choice")
    @GetMapping("/choice")
    public ModelAndView choice(@RequestParam("id") String id, @RequestParam("b") boolean b) {
        int i = service.choice(id, b);
        if (i > 0)
            return new ModelAndView(RedirectUtil.getRddirect() + view + "init/0");
        return new ModelAndView(RedirectUtil.getRddirect() + view + "init/0")
                .addObject("error", "操作失败");
    }
}
