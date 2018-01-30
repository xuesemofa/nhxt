package org.consume.com.heat.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
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
 * @shiro heat:
 */
@RestController
@RequestMapping("/heat")
public class HeatController {

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Autowired
    private HeatService service;

    @Autowired
    private StructureService structureService;

    /**
     * init
     *
     * @param pageNow Integer
     * @return ModelAndView Page<HeatModel> /heat/index
     */
//    @RequiresPermissions(value = "heat:init")
    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow,@RequestParam(value = "query",required = false)String query) {
        Page<HeatModel> page = new Page<>();
        if(query == null || "".equals(query))
        {
            page = service.findAllPage(pageNow, pageSize);
        }else
            {
                page = service.findByQueryPage(pageNow, pageSize, query);
            }
        List<StructureModel> list = structureService.getByParents();
        return new ModelAndView("/heat/index")
                .addObject("model", new HeatModel())
                .addObject("list", list)
                .addObject("page", page)
                .addObject("query",query);
    }

    /**
     * @param model  HeatModel
     * @param result BindingResult
     * @return ModelAndView
     */
//    @RequiresPermissions(value = "heat:add")
    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute("model") HeatModel model, BindingResult result) {
        Page<HeatModel> page = service.findAllPage(0, pageSize);
        List<StructureModel> list = structureService.getByParents();
        if (result.hasErrors()) {
            return new ModelAndView("/heat/index")
                    .addObject("model", model)
                    .addObject("errortext",
                            result.getFieldError().getDefaultMessage())
                    .addObject("list", list)
                    .addObject("page", page);
        }
        HeatModel model1 = service.getByNames(model.getNames());
        if (model1 != null)
            return new ModelAndView("/heat/index")
                    .addObject("model", model)
                    .addObject("errortext", "站点名称重复")
                    .addObject("list", list)
                    .addObject("page", page);
        service.add(model);
        return new ModelAndView(RedirectUtil.getRddirect() + "/heat/init/0");
    }

    /**
     * @param id String
     * @return ModelAndView
     */
    @RequiresPermissions(value = "heat:del")
    @GetMapping("/del/{id}")
    public ModelAndView del(@PathVariable("id") String id,@PathVariable("pageNow") Integer pageNow) {
        service.del(id);
        return new ModelAndView(RedirectUtil.getRddirect() + "/heat/init/"+pageNow);
    }

    @RequiresPermissions(value = "heat:update")
    @GetMapping("/toUpdate")
    public ModelAndView toUpdate(@RequestParam("id") String id,@RequestParam("pageNow") Integer pageNow) {
        HeatModel model = service.getById(id);
//        List<StructureModel> list = structureService.getByParents();
        return new ModelAndView("/heat/update")
//                .addObject("list", list)
                .addObject("model", model)
                .addObject("pageNow",pageNow);
    }

    //    @RequiresPermissions(value = "heat:update")
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("model") HeatModel model
            ,@RequestParam("pageNow")Integer pageNow
            , BindingResult result) {
        List<StructureModel> list = structureService.getByParents();
        if (result.hasErrors()) {
            return new ModelAndView("/heat/update")
                    .addObject("model", model)
                    .addObject("list", list)
                    .addObject("errortext",
                            result.getFieldError().getDefaultMessage());
        }
        service.update(model);
        return new ModelAndView(RedirectUtil.getRddirect() + "/heat/init/"+pageNow);
    }
}
