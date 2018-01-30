package org.consume.com.hrzcbfxdl.controller;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.consume.com.aspect.service.AspectService;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.hrzcbfxdl.service.HrzcbfxdlService;
import org.consume.com.hrzdl.model.HrzdlModel;
import org.consume.com.rltj.service.serviceImpl.RltjService;
import org.consume.com.sdrdj.model.SdrdjModel;
import org.consume.com.sdrdj.service.SdrdjService;
import org.consume.com.sltj.model.SltjModel;
import org.consume.com.sltj.service.SltjService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.util.date.Dates2;
import org.consume.com.util.doubleUtils.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/hrzcbfxdl")
public class HrzcbfxdlController {

    @Autowired
    private RltjService service;
    @Autowired
    private HeatService heatService;
    @Autowired
    private HrzcbfxdlService hrzcbfxdlService;
    @Autowired
    private StructureService structureService;
    @Autowired
    private SltjService sltjService;
    @Autowired
    private AspectService aspectService;
    @Autowired
    private SdrdjService sdrdjService;

    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        List<StructureModel> all = structureService.findAlls("0", "1");
        return new ModelAndView("/hrzcbfxdl/index")
                .addObject("page", all);
    }

    @RequestMapping("/qureyid")
    public ModelAndView qureyid(@RequestParam(value = "id",required = false) String id) {
        List<SdrdjModel> list2 = sdrdjService.finds();
        String dj = "0";
        if (list2 != null && list2.size() > 0)
            dj = list2.get(0).getV2() + "";
        List<SltjModel> byId = sltjService.findById(id);
        DecimalFormat df = new DecimalFormat("######0.00");
        for (SltjModel s : byId) {
            s.setDdh(Double.valueOf(dj));
            s.setUuid(df.format(Double.valueOf(s.getV())));
            s.setV(df.format(Arith.mul(Double.valueOf(dj), Double.valueOf(s.getV()))));
        }
        return new ModelAndView("/hrzcbfxdl/Exhibition")
                .addObject("list", byId);
    }

    @RequestMapping("/qurey")
    public Map<String, Object> qurey(@RequestParam("id") String id) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
//        获取今天开始的和结束的long
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = sdf.format(new Date());
        long strDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format1 + " 00:00:00").getTime();
        long endDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format1 + " 23:59:59").getTime();
//        获取今天的数据
        List<HrzdlModel> models1 = hrzcbfxdlService.getByJzid(id, strDate1, endDate1);

//        获取历史数据
        Map<Integer, List<HrzdlModel>> ma = new HashMap<>();
        for (int i = 1; i < 7; i++) {
            String format2 = sdf.format(Dates2.getPreviousDate2(new Date(), i));
            long strDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format2 + " 00:00:00").getTime();
            long endDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format2 + " 23:59:59").getTime();
            List<HrzdlModel> models2 = hrzcbfxdlService.getByJzid(id, strDate2, endDate2);
            ma.put(i, models2);
        }

        String rq[] = new String[7];
        Double v1[] = new Double[7];

        if (ma.get(6) == null || ma.get(6).size() <= 0) {
            rq[0] = sdf.format(Dates2.getPreviousDate2(new Date(), 6));
            v1[0] = Double.valueOf("0.0");
        } else {
            rq[0] = sdf.format(ma.get(6).get(0).getDates());
            v1[0] = Double.valueOf(ma.get(6).get(0).getAcc_hea());
        }

        if (ma.get(5) == null || ma.get(5).size() <= 0) {
            rq[1] = sdf.format(Dates2.getPreviousDate2(new Date(), 5));
            v1[1] = Double.valueOf("0.0");
        } else {
            rq[1] = sdf.format(ma.get(5).get(0).getDates());
            v1[1] = Double.valueOf(ma.get(5).get(0).getAcc_hea());
        }

        if (ma.get(4) == null || ma.get(4).size() <= 0) {
            rq[2] = sdf.format(Dates2.getPreviousDate2(new Date(), 4));
            v1[2] = Double.valueOf("0.0");
        } else {
            rq[2] = sdf.format(ma.get(4).get(0).getDates());
            v1[2] = Double.valueOf(ma.get(4).get(0).getAcc_hea());
        }
        if (ma.get(3) == null || ma.get(3).size() <= 0) {
            rq[3] = sdf.format(Dates2.getPreviousDate2(new Date(), 3));
            v1[3] = Double.valueOf("0.0");
        } else {
            rq[3] = sdf.format(ma.get(3).get(0).getDates());
            v1[3] = Double.valueOf(ma.get(3).get(0).getAcc_hea());
        }
        if (ma.get(2) == null || ma.get(2).size() <= 0) {
            rq[4] = sdf.format(Dates2.getPreviousDate2(new Date(), 2));
            v1[4] = Double.valueOf("0.0");
        } else {
            rq[4] = sdf.format(ma.get(2).get(0).getDates());
            v1[4] = Double.valueOf(ma.get(2).get(0).getAcc_hea());
        }

        if (ma.get(1) == null || ma.get(1).size() <= 0) {
            rq[5] = sdf.format(Dates2.getPreviousDate2(new Date(), 1));
            v1[5] = Double.valueOf("0.0");
        } else {
            rq[5] = sdf.format(ma.get(1).get(0).getDates());
            v1[5] = Double.valueOf(ma.get(1).get(0).getAcc_hea());
        }
        if (models1 == null || models1.size() <= 0) {
            rq[6] = sdf.format(Dates2.getPreviousDate2(new Date(), 0));
            v1[6] = Double.valueOf("0.0");
        } else {
            rq[6] = sdf.format(models1.get(0).getDates());
            v1[6] = Double.valueOf(models1.get(0).getAcc_hea());
        }

        String rq1[] = new String[6];
        Double v11[] = new Double[6];

        rq1[0] = rq[1];
        rq1[1] = rq[2];
        rq1[2] = rq[3];
        rq1[3] = rq[4];
        rq1[4] = rq[5];
        rq1[5] = rq[6];

        v11[0] = v1[1] - v1[0];
        v11[1] = v1[2] - v1[1];
        v11[2] = v1[3] - v1[2];
        v11[3] = v1[4] - v1[3];
        v11[4] = v1[5] - v1[4];
        v11[5] = v1[6] - v1[5];

        map.put("k", rq1);
        map.put("v", v11);
        return map;
    }

    @GetMapping("/init1")
    public ModelAndView init1(@RequestParam(value = "pageNow", required = false) Integer pageNow,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                              @RequestParam(value = "serach", required = false) String serach) {
        if (pageNow == null) {
            pageNow = 0;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 5;
        }
        Page<ExchangeModel> page = hrzcbfxdlService.findAllPage(pageNow, pageSize, serach);
        return new ModelAndView("/hrzcbfxdl/aspect")
                .addObject("page", page)
                .addObject("serach", serach);
    }

    @RequestMapping("/choiceTime")
    public ModelAndView choiceTime(@RequestParam("id") String id) {
        return new ModelAndView("/hrzcbfxdl/choiceTime").addObject("jzid", id);
    }

    @RequestMapping("/confirmTime")
    public ModelAndView confirmTime(@RequestParam("t") String t, @RequestParam("j") String j) throws Exception {
        //切割字符串将周期一到周期七的时间提取出来
        HeatModel model = heatService.getById(j);
        List<SdrdjModel> list2 = sdrdjService.finds();
        String dj = "0";
        if (list2 != null && list2.size() > 0)
            dj = list2.get(0).getV2() + "";

        String[] split = t.split("--");
        ArrayList<Object> obj = new ArrayList<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df = new DecimalFormat("######0.00");
        for (int i = 0; i < split.length; i++) {
            Object[] ob = new Object[7];
            String s = split[i];
            i++;
            String s1 = split[i];
            Date p = sim.parse(s);
            Date p1 = sim.parse(s1);
            long l = p.getTime();
            long ll = p1.getTime();
            List<SltjModel> byId = sltjService.findByTimeAndId(j, l, ll);
            if (byId != null && byId.size() > 0) {
                double d = 0;
                if(byId != null && byId.size() > 0)
                {
                    d = Double.parseDouble(byId.get(0).getV()) - Double.parseDouble(byId.get(byId.size()-1).getV());
                }
                double tqwdByTime = aspectService.findTqwdByTime(s, s1);
                ob[0] = s + "---" + s1;
                ob[1] = df.format(tqwdByTime);
                ob[2] = model.getNames();
                ob[3] = dj;
                ob[4] = df.format(d);
                Double mul = Arith.mul(Double.valueOf(dj), d);
                ob[5] = df.format(mul);
                ob[6] = byId.get(0).getJzwlx();
                obj.add(ob);
            } else {
                double tqwdByTime = aspectService.findTqwdByTime(s, s1);
                ob[0] = s + "---" + s1;
                ob[1] = df.format(tqwdByTime);
                ob[2] = model.getNames();
                ob[3] = dj;
                ob[4] = 0;
                ob[5] = 0;
                ob[6] = "";
                obj.add(ob);
            }
        }
        return new ModelAndView("/hrzcbfxdl/portrait").addObject("list", obj);
    }

    @RequestMapping("/init2")
    public ModelAndView init2(@RequestParam(value = "pageNow", required = false) Integer pageNow,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                              @RequestParam(value = "serach", required = false) String serach,
                              @RequestParam(value = "A", required = false) String A,
                              @RequestParam(value = "AA", required = false) String AA,
                              @RequestParam(value = "B", required = false) String B,
                              @RequestParam(value = "BB", required = false) String BB) {
        if (pageNow == null) {
            pageNow = 0;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 5;
        }
        Page<ExchangeModel> page = hrzcbfxdlService.findAllPage(pageNow, pageSize, serach);
        return new ModelAndView("/hrzcbfxdl/aspect2")
                .addObject("page", page)
                .addObject("A", A)
                .addObject("B", B)
                .addObject("AA", AA)
                .addObject("BB", BB)
                .addObject("serach", serach);
    }

    @RequestMapping("/toTime")
    public ModelAndView toTime(@Param("s") String s) {
        return new ModelAndView("/hrzcbfxdl/choiceTime2").addObject("jzid", s);
    }

    @RequestMapping("/confirmTime2")
    public ModelAndView confirmTime2(@RequestParam("t") String t, @RequestParam("j") String j) {
        //切割字符串将周期一到周期七的时间提取出来
        List<SdrdjModel> list2 = sdrdjService.finds();
        String dj = "0";
        if (list2 != null && list2.size() > 0)
            dj = list2.get(0).getV2() + "";

        String[] split = t.split("--");
        String[] split1 = j.split(",");
        ArrayList<Object> obj = new ArrayList<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = 0;
        long ll = 0;
        try {
            l = sim.parse(split[0]).getTime();
            ll = sim.parse(split[1]).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < split1.length; i++) {
            List<SltjModel> byId = sltjService.findByTimeAndId(split1[i], l, ll);
            double d = 0;
            double v = 0;
            if (byId != null && byId.size() > 0) {
                v = d / (Double.parseDouble(byId.get(0).getLx()) * byId.size());
                if(byId != null && byId.size() > 0)
                {
                    d = Double.parseDouble(byId.get(0).getV()) - Double.parseDouble(byId.get(byId.size()-1).getV());
                }
                double tqwdByTime = aspectService.findTqwdByTime(split[0], split[1]);
                BigDecimal d1 = new BigDecimal(Double.toString(d));
                BigDecimal d12 = new BigDecimal(Double.toString(tqwdByTime));
                BigDecimal d2 = new BigDecimal(Integer.toString(1));
                // 四舍五入,保留2位小数
                String st = d1.divide(d2, 2, BigDecimal.ROUND_HALF_UP).toString();
                String st2 = d12.divide(d2, 2, BigDecimal.ROUND_HALF_UP).toString();
                Object[] ob = new Object[8];
                ob[0] = st2;
                ob[1] = byId.get(0).getZdid();
                ob[2] = byId.get(0).getLx();
                ob[3] = dj;
                ob[4] = st;
                Double mul = Arith.mul(Double.valueOf(dj), d);
                BigDecimal d11 = new BigDecimal(Double.toString(mul));
                BigDecimal d21 = new BigDecimal(Integer.toString(1));
                // 四舍五入,保留2位小数
                String st1 = d11.divide(d21, 2, BigDecimal.ROUND_HALF_UP).toString();
                ob[5] = st1;
                ob[6] = byId.get(0).getJzwlx();
                ob[7] = split[0] +"--"+ split[1];
                obj.add(ob);
            }
        }
        return new ModelAndView("/hrzcbfxdl/transverse2").addObject("list", obj);
    }
}
