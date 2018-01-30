package org.consume.com.sdrzz.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 水电热站与站统计分析
 */
@RestController
@RequestMapping("/sdrzz")
public class SdrzzController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "sdrzz:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"zonghe2");
    }
}
