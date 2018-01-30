package org.consume.com.structure.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.util.chartonumber.CTNUtil;
import org.consume.com.util.redirect.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name 组织结构
 * @shiro @roles admins
 */
@RestController
@RequestMapping("/structure")
public class StructureController {

    private static String view = "/structure/";

    @Autowired
    private StructureService service;

    @Autowired
    private UserService userService;

    /**
     * init
     *
     * @param parentid String
     * @return ModelAndView
     */
    @RequiresRoles("admins")
    @GetMapping("/init")
    public ModelAndView init(@RequestParam(value = "parentid", required = false) String parentid) {
//        List<StructureModel> list = service.getByParent(parentid == null ? "0" : parentid);
        List<StructureModel> list = service.getByParents();
        return new ModelAndView(view + "index")
                .addObject("list", list);
    }

    /**
     * @return ModelAndView
     */
    @RequiresRoles("admins")
    @GetMapping("/toAdd")
    public ModelAndView toAdd() {
        return new ModelAndView(view + "add")
                .addObject("model", new StructureModel());
    }

    @RequiresRoles("admins")
    @GetMapping("/toAdds/{views}")
    public ModelAndView toAdds(@PathVariable("views") String views) {
        List<StructureModel> companysList = service.findAlls("0", "1");
        return new ModelAndView(view + views)
                .addObject("model", new StructureModel())
                .addObject("companysList", companysList);
    }

    /**
     * add
     *
     * @param model StructureModel
     * @return ModelAndView
     */
    @RequiresRoles("admins")
    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute("model") StructureModel model, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/error")
                    .addObject("errors", result.getFieldError().getDefaultMessage());
        }
        int i1 = CTNUtil.CharToNumber(model.getNames());
        model.setOrdes(i1);
        int i = service.add(model);
        if (i > 0)
            return new ModelAndView(RedirectUtil.getRddirect() + view + "init");
        return new ModelAndView(view + "add")
                .addObject("model", model);
    }

    /**
     * del
     *
     * @param id String
     * @return ModelAndView
     */
    @RequiresRoles("admins")
    @GetMapping("/del/{id}")
    public Map<String, String> del(@PathVariable("id") String id) {
        List<StructureModel> list = service.getByParent(id);
        List<UserModel> list1 = userService.getByCD(id);
        Map<String, String> map = new HashMap<>();
        if ((list == null || list.size() <= 0) && (list1 == null || list1.size() <= 0)) {
            int i = service.del(id);
            if (i > 0)
                map.put("a", "200");
            else {
                map.put("a", "500");
                map.put("b", "删除失败");
            }
        } else {
            map.put("a", "500");
            map.put("b", "请先删除下级");
        }
        return map;
    }

    /**
     * toupdate
     *
     * @param id String
     * @return ModelAndView
     */
    @RequiresRoles("admins")
    @GetMapping("/toUpdate/{id}")
    public ModelAndView toupdate(@PathVariable("id") String id) {
        StructureModel model = service.getById(id);
        return new ModelAndView(view + "update")
                .addObject("model", model);
    }

    /**
     * update
     *
     * @param model  StructureModel
     * @param result BindingResult
     * @return ModelAndView
     */
    @RequiresRoles("admins")
    @PutMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("model") StructureModel model, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(view + "update").addObject("model", model);
        }
        service.update(model);
        return new ModelAndView(RedirectUtil.getRddirect() + view + "init");
    }

    /**
     * 根据父级id和类型获取
     *
     * @param id String
     * @param lx String
     * @return List<StructureModel>
     */
//    @RequiresRoles("admins")
    @PostMapping("/subordinate")
    public List<StructureModel> getSubordinate(@RequestParam("id") String id, @RequestParam("lx") String lx) {
        return service.findAll(id);
    }
}
