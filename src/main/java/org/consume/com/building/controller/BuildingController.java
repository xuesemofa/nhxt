package org.consume.com.building.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.consume.com.building.model.BuildingModel;
import org.consume.com.building.service.BuildingService;
import org.consume.com.util.redirect.RedirectUtil;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/building")
public class BuildingController {

    private static String views = "/building";

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Autowired
    private BuildingService service;

    @RequestMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow
            , @RequestParam(value = "serch", required = false) String serch) {
        Page<BuildingModel> all = service.findAll2(pageNow, pageSize, serch);
        return new ModelAndView(views + "/index")
                .addObject("page", all)
                .addObject("serch", serch)
                .addObject("model", new BuildingModel());
    }

    @RequestMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute("model") BuildingModel model, BindingResult result) {
        Page<BuildingModel> all = service.findAll2(0, pageSize, null);
        if (result.hasErrors()) {
            return new ModelAndView(views + "/index")
                    .addObject("model", model)
                    .addObject("errortext",
                            result.getFieldError().getDefaultMessage())
                    .addObject("page", all);
        }
        BuildingModel byName = service.findByName(model.getNames());
        if (null != byName) {
            return new ModelAndView(views + "/index")
                    .addObject("model", model)
                    .addObject("errortext", "建筑类型名称重复")
                    .addObject("page", all);
        }
        model.setUuid(GetUuid.getUUID());
        service.add(model);
        return new ModelAndView("redirect:/building/init/0");
    }

    /**
     * @param id String
     * @return ModelAndView
     */
//    @RequiresPermissions(value = "heat:del")
    @GetMapping("/del")
    public ModelAndView del(@RequestParam("id") String id, @RequestParam("pageNow") Integer pageNow) {
        service.del(id);
        return new ModelAndView(RedirectUtil.getRddirect() + "/building/init/" + pageNow);
    }

//    @RequiresPermissions(value = "heat:update")
    @GetMapping("/toUpdate")
    public ModelAndView toUpdate(@RequestParam("id") String id, @RequestParam("pageNow") Integer pageNow) {
        BuildingModel model = service.findById(id);
        return new ModelAndView(views + "/update")
                .addObject("model", model)
                .addObject("pageNow", pageNow);
    }

    //    @RequiresPermissions(value = "heat:update")
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("model") BuildingModel model
            , @RequestParam("pageNow") Integer pageNow
            , BindingResult result) {
        BuildingModel mo = service.findById(model.getUuid());
        if (result.hasErrors()) {
            return new ModelAndView(views + "/update")
                    .addObject("model", mo)
                    .addObject("errortext",
                            result.getFieldError().getDefaultMessage());
        }
        service.update(model);
        return new ModelAndView(RedirectUtil.getRddirect() + "/building/init/" + pageNow);
    }
}
