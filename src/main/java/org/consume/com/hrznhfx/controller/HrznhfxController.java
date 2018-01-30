package org.consume.com.hrznhfx.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 换热站能耗分析
 */
@RestController
@RequestMapping("/hrznhfx")
public class HrznhfxController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "hrznhfx:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"nh-list");
    }
}
