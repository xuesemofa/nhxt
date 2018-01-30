package org.consume.com.roles.controller;

import com.github.pagehelper.Page;
import org.consume.com.roles.model.RolesModel;
import org.consume.com.roles.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/roles")
public class RolesController {
    private static String roles = "/roles";

    @Value("${page.pageSize}")
    private int pageSizeDefull;

    @Autowired
    private RoleService service;

    @RequestMapping("/init")
    public ModelAndView init(@RequestParam(required = false) String pageNow,
                             @RequestParam(required = false) String pageSize,
                             @RequestParam(required = false) String serch) {
        if (pageNow == null || "".equals(pageNow))
            pageNow = "1";
        if (pageSize == null || "".equals(pageSize))
            pageSize = pageSizeDefull + "";
        Page<RolesModel> allPage = service.findAllPage(Integer.parseInt(pageNow), Integer.parseInt(pageSize));
        return new ModelAndView(roles + "/index").addObject("pager", allPage);
    }

    @RequestMapping("/add")
    public ModelAndView add(@ModelAttribute("model") RolesModel model) {
        int add = service.add(model);
        return new ModelAndView("redirect:/roles/init");
    }

    @RequestMapping("/del")
    public ModelAndView del(@RequestParam("uuid") String uuid) {
        int del = service.del(uuid);
        return new ModelAndView("redirect:/roles/init");
    }

    @RequestMapping("/update")
    public ModelAndView update(@RequestParam("uuid") String uuid) {
        return null;
    }
}
