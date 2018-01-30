package org.consume.com.hrzddhpm.controller;

import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.hrzddhpm.model.HrzddhpmModel;
import org.consume.com.hrzddhpm.service.HrzddhpmService;
import org.consume.com.sltj.model.SltjModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/hrzddhpm")
public class HrzddhpmController {

    private static String views = "/hrzddhpm";
    @Autowired
    private HeatService heatService;
    @Autowired
    private HrzddhpmService service;

    @RequestMapping("/init")
    public ModelAndView init() {
        List<String> obj = new ArrayList<String>();
        List<String> object = new ArrayList<String>();
        List<SltjModel> list = new ArrayList<SltjModel>();
        obj.add("市公安局");
        obj.add("一零二号小区");
        obj.add("香格里拉");
        obj.add("庞大汽车城");
        obj.add("公元2099C区");
        obj.add("圣水雅阁");
        obj.add("市政府");
        obj.add("御景生态家园");
        obj.add("政务中心");
        for (int i = 0; i < obj.size() - 1; i++) {
            HeatModel byNames = heatService.getByNames(obj.get(i));
            object.add(byNames.getUuid());
        }
        List<SltjModel> hrzddhpm = service.findHrzddhpm(object);
        SltjModel slt = new SltjModel();
        //取出每天每个站点最后的数据记录
        for (int i = 0; i < object.size() - 1; i++) {
            for (int ii = 0; ii < hrzddhpm.size() - 1; ii++) {
                Date date = new Date();
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                //将long类型转换为Date类型
                Date date1 = new Date(hrzddhpm.get(ii).getDates());
                //对日期进行计算
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date1);
                calendar.add(Calendar.DATE, i);
                //将日期转换为字符串
                String format = sim.format(date);
                String format1 = sim.format(calendar.getTime());
                if (format.equals(format1)) {
                    slt = hrzddhpm.get(ii);
                }
                if (slt.getUuid() != null
                        && !"".equals(slt.getUuid())
                        && !(hrzddhpm.get(ii).getZdid().equals(hrzddhpm.get(ii).getZdid()))) {
                    list.add(slt);
                    slt = new SltjModel();
                }
            }
        }
        //开始对筛选出来的数据进行计算,
        List<HrzddhpmModel[]> hrzlist = new ArrayList<>();
        for (int i = 0; i < object.size() - 1; i++) {
            HrzddhpmModel[] dou = new HrzddhpmModel[object.size()];
            for (int ii = 0; ii < list.size() - 1; ) {
                String v = list.get(ii).getV();
                Date dates = new Date(list.get(ii).getDates());
                ii++;
                String v1 = list.get(ii).getV();
                if (list.get(ii - 1).getZdid().equals(list.get(ii).getZdid())) {
                    HrzddhpmModel hr = new HrzddhpmModel();
                    if ("8ce459ae8590481ca7fc521bbffd0e10".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 239150.2;
                        hr.setHdl(v2);
                    }
                    if ("541b06778c7f42d5ac0860e8768be091".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 72088.01;
                        hr.setHdl(v2);
                    }
                    if ("ebdb6542cafb402c9df7c98502a1164a".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 79882.41;
                        hr.setHdl(v2);
                    }
                    if ("8e016b78f3734d28a160f84cd3105dfc".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 30442.62;
                        hr.setHdl(v2);
                    }
                    if ("b1c60e0e6d8f4bc29bf2c4c2b6dc2d52".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 270783.57;
                        hr.setHdl(v2);
                    }
                    if ("01daae053c6746e68332a27e73c16ba8".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 282306.43;
                        hr.setHdl(v2);
                    }
                    if ("38649d724824436abb994325f4d7268f".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 40578.6;
                        hr.setHdl(v2);
                    }
                    if ("409521f8bd214498acdaba91cc95fcaf".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 162369.07;
                        hr.setHdl(v2);
                    }
                    if ("21cc859d839f492197d550608f56e538".equals(list.get(ii).getZdid())) {
                        double v2 = Double.valueOf(v) - Double.valueOf(v1);
                        v2 = v2 / 36331.06;
                        hr.setHdl(v2);
                    }
                    SimpleDateFormat sim = new SimpleDateFormat("yyyy=MM-dd");
                    hr.setDates(sim.format(dates));
                    HeatModel byId = heatService.getById(list.get(ii - 1).getZdid());
                    hr.setZdname(byId.getNames());
                    dou[ii] = hr;
                    if (dou != null) {
                        hrzlist.add(i, dou);
                        dou = new HrzddhpmModel[object.size()];
                    }
                }
            }
        }
        //查出hrzlist中长度最长的数组
        List<HrzddhpmModel[]> hrzSizeList = hrzlist;
        for (int i = 1; i < hrzSizeList.size() - 1; i++) {
            int le = hrzSizeList.get(0).length;
            int le2 = hrzSizeList.get(i).length;
            if (le < le2) {
                HrzddhpmModel[] hrzd = hrzSizeList.get(i);
                hrzSizeList.add(0, hrzd);
            }
        }
        //此处将数组中放置同一机组不同时间转换为数组中放置同一时间不同机组
        HrzddhpmModel[][] pm = new HrzddhpmModel[hrzSizeList.get(0).length][hrzSizeList.get(0).length];
        for (int i = 0; i < hrzlist.size() - 1; i++) {
            for (int ii = 0; ii < hrzlist.get(i).length - 1; ii++) {
                HrzddhpmModel[] hrzddhpmModels = hrzlist.get(i);
                pm[ii][i] = hrzddhpmModels[ii];
            }
        }
        //此处比较同一时间不同机组的电单耗值的大小并进行冒泡排序
        for (int i = 0; i < pm.length - 1; i++) {
            for (int ii = 0; ii < pm[i].length - 1; ii++) {
                for (int iii = ii; iii < pm[i].length - 1; iii++) {
                    if (pm[i][ii].getHdl() < pm[i][iii].getHdl()) {
                        swap(pm[i][ii], pm[i][iii]);
                    }
                }
            }
        }
        ArrayList<HrzddhpmModel[]> h = new ArrayList<>();
        for (int i = 0; i < pm.length - 1; i++) {
            h.add(pm[i]);
        }
        return new ModelAndView(views + "/hrzddhpm").addObject("h", h);
    }

    void swap(HrzddhpmModel a, HrzddhpmModel b) {
        HrzddhpmModel c;
        c = a;
        a = b;
        b = c;
    }
}
