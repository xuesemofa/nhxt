package org.consume.com.zbgl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/zbgl")
public class ZbglController {
    private static String views = "/zbgl";
    @RequestMapping("/init")
    public ModelAndView init()
    {
        return new ModelAndView(views+"/choiceTime2");
    }
    @RequestMapping("/confirmTime2")
    public ModelAndView confirmTime2(@RequestParam("t")String t)
    {
        String[] split = t.split("--");
        return new ModelAndView(views+"/details")
                .addObject("startTime",split[0])
                .addObject("endTime",split[1]);
    }
}
