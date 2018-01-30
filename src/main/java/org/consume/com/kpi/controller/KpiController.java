package org.consume.com.kpi.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 换热站能耗KPI指标管理
 */
@RestController
@RequestMapping("/kpi")
public class KpiController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "kpi:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"kpicbfx");
    }
}
