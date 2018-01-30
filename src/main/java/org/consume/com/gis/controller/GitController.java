package org.consume.com.gis.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * gis
 */
@RestController
@RequestMapping("/gis")
public class GitController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "gis:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"gis");
    }
}
