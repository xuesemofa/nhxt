package org.consume.com.home.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.consume.com.api.ApiService;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.opc.service.OPC2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HeatService service;
    @Autowired
    private OPC2Service opc2Service;

    /**
     * 登录后主页
     */
    @RequiresAuthentication
    @RequestMapping("/init")
    public ModelAndView init() throws Exception {
//所有的换热站
        List<HeatModel> list = service.findAll();
        ApiService api = new ApiService();
//        api.httpUrlConnetionGet();
//        api.httpUrlConnectionPost();
        return new ModelAndView("/home/index")
                .addObject("heats", list);

    }

    /**
     * 登录后首页
     *
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("/initSubpage")
    public ModelAndView initSubpage() {
        return new ModelAndView("/right");
    }

    @RequestMapping("/getCpuRatioForWindows")
    public int getCpuRatioForWindows() throws Exception {
        int max = 300;
        int min = 1;
        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    @GetMapping("/testApi")
    public String testApi(@RequestParam("rq") String rq) throws Exception {
        opc2Service.getByDate(rq);
        return "";
    }

}
