package org.consume.com.hrzcbfx.controller;

import org.consume.com.gwgrzq.model.GwgrzqModel;
import org.consume.com.gwgrzq.service.GwgrzqService;
import org.consume.com.hrzcbfx.model.HrzcbfxModel;
import org.consume.com.hrzcbfx.service.HrzcbfxService;
import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.hrzrl.service.HrzrlService;
import org.consume.com.sdrdj.model.SdrdjModel;
import org.consume.com.sdrdj.service.SdrdjService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.util.ExportExcelSeedBack.ExportExcelSeedBack;
import org.consume.com.util.date.Dates2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/hrzcbfx")
public class HrzcbfxController {

    @Autowired
    private StructureService structureService;
    @Autowired
    private HrzcbfxService hrzcbfxService;
    @Autowired
    private SdrdjService sdrdjService;
    @Autowired
    private HrzrlService hrzrlService;
    @Autowired
    private GwgrzqService gwgrzqService;

    @GetMapping("/init")
    public ModelAndView init() {
        List<StructureModel> page = structureService.findAlls("0", "1");
        return new ModelAndView("/hrzcbfx/fgs")
                .addObject("page", page);
    }
    @GetMapping("/init2")
    public ModelAndView init2() {
        List<StructureModel> page = structureService.findAlls("0", "1");
        return new ModelAndView("/hrzcbfx/fgs2")
                .addObject("page", page);
    }

    /**
     * 机组查看
     *
     * @param id 分公司id
     * @return
     * @throws Exception
     */
//    @RequiresPermissions(value = "exchange:queryByCompany")
    @GetMapping("/queryByCompany")
    public ModelAndView querys(@RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "zd", required = false) String zd,
                               @RequestParam(value = "px", required = false) String px) {
    	List<SdrdjModel> finds = sdrdjService.finds();
        SdrdjModel model;
        if (finds != null && finds.size() > 0)
            model = finds.get(0);
        else
            model = new SdrdjModel();
        StructureModel structuremodel = structureService.getById(id);
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

        long l = (new Date().getTime() - date.getTime()) / 1000;
        map.put(6, l + "");
        List<HrzrlFxModel> list = hrzrlService.getJzs2(map);
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)!=null&&!"".equals(list.get(i)))
            {
                list.get(i).setCb(Double.toString(Double.valueOf(list.get(i).getYwljrl()==null?
                        "0":list.get(i).getYwljrl())*Double.valueOf(model.getV3())));
            }else
                {
                    list.remove(i);
                    i--;
                }
        }
        return new ModelAndView("/hrzcbfx/index")
                .addObject("list", list)
                .addObject("model", model)
                .addObject("structuremodel",structuremodel)
                .addObject("zd", zd)
                .addObject("px", px);
    }

    /**
     * 换热站查看
     *
     * @param id 分公司id
     * @return
     * @throws Exception
     */
//    @RequiresPermissions(value = "exchange:queryByCompany")
    @GetMapping("/queryByCompany2")
    public ModelAndView querys2(@RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "zd", required = false) String zd,
                               @RequestParam(value = "px", required = false) String px) {
        List<SdrdjModel> finds = sdrdjService.finds();
        SdrdjModel model;
        if (finds != null && finds.size() > 0)
            model = finds.get(0);
        else
            model = new SdrdjModel();
        StructureModel structuremodel = structureService.getById(id);
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

        long l = (new Date().getTime() - date.getTime()) / 1000;
        map.put(6, l + "");
        List<HrzrlFxModel> list = hrzrlService.getJzs3(map);
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)!=null&&!"".equals(list.get(i)))
            {
                list.get(i).setCb(Double.toString(Double.valueOf(list.get(i).getYwljrl()==null?
                        "0":list.get(i).getYwljrl())*Double.valueOf(model.getV3())));
            }else
            {
                list.remove(i);
                i--;
            }
        }
        return new ModelAndView("/hrzcbfx/index2")
                .addObject("list", list)
                .addObject("model", model)
                .addObject("structuremodel",structuremodel)
                .addObject("zd", zd)
                .addObject("px", px);
    }

    @PostMapping("/queryByCompanyJson")
    public List<HrzcbfxModel> queryByCompanyJson(@RequestParam("id") String id) {
        List<HrzcbfxModel> hrzcbfxModels = hrzcbfxService.queryByCompany(id);
        List<SdrdjModel> finds = sdrdjService.finds();
        SdrdjModel sdrdjModel;
        if (finds == null || finds.size() <= 0) {
            sdrdjModel = new SdrdjModel();
            sdrdjModel.setV1(new Double(0));
            sdrdjModel.setV2(new Double(0));
            sdrdjModel.setV3(new Double(0));
        } else {
            sdrdjModel = finds.get(0);
        }

        List<HrzcbfxModel> list = new ArrayList<>();
        for (HrzcbfxModel k : hrzcbfxModels
                ) {
            HrzcbfxModel h = new HrzcbfxModel();
            h.setUuid(k.getUuid());
            h.setV(k.getV() * sdrdjModel.getV1());
            h.setV1(k.getV1() * sdrdjModel.getV2());
            h.setV2(k.getV2() * sdrdjModel.getV3());
            list.add(h);
        }

        return list;
    }
    @RequestMapping("/formTable")
    public String fromTable(@RequestParam("table")String table,
                            @RequestParam("na")String na)
    {
        String[] split = table.split(";");
        List<Object[]> s = new ArrayList<>();
        for (String ss : split)
        {
            String[] split1 = ss.split(",");
            s.add(split1);
        }
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        //导出文件的标题
        String title = na + sim.format(new Date()) +"数据.xls";
        //设置表格标题行，由于页面报表的表头固定不变，所以为了省事后端直接定死
        String[] headers = new String[] {"序号","换热站名称", "通讯状态","热单价","一网累计热量","热成本"};
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        try {
            //防止中文乱码
            String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
            response.setContentType("octets/stream");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            //ExportExcel ex = new ExportExcel(title, headers, dataList);//有标题
            ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, s);//没有标题
            ex.export();
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }
    }
}
