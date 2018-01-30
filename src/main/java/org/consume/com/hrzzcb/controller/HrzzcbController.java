package org.consume.com.hrzzcb.controller;

import com.github.pagehelper.Page;
import org.consume.com.aspect.service.AspectService;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.hrzcbfxdl.service.HrzcbfxdlService;
import org.consume.com.hrzrl.service.HrzrlService;
import org.consume.com.hrzzcb.service.HrzzcbService;
import org.consume.com.sdrdj.service.SdrdjService;
import org.consume.com.sltj.service.SltjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hrzzcb")
public class HrzzcbController {
    @Value("${page.pageSize}")
    private Integer pageSize;
    @Autowired
    private HeatService heatService;
    @Autowired
    private HrzrlService hrzrlService;
    @Autowired
    private SdrdjService sdrdjService;
    @Autowired
    private SltjService sltjService;
    @Autowired
    private AspectService aspectService;
    @Autowired
    private HrzzcbService hrzzcbService;
    @Autowired
    private HrzcbfxdlService hrzcbfxdlService;

    /*************纵向对比*************************************************************************/
    @GetMapping("/init1/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        Page<HeatModel> page = heatService.findAllPage(pageNow, pageSize);
        return new ModelAndView("/hrzzcb/index")
                .addObject("page", page);
    }

    @GetMapping("/query")
    public ModelAndView query(@RequestParam("id") String id) {
        return new ModelAndView("/hrzzcb/zxdbQuerySj")
                .addObject("id", id);
    }

    @RequestMapping("/confirmTime")
    public ModelAndView confirmTime(@RequestParam("id") String id, @RequestParam("rq") String rq) throws Exception {
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
        ArrayList<Object> array = hrzzcbService.getByIdAndRq(id, map);

        return new ModelAndView("/hrzzcb/querySj")
                .addObject("list", array);
    }


    /*************横向对比*************************************************************************/
    @RequestMapping("/init2")
    public ModelAndView init2(@RequestParam(value = "pageNow", required = false) Integer pageNow,
                              @RequestParam(value = "serach", required = false) String serach,
                              @RequestParam(value = "A", required = false) String A,
                              @RequestParam(value = "AA", required = false) String AA,
                              @RequestParam(value = "B", required = false) String B,
                              @RequestParam(value = "BB", required = false) String BB) {
        if (pageNow == null) {
            pageNow = 0;
        }
        Page<ExchangeModel> page = hrzcbfxdlService.findAllPage(pageNow, pageSize, serach);
        return new ModelAndView("/hrzzcb/index2")
                .addObject("page", page)
                .addObject("A", A)
                .addObject("B", B)
                .addObject("AA", AA)
                .addObject("BB", BB)
                .addObject("serach", serach);
    }

    @RequestMapping("/toTime")
    public ModelAndView toTime(@RequestParam("s") String s) {
        return new ModelAndView("/hrzzcb/choiceTime2").addObject("jzid", s);
    }


    @RequestMapping("/confirmTime2")
    public ModelAndView confirmTime2(@RequestParam("t") String t, @RequestParam("j") String j) {
        //切割字符串将周期一到周期七的时间提取出来
        String[] split = t.split("--");
        String[] split1 = j.split(",");
        ArrayList<Object> obj = new ArrayList<>();
        Map<Integer, long[]> map = new HashMap<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = 0;
        long ll = 0;
        try {
            l = sim.parse(split[0]).getTime();
            ll = sim.parse(split[1]).getTime() + 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] lll = new long[2];
        lll[0] = l;
        lll[1] = ll;
        map.put(1, lll);
        for (int i = 0; i < split1.length; i++) {
            ArrayList<Object> array = hrzzcbService.getByIdAndRq(split1[i], map);
            obj.add(array.get(0));
        }
        return new ModelAndView("/hrzzcb/transverse2").addObject("list", obj);
    }

}
