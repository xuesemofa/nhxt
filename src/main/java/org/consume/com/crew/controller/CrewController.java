package org.consume.com.crew.controller;

import com.github.pagehelper.Page;
import org.consume.com.building.model.BuildingModel;
import org.consume.com.building.service.BuildingService;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.crew.service.CrewService;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.util.redirect.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @name 机组维护
 * @shiro crew:
 */
@RestController
@RequestMapping("/crew")
public class CrewController {

    private static String view = "/crew/";
    @Value("${page.pageSize}")
    private Integer pageSize;
    @Autowired
    private CrewService service;

    @Autowired
    private HeatService heatService;

    @Autowired
    private BuildingService buildingService;

    /**
     * init
     *
     * @param pageNow Integer
     * @return ModelAndView Page<AttributeModel> /heat/index
     */
//    @RequiresPermissions(value = "crew:init")
    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow,@RequestParam(value = "query",required = false)String query) {
        Page<CrewModel> page = service.findAllPage1(pageNow, pageSize,query);
        List<HeatModel> heats = heatService.findAll();
        List<BuildingModel> all = buildingService.findAll();
        return new ModelAndView(view + "index")
                .addObject("model", new CrewModel())
                .addObject("list", heats)
                .addObject("page", page)
                .addObject("query",query)
                .addObject("jzlx",all);
    }

    /**
     * @param model  HeatModel
     * @param result BindingResult
     * @return ModelAndView
     */
//    @RequiresPermissions(value = "crew:add")
    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute("model") CrewModel model, BindingResult result) {
        Page<CrewModel> page = service.findAllPage(0, pageSize);
        List<HeatModel> heats = heatService.findAll();
        if (result.hasErrors()) {
            return new ModelAndView(view + "index")
                    .addObject("model", model)
                    .addObject("errortext",
                            result.getFieldError().getDefaultMessage())
                    .addObject("list", heats)
                    .addObject("page", page);
        }
        CrewModel model1 = service.getByNames(model.getNames());
        if (model1 != null)
            return new ModelAndView(view + "index")
                    .addObject("model", model)
                    .addObject("errortext", "站点名称重复")
                    .addObject("list", heats)
                    .addObject("page", page);
        service.add(model);
        return new ModelAndView(RedirectUtil.getRddirect() + view + "init/0");
    }

    /**
     * @param id String
     * @return ModelAndView
     */
//    @RequiresPermissions(value = "crew:del")
    @GetMapping("/del")
    public ModelAndView del(@RequestParam("id") String id,@RequestParam("pageNow") Integer pageNow) {
        service.del(id);
        return new ModelAndView(RedirectUtil.getRddirect() + view + "init/"+pageNow);
    }

    //    @RequiresPermissions(value = "crew:update")
    @GetMapping("/toUpdate")
    public ModelAndView toUpdate(@RequestParam("id") String id,@RequestParam("pageNow") Integer pageNow) {
        CrewModel model = service.getById(id);
        List<HeatModel> heats = heatService.findAll();
        List<BuildingModel> all = buildingService.findAll();
        return new ModelAndView(view + "update")
                .addObject("list", heats)
                .addObject("model", model)
                .addObject("pageNow",pageNow)
                .addObject("jzlx",all);
    }

    //    @RequiresPermissions(value = "crew:update")
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("model") CrewModel model
            ,@RequestParam("pageNow") Integer pageNow
            , BindingResult result) {
        List<HeatModel> heats = heatService.findAll();
        if (result.hasErrors()) {
            return new ModelAndView(view + "update")
                    .addObject("model", model)
                    .addObject("list", heats)
                    .addObject("errortext",
                            result.getFieldError().getDefaultMessage());
        }
        service.update(model);
        return new ModelAndView(RedirectUtil.getRddirect() + view + "init/"+pageNow);
    }
}
