package org.consume.com.ryssgk.controller;

import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.opc.model.Datas3Model;
import org.consume.com.ryssgk.service.RyssgkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据查询
 */
@RestController
@RequestMapping("/ryssgk")
public class RyssgkController {

    private static String views = "/publics/";
    @Autowired
    private RyssgkService service;

    //    @RequiresPermissions(value = "ryssgk:init")
    @RequestMapping("/init")
    public ModelAndView init() {
        Datas3Model all = service.findNewest();
        return new ModelAndView(views + "shishi").addObject("Datas3Model", all);
    }

    //    @RequiresPermissions(value = "ryssgk:findByFour")
    @RequestMapping("/findByFour")
    public Map<String, Object> findByFour(@RequestParam("t") String t) {
        Map<String, Object> map = new HashMap<>();
        List<Datas3Model> byFour = service.findByFour(t);
        String[] datas_data = new String[byFour.size()];
        double[] ywgw_data = new double[byFour.size()];
        double[] ywhw_data = new double[byFour.size()];
        double[] ywgy_data = new double[byFour.size()];
        double[] ywhy_data = new double[byFour.size()];
        double[] ywgsss_data = new double[byFour.size()];
        double[] ywhsss_data = new double[byFour.size()];
        double[] ywss_data = new double[byFour.size()];
        double[] ljrl_data = new double[byFour.size()];
        double[] jdfh_1_data = new double[byFour.size()];
        double[] jdfh_2_data = new double[byFour.size()];
        for (int i = 0; i < byFour.size(); i++) {
            ywgw_data[i] = Double.valueOf(byFour.get(i).getT11());
            ywhw_data[i] = Double.valueOf(byFour.get(i).getT12());
            ywgy_data[i] = Double.valueOf(byFour.get(i).getP11());
            ywhy_data[i] = Double.valueOf(byFour.get(i).getP12());
            ywgsss_data[i] = Double.valueOf(byFour.get(i).getIns_hea());
            ywhsss_data[i] = Double.valueOf(byFour.get(i).getIns_flo());
            ywss_data[i] = Double.valueOf(byFour.get(i).getIns());
            ljrl_data[i] = Double.valueOf(byFour.get(i).getAcc_hea());
            jdfh_1_data[i] = Double.valueOf(byFour.get(i).getEL1());
            jdfh_2_data[i] = Double.valueOf(byFour.get(i).getEL2());
            if (byFour.get(i).getDates() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sDateTime = sdf.format(byFour.get(i).getDates());  //得到精确到秒的表示：08/31/2006 21:08:00

                datas_data[i] = sDateTime;
            } else {
                datas_data[i] = "####-##-## ##:##:##";
            }
        }
        map.put("datas_data", datas_data);
        map.put("ywgw_data", ywgw_data);
        map.put("ywhw_data", ywhw_data);
        map.put("ywgy_data", ywgy_data);
        map.put("ywhy_data", ywhy_data);
        map.put("ywgsss_data", ywgsss_data);
        map.put("ywhsss_data", ywhsss_data);
        map.put("ywss_data", ywss_data);
        map.put("ljrl_data", ljrl_data);
        map.put("jdfh_1_data", jdfh_1_data);
        map.put("jdfh_2_data", jdfh_2_data);
        map.put("title_data", new String[]{"一网供温", "一网回温"});
        map.put("title_data2", new String[]{"一网供压", "一网回压"});
        map.put("title_data3", new String[]{"一网供水瞬时流量", "一网回水瞬时流量"});
        map.put("title_data4", new String[]{"一网瞬时热量"});
        map.put("title_data5", new String[]{"累计热量"});
        map.put("title_data6", new String[]{"#1机电负荷", "#2机电负荷"});
        return map;
    }

    @GetMapping("/choseRq")
    public ModelAndView choseRq() {
        return new ModelAndView("/ryssgk/choiceTime");
    }

    @RequestMapping("/confirmTime")
    public ModelAndView confirmTime(@RequestParam("rq") String rq,@RequestParam("mj") String mj) throws Exception {
        String[] ss = rq.split(",");
        String[] split1 = mj.split(",");
        Map<Integer, long[]> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < ss.length; i++) {
            long[] l = new long[2];
            String[] split = ss[i].split("]");
            if (split.length > 1) {
                l[0] = sdf.parse(ss[i].split("]")[0]).getTime();
                l[1] = sdf.parse(ss[i].split("]")[1]).getTime();
                map.put(i + 1, l);
            }
        }
        List<HrzrlFxModel> list = service.findByRq(map,split1);
        return new ModelAndView("/ryssgk/querySj")
                .addObject("list", list);
    }

    @GetMapping("/choseRq2")
    public ModelAndView choseRq2() {
        return new ModelAndView("/ryssgk/choiceTime2");
    }

    @RequestMapping("/confirmTime2")
    public ModelAndView confirmTime2(@RequestParam("rq") String rq,@RequestParam("mj") String mj) throws Exception {
        String[] ss = rq.split(",");
        String[] split1 = mj.split(",");
        Map<Integer, long[]> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < ss.length; i++) {
            long[] l = new long[2];
            String[] split = ss[i].split("]");
            if (split.length > 1) {
                l[0] = sdf.parse(ss[i].split("]")[0]).getTime();
                l[1] = sdf.parse(ss[i].split("]")[1]).getTime();
                map.put(i + 1, l);
            }
        }
        List<HrzrlFxModel> list = service.findByRq2(map,split1);
        return new ModelAndView("/ryssgk/querySj2")
                .addObject("list", list);
    }
}
