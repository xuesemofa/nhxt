package org.consume.com.hrznhcbfx.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 换热站能耗成本分析
 */
@RestController
@RequestMapping("/hrznhcbfx")
public class HrznhcbfxController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "hrznhcbfx:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"nhcbfx");
    }
}
