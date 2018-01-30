package org.consume.com.hrzrl.controller;

import com.github.pagehelper.Page;
import org.consume.com.aspect.service.AspectService;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.crew.service.CrewService;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.gwgrzq.model.GwgrzqModel;
import org.consume.com.gwgrzq.service.GwgrzqService;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.hrzdl.service.HrzdlService;
import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.hrzrl.service.HrzrlService;
import org.consume.com.qxxx.model.QxxxModel;
import org.consume.com.qxxx.service.QxxxService;
import org.consume.com.rltj.service.serviceImpl.RltjService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.util.date.Dates2;
import org.consume.com.util.excel.CreateSimpleExcelToDisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/hrzrl")
public class HrzrlController {

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Autowired
    private RltjService service;
    @Autowired
    private CrewService crewService;
    @Autowired
    private HrzrlService hrzrlService;
    @Autowired
    private StructureService structureService;
    @Autowired
    private GwgrzqService gwgrzqService;
    @Autowired
    private HeatService heatService;
    @Autowired
    private HrzdlService hrzdlService;
    @Autowired
    private AspectService aspectService;
    @Autowired
    private QxxxService qxxxService;

    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
//        Page<CrewModel> page = crewService.findAllPage(pageNow, 5);
        List<StructureModel> page = structureService.findAlls("0", "1");
        return new ModelAndView("/hrzrl/index2")
                .addObject("page", page);
    }

   /* @GetMapping("/init1/{pageNow}")
    public ModelAndView init1(@PathVariable("pageNow") Integer pageNow) {
//        Page<CrewModel> page = crewService.findAllPage(pageNow, 5);
        List<StructureModel> page = structureService.findAlls("0", "1");
        return new ModelAndView("/hrzrl/index3")
                .addObject("page", page);
    }*/

    /**
     * 按分公司查看
     *
     * @param id 分公司id
     * @return
     * @throws Exception
     */
//    @RequiresPermissions(value = "exchange:queryByCompany")
    @GetMapping("/queryByCompany")
    public ModelAndView querys(@RequestParam("id") String id,
                               @RequestParam(value = "zd", required = false) String zd,
                               @RequestParam(value = "px", required = false) String px) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, id);
        if (zd != null && px != null) {
            map.put(2, zd);
            map.put(3, px);
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        map.put(4, sf.format(new Date()));
        map.put(5, sf.format(Dates2.getPreviousDate2(new Date(), 1)));
        List<GwgrzqModel> gwgrzqModels = gwgrzqService.find();
        Date date;
        if (gwgrzqModels == null || gwgrzqModels.size() <= 0)
            date = new Date();
        else
            date = gwgrzqModels.get(0).getStrat_day();

        long l = (new Date().getTime() - date.getTime())/1000;
        map.put(6, l + "");

        List<HrzrlFxModel> list = hrzrlService.getJzs(map);
        StructureModel model = structureService.getById(id);
        return new ModelAndView("/hrzrl/query")
                .addObject("list", list)
                .addObject("model", model)
                .addObject("zd", zd)
                .addObject("px", px);
    }

    @GetMapping("/queryByCompany2")
    public ModelAndView querys2(@RequestParam("id") String id,
                                @RequestParam(value = "zd", required = false) String zd,
                                @RequestParam(value = "px", required = false) String px) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, id);
        if (zd != null && px != null) {
            map.put(2, zd);
            map.put(3, px);
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        map.put(4, sf.format(new Date()));
        map.put(5, sf.format(Dates2.getPreviousDate2(new Date(), 1)));
        List<GwgrzqModel> gwgrzqModels = gwgrzqService.find();
        Date date;
        if (gwgrzqModels == null || gwgrzqModels.size() <= 0)
            date = new Date();
        else
            date = gwgrzqModels.get(0).getStrat_day();

        long l = (new Date().getTime() - date.getTime())/1000;
        map.put(6, l + "");
        List<HrzrlFxModel> list = hrzrlService.getJzs2(map);
        StructureModel model = structureService.getById(id);
        return new ModelAndView("/hrzrl/query2")
                .addObject("list", list)
                .addObject("model", model)
                .addObject("zd", zd)
                .addObject("px", px);
    }

    @GetMapping("/queryCompanysToExcel")
    public ModelAndView queryCompanysToExcel(@RequestParam("id") String id, @RequestParam(value = "zd", required = false) String zd,
                                             @RequestParam(value = "px", required = false) String px) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, id);
        if (zd != null && px != null) {
            map.put(2, zd);
            map.put(3, px);
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        map.put(4, sf.format(new Date()));
        map.put(5, sf.format(Dates2.getPreviousDate2(new Date(), 1)));
        List<GwgrzqModel> gwgrzqModels = gwgrzqService.find();
        Date date;
        if (gwgrzqModels == null || gwgrzqModels.size() <= 0)
            date = new Date();
        else
            date = gwgrzqModels.get(0).getStrat_day();

        long l = (new Date().getTime() - date.getTime())/1000;
        map.put(6, l + "");
        List<HrzrlFxModel> list = hrzrlService.getJzs(map);
        StructureModel model = structureService.getById(id);
        String excels;
        try {
            excels = CreateSimpleExcelToDisk.excels2(model.getNames(), list);
        } catch (Exception e) {
            return new ModelAndView("/hrzrl/dow");
        }
        return new ModelAndView("/hrzrl/dow").addObject("excels", excels);
    }

    @GetMapping("/queryCompanysToExcel2")
    public ModelAndView queryCompanysToExcel2(@RequestParam("id") String id, @RequestParam(value = "zd", required = false) String zd,
                                              @RequestParam(value = "px", required = false) String px) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, id);
        if (zd != null && px != null) {
            map.put(2, zd);
            map.put(3, px);
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        map.put(4, sf.format(new Date()));
        map.put(5, sf.format(Dates2.getPreviousDate2(new Date(), 1)));
        List<GwgrzqModel> gwgrzqModels = gwgrzqService.find();
        Date date;
        if (gwgrzqModels == null || gwgrzqModels.size() <= 0)
            date = new Date();
        else
            date = gwgrzqModels.get(0).getStrat_day();

        long l = (new Date().getTime() - date.getTime())/1000;
        map.put(6, l + "");
        List<HrzrlFxModel> list = hrzrlService.getJzs2(map);
        StructureModel model = structureService.getById(id);
        String excels;
        try {
            excels = CreateSimpleExcelToDisk.excels3(model.getNames(), list);
        } catch (Exception e) {
            return new ModelAndView("/hrzrl/dow");
        }
        return new ModelAndView("/hrzrl/dow").addObject("excels", excels);
    }

    @GetMapping("/queryCompanysToExcel3")
    public ModelAndView queryCompanysToExcel3(@RequestParam(value = "fileName", required = false) String fileName) {

        return new ModelAndView("/exchange/dow").addObject("fileName", fileName);
    }

    
    @GetMapping("/qureyid/{id}")
    public ModelAndView qureyid(@PathVariable("id") String id) {
        return new ModelAndView("/hrzrl/index")
                .addObject("id", id);
    }

    /********热单耗***********************************************************************/
    //机组
    //    查看热单耗
    @GetMapping("/queryRdh/{pageNow}")
    public ModelAndView queryRdh(@PathVariable("pageNow") Integer pageNow) {
        Page<CrewModel> page = crewService.findAllPage(pageNow, pageSize);
        return new ModelAndView("/hrzrl/queryRdh")
                .addObject("page", page);
    }

    @GetMapping("/queryRdhQuery")
    public ModelAndView queryRdhQuery(@RequestParam("id") String id) throws Exception {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        String format1 = sim.format(new Date());
        String s = format1 + " 00:00:00";
        Date parse1 = df.parse(s);
        long l = (new Date().getTime() - parse1.getTime()) / 1000;
        List<HrzrlFxModel> list = hrzrlService.getById2(id, l);
        list.remove(0);
        List<QxxxModel> page = qxxxService.findPage(0, 5);

        return new ModelAndView("/hrzrl/queryRdhList")
                .addObject("page", list);
    }

    //    站点
//    查看热单耗
    @GetMapping("/queryRdh2/{pageNow}")
    public ModelAndView queryRdh2(@PathVariable("pageNow") Integer pageNow) {
        Page<HeatModel> page = heatService.findAllPage(pageNow, pageSize);
        return new ModelAndView("/hrzrl/queryRdh2")
                .addObject("page", page);
    }

    @GetMapping("/queryRdhQuery2")
    public ModelAndView queryRdhQuery2(@RequestParam("id") String id) {
        List<GwgrzqModel> gwgrzqModels = gwgrzqService.find();
        Date date;
        if (gwgrzqModels == null || gwgrzqModels.size() <= 0)
            date = new Date();
        else
            date = gwgrzqModels.get(0).getStrat_day();
        long l = (new Date().getTime() - date.getTime()) / 1000;
        List<HrzrlFxModel> list = hrzrlService.getById3(id, l);
        list.remove(0);
        return new ModelAndView("/hrzrl/queryRdhList2")
                .addObject("page", list);
    }

    /********纵向对比***********************************************************************/
    @GetMapping("/zxdbIndex/{pageNow}")
    public ModelAndView zxdbIndex(@PathVariable("pageNow") Integer pageNow) {
        Page<CrewModel> page = crewService.findAllPage(pageNow, pageSize);
        return new ModelAndView("/hrzrl/zxdbIndex")
                .addObject("page", page);
    }

    @GetMapping("/zxdbQuery")
    public ModelAndView zxdbQuery(@RequestParam("id") String id) {
        return new ModelAndView("/hrzrl/zxdbQuerySj")
                .addObject("id", id);
    }

    @RequestMapping("/choiceTime")
    public ModelAndView choiceTime(@RequestParam("id") String id,@RequestParam("na")String name) {
        name = "当前机组："+name;
        return new ModelAndView("/hrzrl/zxdbQuerySj")
                .addObject("jzid", id)
                .addObject("name",name);
    }

    @RequestMapping("/confirmTime")
    public ModelAndView confirmTime(@RequestParam("id") String id, @RequestParam("rq") String rq) throws Exception {
        String[] ss = rq.split(",");
        Map<Integer, long[]> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < ss.length; i++) {
            long[] l = new long[2];
            String[] split = ss[i].split("]");
            if (split.length > 1) {
                l[0] = sdf.parse(ss[i].split("]")[0]).getTime();
                l[1] = sdf.parse(ss[i].split("]")[1]).getTime()+1000;
                map.put(i + 1, l);
            }
        }
        List<HrzrlFxModel> list = hrzrlService.getByIdAndRq(id, map);
        return new ModelAndView("/hrzrl/querySj")
                .addObject("list", list);
    }

    @GetMapping("/zxdbIndex2/{pageNow}")
    public ModelAndView zxdbIndex2(@PathVariable("pageNow") Integer pageNow) {
        Page<HeatModel> page = heatService.findAllPage(pageNow, pageSize);
        return new ModelAndView("/hrzrl/zxdbIndex2")
                .addObject("page", page);
    }

    @GetMapping("/zxdbQuery2")
    public ModelAndView zxdbQuery2(@RequestParam("id") String id) {
        return new ModelAndView("/hrzrl/zxdbQuerySj2")
                .addObject("id", id);
    }

    @RequestMapping("/confirmTime2")
    public ModelAndView confirmTime2(@RequestParam("id") String id, @RequestParam("rq") String rq) throws Exception {
        String[] ss = rq.split(",");
        Map<Integer, long[]> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < ss.length; i++) {
            long[] l = new long[2];
            String[] split = ss[i].split("]");
            if (split.length > 1 && ss[i].split("]")[0] != null && ss[i].split("]")[1] != null) {
                l[0] = sdf.parse(ss[i].split("]")[0]).getTime();
                l[1] = sdf.parse(ss[i].split("]")[1]).getTime()+1000;
                map.put(i + 1, l);
            }
        }
        List<HrzrlFxModel> list = hrzrlService.getByIdAndRq2(id, map);
        return new ModelAndView("/hrzrl/querySj2")
                .addObject("list", list);
    }


    /********横向对比***********************************************************************/

    @RequestMapping("/hxdbIndex")
    public ModelAndView hxdbIndex(@RequestParam(value = "pageNow", required = false) Integer pageNow,
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
            pageSize = 15;
        }
        if (serach == null || "null".equals(serach)) {
            serach = "";
        }
        Page<CrewModel> page = crewService.findAllPage1(pageNow, pageSize, serach);
        return new ModelAndView("/hrzrl/aspect")
                .addObject("page", page)
                .addObject("A", A)
                .addObject("B", B)
                .addObject("AA", AA)
                .addObject("BB", BB)
                .addObject("serach", serach);
    }

    @GetMapping("/hxdbQuery")
    public ModelAndView hxdbQuery(@RequestParam("id") String id) {
        return new ModelAndView("/hrzrl/hxdbQuerySj")
                .addObject("id", id);
    }

    @RequestMapping("/confirmTime1")
    public ModelAndView confirmTime1(@RequestParam("id") String id, @RequestParam("rq") String rq) throws Exception {
        //切割字符串将周期一到周期七的时间提取出来
        String[] split = rq.split("--");
        String[] split1 = id.split(",");
        ArrayList<Object> obj = new ArrayList<>();
        Map<Integer, long[]> map = new HashMap<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = 0;
        long ll = 0;
        try {
            l = sim.parse(split[0]).getTime();
            ll = sim.parse(split[1]).getTime()+1000;
            long[] longs = new long[2];
            longs[0] = l;
            longs[1] = ll;
            map.put(1, longs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < split1.length; i++) {
            List<HrzrlFxModel> byId = hrzrlService.getByIdAndRq(split1[i], map);
            for (HrzrlFxModel s12 : byId) {
                obj.add(s12);
            }
        }
        return new ModelAndView("/hrzrl/transverse")
                .addObject("list", obj);
    }

    @RequestMapping("/hxdbIndex2/{pageNow}")
    public ModelAndView hxdbIndex2(@PathVariable("pageNow") Integer pageNow,
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
        return new ModelAndView("/hrzrl/aspect2")
                .addObject("page", page)
                .addObject("A", A)
                .addObject("B", B)
                .addObject("AA", AA)
                .addObject("BB", BB)
                .addObject("serach", serach);
    }

    @GetMapping("/hxdbQuery2")
    public ModelAndView hxdbQuery2(@RequestParam("id") String id) {
        return new ModelAndView("/hrzrl/hxdbQuerySj2")
                .addObject("id", id);
    }

    @RequestMapping("/confirmTime21")
    public ModelAndView confirmTime21(@RequestParam("id") String id, @RequestParam("rq") String rq) throws Exception {
        //切割字符串将周期一到周期七的时间提取出来
        String[] split = rq.split("--");
        String[] split1 = id.split(",");
        ArrayList<Object> obj = new ArrayList<>();
        Map<Integer, long[]> map = new HashMap<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = 0;
        long ll = 0;
        try {
            l = sim.parse(split[0]).getTime();
            ll = sim.parse(split[1]).getTime()+1000;
            long[] longs = new long[2];
            longs[0] = l;
            longs[1] = ll;
            map.put(1, longs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < split1.length; i++) {
            List<HrzrlFxModel> byId = hrzrlService.getByIdAndRq2(split1[i], map);
            for (HrzrlFxModel s12 : byId) {
                obj.add(s12);
            }
        }
        return new ModelAndView("/hrzrl/transverse2").addObject("list", obj);
    }
}