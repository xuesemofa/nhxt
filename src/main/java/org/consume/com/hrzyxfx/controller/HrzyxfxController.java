package org.consume.com.hrzyxfx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 换热站营销分析
 */
@RestController
@RequestMapping("/hrzyxfx")
public class HrzyxfxController {

    private static String views = "/hrzyxfx/";

    //    @RequiresPermissions(value = "hrzyxfx:init")
    @RequestMapping("/init")
    public ModelAndView init() {
        return new ModelAndView(views + "index");
    }
}
