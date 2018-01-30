package org.consume.com.sf.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 收费信息
 */
@RestController
@RequestMapping("/sf")
public class SfController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "sf:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"shou-list");
    }
}
