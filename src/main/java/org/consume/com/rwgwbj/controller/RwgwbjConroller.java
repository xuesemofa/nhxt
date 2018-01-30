package org.consume.com.rwgwbj.controller;

import org.consume.com.rwgwbj.model.RwgwbjModel;
import org.consume.com.rwgwbj.service.RwgwbjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rwgwbj")
public class RwgwbjConroller {

    private static String views = "/rwgwbj";

    @Autowired
    private RwgwbjService service;

    @RequestMapping("/init")
    public ModelAndView init()
    {
        return new ModelAndView(views+"/rwgwbj");
    }

    @RequestMapping("/qurey")
    public Map<String,Object> qurey()
    {
        List<RwgwbjModel> nowByRwgw = service.findNowByRwgw();
        HashMap<String, Object> map = new HashMap<>();
        String[] str = new String[nowByRwgw.size()];
        String[] str2 = new String[nowByRwgw.size()];
        for (int i = 0;i<nowByRwgw.size();i++)
        {
            str[i] = nowByRwgw.get(i).getJzid();
            str2[i] = nowByRwgw.get(i).getRwgw();
        }
        map.put("jzid",str);
        map.put("rwgw",str2);
        return map;
    }
}
