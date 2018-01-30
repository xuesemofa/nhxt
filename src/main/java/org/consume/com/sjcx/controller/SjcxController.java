package org.consume.com.sjcx.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据查询
 */
@RestController
@RequestMapping("/sjcx")
public class SjcxController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "sjcx:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"cha-1");
    }
}
