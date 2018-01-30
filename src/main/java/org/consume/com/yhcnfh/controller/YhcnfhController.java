package org.consume.com.yhcnfh.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户采暖负荷信息
 */
@RestController
@RequestMapping("/yhcnfh")
public class YhcnfhController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "yhcnfh:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"cai-list");
    }
}
