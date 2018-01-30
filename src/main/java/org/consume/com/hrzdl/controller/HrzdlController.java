package org.consume.com.hrzdl.controller;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.consume.com.aspect.service.AspectService;
import org.consume.com.crew.service.CrewService;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.gwgrzq.service.GwgrzqService;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.hrzdl.model.HrzdlModel;
import org.consume.com.hrzdl.service.HrzdlService;
import org.consume.com.qxxx.model.QxxxModel;
import org.consume.com.qxxx.service.QxxxService;
import org.consume.com.rltj.service.serviceImpl.RltjService;
import org.consume.com.sltj.model.SltjModel;
import org.consume.com.sltj.service.SltjService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.util.date.Dates2;
import org.consume.com.util.doubleUtils.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/hrzdl")
public class HrzdlController {
    @Value("${page.pageSize}")
    private Integer pageSize;
    @Autowired
    private RltjService service;
    @Autowired
    private HeatService heatService;
    @Autowired
    private HrzdlService hrzdlService;
    @Autowired
    private StructureService structureService;
    @Autowired
    private SltjService sltjService;
    @Autowired
    private AspectService aspectService;
    @Autowired
    private CrewService crewService;
    @Autowired
    private GwgrzqService gwgrzqService;
    @Autowired
    private QxxxService qxxxService;

    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        List<StructureModel> all = structureService.findAlls("0", "1");
        return new ModelAndView("/hrzdl/index")
                .addObject("page", all);
    }

    @RequestMapping("/qureyid")
    public ModelAndView qureyid(@RequestParam("id") String id) {
        List<SltjModel> byId = sltjService.findById(id);
        for (SltjModel s : byId) {
            double v = Double.parseDouble(s.getV()) / (Double.parseDouble(s.getLx()) / 10000);
            DecimalFormat df = new DecimalFormat("######0.00");
            String format = df.format(v);
            String format1 = df.format(Double.valueOf(s.getV()));
            s.setDdh(Double.valueOf(format));
            s.setV(format1);
        }
        return new ModelAndView("/hrzdl/Exhibition")
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
        List<HrzdlModel> models1 = hrzdlService.getByJzid(id, strDate1, endDate1);

//        获取历史数据
        Map<Integer, List<HrzdlModel>> ma = new HashMap<>();
        for (int i = 1; i < 7; i++) {
            String format2 = sdf.format(Dates2.getPreviousDate2(new Date(), i));
            long strDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format2 + " 00:00:00").getTime();
            long endDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format2 + " 23:59:59").getTime();
            List<HrzdlModel> models2 = hrzdlService.getByJzid(id, strDate2, endDate2);
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
        Page<ExchangeModel> page = hrzdlService.findAllPage(pageNow, pageSize, serach);
        return new ModelAndView("/hrzdl/aspect")
                .addObject("page", page)
                .addObject("serach", serach);
    }

    @RequestMapping("/choiceTime")
    public ModelAndView choiceTime(@RequestParam("id") String id) {
        return new ModelAndView("/hrzdl/choiceTime").addObject("jzid", id);
    }

    @RequestMapping("/confirmTime")
    public ModelAndView confirmTime(@RequestParam("t") String t, @RequestParam("j") String j) throws Exception {
        //切割字符串将周期一到周期七的时间提取出来
        HeatModel model = heatService.getById(j);
        List<QxxxModel> qxxxs = qxxxService.findPage(0, 0);
        DecimalFormat df = new DecimalFormat("######0.00");
        String[] split = t.split("--");
        ArrayList<Object> obj = new ArrayList<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < split.length - 1; i++) {
            Object[] ob = new Object[6];
            String s = split[i];
            i++;
            String s1 = split[i];
            Date p = sim.parse(s);
            Date p1 = sim.parse(s1);
            long l = p.getTime();
            long ll = p1.getTime() + 1000;
            List<SltjModel> list = sltjService.findByTimeAndId3(j, l, ll);
            //            获取最大数据和最小数据
            if (list != null && list.size() > 0) {
                ob[0] = s + "---" + s1;
                ob[2] = model.getNames();
                ob[3] = model.getMj();
//                最大
                String v1 = list.get(0).getV();
//                最小
                String v2;
                if (list.size() > 1)
                    v2 = list.get(list.size() - 1).getV();
                else
                    v2 = "0";
//                相减获取真实电量
                Double sub = Arith.sub(Double.valueOf(v1), Double.valueOf(v2));
                ob[5] = df.format(sub);
//                电单耗
                Double div = Arith.div(sub, Double.valueOf((model.getMj() / 10000) + ""));
                ob[4] = df.format(div);

//                气象
                double zgwd = 0.00;
                double zdwd = 0.00;
                if (qxxxs != null && qxxxs.size() > 0) {
                    zgwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZgwd();
                    zdwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZdwd();
                }
                Double div2 = Arith.div(Arith.add(zgwd, zdwd), Double.valueOf("2"));
                ob[1] = df.format(div2);
                obj.add(ob);
            } else {
                ob[0] = s + "---" + s1;
                ob[2] = model.getNames();
                ob[3] = model.getMj();
                ob[5] = 0;
                ob[4] = 0;

//                气象
                double zgwd = 0.00;
                double zdwd = 0.00;
                if (qxxxs != null && qxxxs.size() > 0) {
                    zgwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZgwd();
                    zdwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZdwd();
                }
                Double div2 = Arith.div(Arith.add(zgwd, zdwd), Double.valueOf("2"));
                ob[1] = df.format(div2);
                obj.add(ob);
            }
        }
        return new ModelAndView("/hrzdl/portrait").addObject("list", obj);
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
        Page<ExchangeModel> page = hrzdlService.findAllPage(pageNow, pageSize, serach);
        return new ModelAndView("/hrzdl/aspect2")
                .addObject("page", page)
                .addObject("A", A)
                .addObject("B", B)
                .addObject("AA", AA)
                .addObject("BB", BB)
                .addObject("serach", serach);
    }

    @RequestMapping("/toTime")
    public ModelAndView toTime(@Param("s") String s) {
        return new ModelAndView("/hrzdl/choiceTime2").addObject("jzid", s);
    }

    @RequestMapping("/confirmTime2")
    public ModelAndView confirmTime2(@RequestParam("t") String t, @RequestParam("j") String j) {
        //切割字符串将周期一到周期七的时间提取出来
        String[] split = t.split("--");
        String[] split1 = j.split(",");
        ArrayList<Object> obj = new ArrayList<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = 0;
        long ll = 0;
        try {
            l = sim.parse(split[0]).getTime();
            ll = sim.parse(split[1]).getTime() + 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < split1.length; i++) {
            List<SltjModel> byId = sltjService.findByTimeAndId(split1[i], l, ll);
            if (byId != null && byId.size() > 0) {
                double v1;
                double v;
                v1 = Double.parseDouble(byId.get(byId.size() - 1).getV()) - Double.parseDouble(byId.get(0).getV());
                v = v1 / (Double.parseDouble(byId.get(byId.size() - 1).getLx()) / 10000);
                double tqwdByTime = aspectService.findTqwdByTime(split[0], split[1]);
                DecimalFormat df = new DecimalFormat("######0.00");
                String format = df.format(v);
                String format2 = df.format(tqwdByTime);
                String format1 = df.format(v1);
                Object[] ob = new Object[6];
                ob[0] = format2;
                ob[1] = byId.get(0).getZdid();
                ob[2] = byId.get(0).getLx();
                ob[3] = format;
                ob[4] = format1;
                obj.add(ob);
            } else {
                double tqwdByTime = aspectService.findTqwdByTime(split[0], split[1]);
                DecimalFormat df = new DecimalFormat("######0.00");
                String format2 = df.format(tqwdByTime);
                Object[] ob = new Object[6];
                ob[0] = format2;
                ob[1] = "";
                ob[2] = "";
                ob[3] = 0;
                ob[4] = 0;
                obj.add(ob);
            }
        }
        return new ModelAndView("/hrzdl/transverse").addObject("list", obj);
    }

    //    站点
//    查看热单耗
    @GetMapping("/inithrz/{pageNow}")
    public ModelAndView queryRdh2(@PathVariable("pageNow") Integer pageNow) {
        Page<HeatModel> page = heatService.findAllPage(pageNow, pageSize);
        return new ModelAndView("/hrzdl/queryRdh2")
                .addObject("page", page);
    }

    @GetMapping("/queryRdhQuery2")
    public ModelAndView queryRdhQuery2(@RequestParam("id") String id) {
        List<HrzdlModel> byId = hrzdlService.findById(id);
        ArrayList<HrzdlModel> list = new ArrayList<>();
        if (byId.size() != 0) {
            HeatModel byId2 = heatService.getById(byId.get(0).getNames());
            byId.get(0).setNames(byId2.getNames());
            byId.get(0).setMj(Double.toString(byId2.getMj()));
            list.add(byId.get(0));
            for (int i = 0; i < byId.size() - 1; i++) {
                String times = byId.get(i).getTimes();
                String times1 = byId.get(i + 1).getTimes();
                if (!byId.get(i).getTimes().equals(byId.get(i + 1).getTimes()) && byId.get(i + 1) != null) {
                    HeatModel byId1 = heatService.getById(byId.get(i + 1).getNames());
                    byId.get(i + 1).setNames(byId1.getNames());
                    byId.get(i + 1).setMj(Double.toString(byId1.getMj()));
                    list.add(byId.get(i + 1));
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.size() != (i + 1)) {
                    double v = (Double.parseDouble(list.get(i).getAcc_hea()) - Double.parseDouble(list.get(i + 1).getAcc_hea()))
                            / (Double.parseDouble(list.get(i).getMj()) / 10000);
                    DecimalFormat df = new DecimalFormat("######0.00");
                    String format = df.format(v);
                    list.get(i).setAcc_hea(format);
                } else {
                    list.remove(i);
                }
            }
        }
        double v = 0;
        double v1 = 0;
        for (HrzdlModel h : list) {
            v += Double.parseDouble(h.getAcc_hea());
        }
        if (list.size() != 0) {
            v1 = v / list.size();
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        String format = df.format(v1);
        return new ModelAndView("/hrzdl/queryRdhList2")
                .addObject("page", list)
                .addObject("v", format);
    }
}
