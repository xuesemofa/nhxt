package org.consume.com.cbgl.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据查询
 */
@RestController
@RequestMapping("/cbgl")
public class CbglController {

    private static String views = "/publics/";

//    @RequiresPermissions(value = "cbgl:init")
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView(views+"chaobiao-list");
    }
}
