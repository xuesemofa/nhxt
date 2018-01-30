package org.consume.com.jurisdiction.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.jurisdiction.service.JurisdictionService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/jurisdiction")
public class JurisdictionController {

    private static String views = "/jurisdiction";

    @Value("${page.pageSize}")
    private int pageSizeDefull;
    @Autowired
    private JurisdictionService service;

    @RequiresRoles("admin")
    @RequestMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable(required = false, value = "pageNow") String pageNow,
                             @RequestParam(required = false, value = "pageSize") String pageSize,
                             @RequestParam(required = false, value = "serch") String serch) {
        if (pageNow == null || "".equals(pageNow)) {
            pageNow = "1";
        }
        Page<JurisdictionModel> allByPage = service.findAllByPage(Integer.parseInt(pageNow), pageSizeDefull);
        return new ModelAndView(views + "/index").addObject("page", allByPage);
    }

    @RequiresRoles("admin")
    @RequestMapping("/add")
    public ModelAndView add(@ModelAttribute("model") JurisdictionModel modle) {
        modle.setUuid(GetUuid.getUUID());
        int add = service.add(modle);
        return new ModelAndView("redirect:/jurisdiction/init");
    }

    @RequiresRoles("admin")
    @RequestMapping("/del")
    public ModelAndView del(@RequestParam("id") String id) {
        int del = service.del(id);
        return new ModelAndView("redirect:/jurisdiction/init/0");
    }

    @RequiresRoles("admin")
    @RequestMapping("/qxcsh")
    public ModelAndView qxcsh() {
        return new ModelAndView("/qxcsh/index");
    }
}
