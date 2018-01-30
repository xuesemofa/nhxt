package org.consume.com.unitcomparison.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.crew.service.CrewService;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.heatattribute.service.AttributeService;
import org.consume.com.opc.model.Datas2Model;
import org.consume.com.opc.service.OPC2Service;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.unitcomparison.model.UnitComparisonModel;
import org.consume.com.unitcomparison.service.UnitcomparisonService;
import org.consume.com.user.model.UserModel;
import org.consume.com.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机组对比
 *
 * @shiro unitcomparison:
 */
@RestController
@RequestMapping("/unitcomparison")
public class UnitComparisonController {
    private static String view = "/unitcomparison/";

    @Autowired
    private UnitcomparisonService service;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private UnitcomparisonService unitcomparisonService;
    @Autowired
    private OPC2Service opc2Service;
    @Autowired
    private CrewService crewService;
    @Autowired
    private HeatService heatService;
    @Autowired
    private StructureService structureService;


//    @RequiresPermissions(value = "unitcomparison:init")
    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        Page<UnitComparisonModel> page = service.findAllPage2(pageNow, 5);
        for (UnitComparisonModel u:page)
        {
            String[] split = u.getJzaid().split(",");
            String a = "";
            for (String s : split)
            {
                CrewModel byId = crewService.getById(s);
                HeatModel byId1 = heatService.getById(byId.getParents());
                StructureModel byId2 = structureService.getById(byId1.getParents());
                a += byId2.getNames() + "-" + byId1.getNames() + "-" + byId.getNames();
            }
            u.setJzname(a);
        }
        return new ModelAndView(view + "index")
                .addObject("page", page);
    }

    @GetMapping("/del")
    public ModelAndView del(@RequestParam("id") String id) {
        service.del(id);
        return new ModelAndView("redirect:/unitcomparison/init/0");
    }

    /**
     * 跳转对比添加
     *
     * @return
     */
//    @RequiresPermissions(value = "unitcomparison:add")
    @GetMapping("/toDB")
    public ModelAndView toDB(@RequestParam(value = "pageNow") Integer pageNow,
                             @RequestParam(value = "pageSize") Integer pageSize,
                             @RequestParam(value = "serach", required = false) String serach,
                             @RequestParam(value = "A", required = false) String A,
                             @RequestParam(value = "AA", required = false) String AA,
                             @RequestParam(value = "B", required = false) String B,
                             @RequestParam(value = "BB", required = false) String BB) {
        Page<ExchangeModel> page = service.findAllPage(pageNow, pageSize, serach);
        return new ModelAndView(view + "add")
                .addObject("page", page)
                .addObject("A", A)
                .addObject("B", B)
                .addObject("AA", AA)
                .addObject("BB", BB)
                .addObject("serach", serach);
    }

//    @RequiresPermissions(value = "unitcomparison:add")
    @PostMapping("/add")
    public ResponseResult add(@RequestParam(value = "A") String A,
                              @RequestParam(value = "B") String B) {
        ResponseResult r = new ResponseResult();
        if ((A == null || B == null) || (A.equals("") || B.equals(""))) {
            r.setSuccess(false);
            r.setErrorcode("对比项不能为空");
        } else {
            UnitComparisonModel u = new UnitComparisonModel();
            Subject subject = SecurityUtils.getSubject();
            UserModel user = (UserModel) subject.getSession().getAttribute("user");
            u.setAccid(user.getAccount());
            u.setJzaid(A);
            u.setJzbid(B);
            u.setScsj(new Timestamp(System.currentTimeMillis()));
            u.setZhlx(0);
            int i = service.add(u);
            switch (i) {
                case 1:
                    r.setSuccess(true);
                    break;
                default:
                    r.setSuccess(false);
                    r.setErrorcode("数据库操作错误");
                    break;
            }
        }
        return r;
    }

    /**
     * 根据id查询要对比的两个机组
     *
     * @param id
     * @return
     */
//    @RequiresPermissions(value = "unitcomparison:query")
    @GetMapping("/query")
    public ModelAndView query(@RequestParam("id") String id) {
        //        判断有无设置对比项
//        List<AttributeModel> db = attributeService.findDB();
//        if (db == null || db.size() <= 0)
//            return new ModelAndView(view + "error");
        UnitComparisonModel model = service.getById(id);
        return new ModelAndView(view + "query")
                .addObject("model", model)
                .addObject("id", id);
    }

    /**
     * 获取对比数据json
     *
     * @param id
     * @return
     */
//    @RequiresPermissions(value = "unitcomparison:getJson")
    @PostMapping("/getJson")
    public Map<String, Object> getJson(@RequestParam("id") String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
//获取需要对比的两个机组
        UnitComparisonModel unitComparisonModel = unitcomparisonService.getById(id);
//查询两个机组的数据
        //        获取当前年份
        Calendar now = Calendar.getInstance();
        int i = now.get(Calendar.YEAR);
        Map<Integer, Datas2Model> mapa = new HashMap<>();
        for (int j = 1; j < 13; j++) {
            if (j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 12) {
                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-31 24:59:59").getTime();
                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzaid(), oneStrat, oneEnd);
                if (jza != null && jza.size() > 0) {
                    mapa.put(j, jza.get(0));
                } else {
                    Datas2Model d = new Datas2Model();
                    mapa.put(j, d);
                }
            } else if (j == 4 || j == 6 || j == 9 || j == 11) {
                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-30 24:59:59").getTime();
                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzaid(), oneStrat, oneEnd);
                if (jza != null && jza.size() > 0) {
                    mapa.put(j, jza.get(0));
                } else {
                    Datas2Model d = new Datas2Model();
                    mapa.put(j, d);
                }
            } else if (j == 2) {
                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-28 24:59:59").getTime();
                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzaid(), oneStrat, oneEnd);
                if (jza != null && jza.size() > 0) {
                    mapa.put(j, jza.get(0));
                } else {
                    Datas2Model d = new Datas2Model();
                    mapa.put(j, d);
                }
            }
        }

        Map<Integer, Datas2Model> mapb = new HashMap<>();
        for (int j = 1; j < 13; j++) {
            if (j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 12) {
                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-31 24:59:59").getTime();
                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzbid(), oneStrat, oneEnd);
                if (jza != null && jza.size() > 0) {
                    mapb.put(j, jza.get(0));
                } else {
                    Datas2Model d = new Datas2Model();
                    mapb.put(j, d);
                }
            } else if (j == 4 || j == 6 || j == 9 || j == 11) {
                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-30 24:59:59").getTime();
                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzbid(), oneStrat, oneEnd);
                if (jza != null && jza.size() > 0) {
                    mapb.put(j, jza.get(0));
                } else {
                    Datas2Model d = new Datas2Model();
                    mapb.put(j, d);
                }
            } else if (j == 2) {
                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-28 24:59:59").getTime();
                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzbid(), oneStrat, oneEnd);
                if (jza != null && jza.size() > 0) {
                    mapb.put(j, jza.get(0));
                } else {
                    Datas2Model d = new Datas2Model();
                    mapb.put(j, d);
                }
            }
        }
//        供温回温
        double[] hw_a_data = new double[12];
        double[] hw_b_data = new double[12];
        double[] gw_b_data = new double[12];
        double[] gw_a_data = new double[12];

        for (int j = 0; j < mapa.size(); j++) {
            gw_a_data[j] = Double.valueOf(mapa.get(j + 1).getT11());
            hw_a_data[j] = Double.valueOf(mapa.get(j + 1).getT12());
        }
        for (int j = 0; j < mapb.size(); j++) {
            gw_b_data[j] = Double.valueOf(mapb.get(j + 1).getT11());
            hw_b_data[j] = Double.valueOf(mapb.get(j + 1).getT12());
        }

        double[] pj_a_data = new double[12];
//        for (int j = 0; j < mapa.size(); j++) {
//            pj_a_data[j] = Double.valueOf(mapa.get(j - 1).getT12());
//        }
        double[] pj_b_data = new double[12];
//        for (int j = 0; j < mapa.size(); j++) {
//            pj_b_data[j] = Double.valueOf(mapb.get(j - 1).getT12());
//        }

        //        压力
        double[] hy_a_data = new double[12];
        double[] hy_b_data = new double[12];
        double[] gy_b_data = new double[12];
        double[] gy_a_data = new double[12];

        for (int j = 0; j < mapa.size(); j++) {
            gy_a_data[j] = Double.valueOf(mapa.get(j + 1).getP11());
            hy_a_data[j] = Double.valueOf(mapa.get(j + 1).getP12());
        }
        for (int j = 0; j < mapb.size(); j++) {
            gy_b_data[j] = Double.valueOf(mapb.get(j + 1).getP11());
            hy_b_data[j] = Double.valueOf(mapb.get(j + 1).getP12());
        }

        double[] pjy_a_data = new double[12];
//        for (int j = 0; j < mapa.size(); j++) {
//            pj_a_data[j] = Double.valueOf(mapa.get(j - 1).getT12());
//        }
        double[] pjy_b_data = new double[12];
//        for (int j = 0; j < mapa.size(); j++) {
//            pj_b_data[j] = Double.valueOf(mapb.get(j - 1).getT12());
//        }

        map.put("gw_a_data", gw_a_data);
        map.put("gw_b_data", gw_b_data);
        map.put("hw_a_data", hw_a_data);
        map.put("hw_b_data", hw_b_data);

        map.put("gy_a_data", gy_a_data);
        map.put("gy_b_data", gy_b_data);
        map.put("hy_a_data", hy_a_data);
        map.put("hy_b_data", hy_b_data);

//        map.put("pj_a_data", new double[]{2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2});
//        map.put("pj_b_data", new double[]{2.0, 2.2, 3.3, 9.5, 6.3, 15.2, 20.3, 23.4, 24.0, 16.5, 19.0, 6.2});
        map.put("title_data", new String[]{"1号一网供温", "1号一网回温", "2号一网供温", "2号一网回温"});//, "1号平均温度", "2号平均温度"});
        map.put("title_data2", new String[]{"1号一网供压", "1号一网回压", "2号一网供压", "2号一网回压"});//, "1号平均温度", "2号平均温度"});

//二网
        //        供温回温
        double[] hw_a_data2 = new double[12];
        double[] hw_b_data2 = new double[12];
        double[] gw_b_data2 = new double[12];
        double[] gw_a_data2 = new double[12];

        for (int j = 0; j < mapa.size(); j++) {
            gw_a_data2[j] = Double.valueOf(mapa.get(j + 1).getT21());
            hw_a_data2[j] = Double.valueOf(mapa.get(j + 1).getT22());
        }
        for (int j = 0; j < mapb.size(); j++) {
            gw_b_data2[j] = Double.valueOf(mapb.get(j + 1).getT21());
            hw_b_data2[j] = Double.valueOf(mapb.get(j + 1).getT22());
        }

        double[] pj_a_data2 = new double[12];
//        for (int j = 0; j < mapa.size(); j++) {
//            pj_a_data[j] = Double.valueOf(mapa.get(j - 1).getT12());
//        }
        double[] pj_b_data2 = new double[12];
//        for (int j = 0; j < mapa.size(); j++) {
//            pj_b_data[j] = Double.valueOf(mapb.get(j - 1).getT12());
//        }

        //        压力
        double[] hy_a_data2 = new double[12];
        double[] hy_b_data2 = new double[12];
        double[] gy_b_data2 = new double[12];
        double[] gy_a_data2 = new double[12];

        for (int j = 0; j < mapa.size(); j++) {
            gy_a_data2[j] = Double.valueOf(mapa.get(j + 1).getP21());
            hy_a_data2[j] = Double.valueOf(mapa.get(j + 1).getP22());
        }
        for (int j = 0; j < mapb.size(); j++) {
            gy_b_data2[j] = Double.valueOf(mapb.get(j + 1).getP21());
            hy_b_data2[j] = Double.valueOf(mapb.get(j + 1).getP22());
        }

        double[] pjy_a_data2 = new double[12];
//        for (int j = 0; j < mapa.size(); j++) {
//            pj_a_data[j] = Double.valueOf(mapa.get(j - 1).getT12());
//        }
        double[] pjy_b_data2 = new double[12];
//        for (int j = 0; j < mapa.size(); j++) {
//            pj_b_data[j] = Double.valueOf(mapb.get(j - 1).getT12());
//        }

        map.put("gw_a_data2", gw_a_data2);
        map.put("gw_b_data2", gw_b_data2);
        map.put("hw_a_data2", hw_a_data2);
        map.put("hw_b_data2", hw_b_data2);

        map.put("gy_a_data2", gy_a_data2);
        map.put("gy_b_data2", gy_b_data2);
        map.put("hy_a_data2", hy_a_data2);
        map.put("hy_b_data2", hy_b_data2);

//        map.put("pj_a_data2", new double[]{2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2});
//        map.put("pj_b_data2", new double[]{2.0, 2.2, 3.3, 9.5, 6.3, 15.2, 20.3, 23.4, 24.0, 16.5, 19.0, 6.2});
        map.put("title_data21", new String[]{"1号二网供温", "1号二网回温", "2号二网供温", "2号二网回温"});//, "1号平均温度", "2号平均温度"});
        map.put("title_data22", new String[]{"1号二网供压", "1号二网回压", "2号二网供压", "2号二网回压"});//, "1号平均温度", "2号平均温度"});
        return map;
    }
}
