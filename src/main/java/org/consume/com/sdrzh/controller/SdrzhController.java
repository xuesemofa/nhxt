package org.consume.com.sdrzh.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 水电热综合统计分析
 */
@RestController
@RequestMapping("/sdrzh")
public class SdrzhController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "sdrzh:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"zonghe");
    }
}
