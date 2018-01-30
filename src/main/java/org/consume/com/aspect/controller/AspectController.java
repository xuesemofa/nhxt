package org.consume.com.aspect.controller;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.consume.com.aspect.service.AspectService;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.unitcomparison.model.UnitComparisonModel;
import org.consume.com.unitcomparison.service.UnitcomparisonService;
import org.consume.com.user.model.UserModel;
import org.consume.com.util.excel.CreateSimpleExcelToDisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RequestMapping("/aspect")
@RestController
public class AspectController {
    private static String views = "/aspect";

    @Value("${page.pageSize}")
    private int pageSizeDefull;

    @Autowired
    private AspectService service;
    @Autowired
    private UnitcomparisonService unitcomparisonService;

    @RequiresPermissions(value = "hrz:gk:jzzxdb")
    @GetMapping("/init")
    public ModelAndView init(@RequestParam(value = "pageNow", required = false) Integer pageNow,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize,
                             @RequestParam(value = "serach", required = false) String serach) {
        if (pageNow == null) {
            pageNow = 0;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = pageSizeDefull;
        }
        Page<ExchangeModel> page = unitcomparisonService.findAllPage(pageNow, pageSize, serach);
        return new ModelAndView(views + "/aspect")
                .addObject("page", page)
                .addObject("serach", serach);
    }

    @RequiresPermissions(value = "hrz:gk:jzhxdb")
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
            pageSize = pageSizeDefull;
        }
        Page<ExchangeModel> page = unitcomparisonService.findAllPage(pageNow, pageSize, serach);
        return new ModelAndView(views + "/aspect2")
                .addObject("page", page)
                .addObject("A", A)
                .addObject("B", B)
                .addObject("AA", AA)
                .addObject("BB", BB)
                .addObject("serach", serach);
    }

    @RequestMapping("/choiceTime")
    public ModelAndView choiceTime(@RequestParam("id") String id,@RequestParam("na")String name) {
        name = "当前机组："+name;
        return new ModelAndView(views + "/choiceTime")
                .addObject("jzid", id)
                .addObject("name",name);
    }

    @RequestMapping("/confirmTime")
    public ModelAndView confirmTime(@RequestParam("t") String t, @RequestParam("j") String j) throws Exception {
        //切割字符串将周期一到周期七的时间提取出来
        String[] split = t.split("--");
        ArrayList<Object> obj = new ArrayList<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int ii = 1;
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            i++;
            String s1 = split[i];
            Date p = sim.parse(s);
            Date p1 = sim.parse(s1);
            long l = p.getTime();
            long ll = p1.getTime();
            double[] byId = service.findById(j, l, ll);
            double tqwdByTime = service.findTqwdByTime(s, s1);
            CrewModel jzById = service.findJzById(j);
            Object[] ob = new Object[5];
            ob[0] = s + "---" + s1;
            ob[1] = (tqwdByTime);
            ob[2] = (jzById.getNames());
            ob[3] = (byId[0]);
            ob[4] = (byId[1]);
            obj.add(ob);
            ii++;
        }
        UnitComparisonModel u = new UnitComparisonModel();
        Subject subject = SecurityUtils.getSubject();
        UserModel user = (UserModel) subject.getSession().getAttribute("user");
        u.setAccid(user.getAccount());
        u.setJzaid(j);
        u.setJzbid(t);
        u.setScsj(new Timestamp(System.currentTimeMillis()));
        u.setZhlx(1);
        unitcomparisonService.add(u);
        return new ModelAndView(views + "/portrait").addObject("list", obj);
    }

    @RequestMapping("/confirmTime3")
    public ModelAndView confirmTime3(@RequestParam("t") String t, @RequestParam("j") String j) throws Exception {
        //切割字符串将周期一到周期七的时间提取出来
        String[] split = t.split("--");
        ArrayList<Object> obj = new ArrayList<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int ii = 1;
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            i++;
            String s1 = split[i];
            Date p = sim.parse(s);
            Date p1 = sim.parse(s1);
            long l = p.getTime();
            long ll = p1.getTime();
            double[] byId = service.findById(j, l, ll);
            double tqwdByTime = service.findTqwdByTime(s, s1);
            CrewModel jzById = service.findJzById(j);
            Object[] ob = new Object[5];
            ob[0] = s + "---" + s1;
            ob[1] = (tqwdByTime);
            ob[2] = (jzById.getNames());
            ob[3] = (byId[0]);
            ob[4] = (byId[1]);
            obj.add(ob);
            ii++;
        }
        return new ModelAndView(views + "/portrait").addObject("list", obj);
    }

    @RequestMapping("/toTime")
    public ModelAndView toTime(@RequestParam("s") String s,@RequestParam("ss")String ss) {
        ss = "当前机组："+ss;
        return new ModelAndView(views + "/choiceTime2")
                .addObject("jzid", s)
                .addObject("ss",ss);
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
            ll = sim.parse(split[1]).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < split1.length; i++) {
            double[] byId = service.findById(split1[i], l, ll);
            CrewModel jzById = service.findJzById(split1[i]);
            double tqwdByTime = service.findTqwdByTime(split[0], split[1]);
            Object[] ob = new Object[6];
            ob[0] = split[0]+"--"+split[1];
            ob[1] = (tqwdByTime);
            ob[2] = (jzById.getNames());
            ob[3] = (byId[0]);
            ob[4] = (byId[1]);
            obj.add(ob);
        }
        UnitComparisonModel u = new UnitComparisonModel();
        Subject subject = SecurityUtils.getSubject();
        UserModel user = (UserModel) subject.getSession().getAttribute("user");
        u.setAccid(user.getAccount());
        u.setJzaid(j);
        u.setJzbid(t);
        u.setScsj(new Timestamp(System.currentTimeMillis()));
        u.setZhlx(2);
        unitcomparisonService.add(u);
        return new ModelAndView(views + "/transverse").addObject("list", obj);
    }

    @RequestMapping("/confirmTime4")
    public ModelAndView confirmTime4(@RequestParam("t") String t, @RequestParam("j") String j) {
        //切割字符串将周期一到周期七的时间提取出来
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
            double[] byId = service.findById(split1[i], l, ll);
            CrewModel jzById = service.findJzById(split1[i]);
            double tqwdByTime = service.findTqwdByTime(split[0], split[1]);
            Object[] ob = new Object[5];
            ob[0] = (tqwdByTime);
            ob[1] = (jzById.getNames());
            ob[2] = (byId[0]);
            ob[3] = (byId[1]);
            obj.add(ob);
        }
        return new ModelAndView(views + "/transverse").addObject("list", obj);
    }

    @RequestMapping("/queryCompanysToExcel")
    public String queryCompanysToExcel(
            @RequestParam("name")String name,
            @RequestParam("title")String title,
            @RequestParam("data")String data,
            HttpServletResponse response) throws Exception {
        String[] split = data.split("]");
        for(int i=1;i<split.length-1;i++)
        {
            split[0] += "]"+split[i];
        }
        String lastData = split[split.length-1];
        String s = CreateSimpleExcelToDisk.strToJs(name, title, data, lastData, response);
        if(s!=null||!"".equals(s))
        {
            return s;
        }else
            {
                return "0";
            }
    }
}
