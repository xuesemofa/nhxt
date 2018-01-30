package org.consume.com.rltj.controller;

import org.consume.com.opc.model.Datas3Model;
import org.consume.com.rltj.service.serviceImpl.RltjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 热量统计分析
 */
@RestController
@RequestMapping("/rltj")
public class RltjController {

    private static String views = "/publics/";
    @Autowired
    private RltjService service;

    //    @RequiresPermissions(value = "rltj:init")
    @RequestMapping("/init")
    public ModelAndView init() {
        return new ModelAndView(views + "nenghao");
    }

    @RequestMapping("/qurey")
    public Map<String, Object> qurey(@RequestParam("id") String id) {
        List<Datas3Model> week = service.findWeek();
        ArrayList<Datas3Model> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        Datas3Model dd = new Datas3Model();
        for (int i = 30; 0 <= i; i--) {
            for (Datas3Model d : week) {
                Timestamp dates = d.getDates();
                //计算日期
                Date da = new Date();
                da = dates;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String format = df.format(new Date(da.getTime() + i * 24 * 60 * 60 * 1000));
                String format1 = df.format(new Date());
                if (format1.equals(format)) {
                    dd = d;
                }
            }
            if (dd.getUuid() != null && !"".equals(dd.getUuid())) {
                list.add(dd);
                dd = new Datas3Model();
            }
        }
        String[] str = new String[list.size() - 1];
        String[] obj = new String[list.size() - 1];
        for (int ii = 0; ii < list.size() - 1; ) {
            String acc_hea = list.get(ii).getAcc_hea();
            ii++;
            Timestamp dates = list.get(ii).getDates();
            String acc_hea2 = list.get(ii).getAcc_hea();
            if (acc_hea2 != null && !"".equals(acc_hea2)) {
                double v = Double.valueOf(acc_hea2) - Double.valueOf(acc_hea);
                String vv = Double.toString(v);
                SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");
                if (dates.getTime() > 0) {
                    String format = sim.format(dates);
                    str[ii - 1] = format;
                    obj[ii - 1] = vv;
                    map.put("dates", str);
                    map.put("v", obj);
                }
            }
        }
        return map;
    }
}