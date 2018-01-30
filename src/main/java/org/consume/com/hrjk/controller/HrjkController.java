package org.consume.com.hrjk.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 换热监控实时工况
 */
@RestController
@RequestMapping("/hrjk")
public class HrjkController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "hrjk:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"shishigong");
    }
}
