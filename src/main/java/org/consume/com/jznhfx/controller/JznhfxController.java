package org.consume.com.jznhfx.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 机组能耗分析
 */
@RestController
@RequestMapping("/jznhfx")
public class JznhfxController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "jznhfx:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"ji-list");
    }
}
